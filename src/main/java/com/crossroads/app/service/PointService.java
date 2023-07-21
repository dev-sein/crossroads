package com.crossroads.app.service;

import com.crossroads.app.domain.dao.ApplyDAO;
import com.crossroads.app.domain.dao.PointDAO;
import com.crossroads.app.domain.dto.BoardDTO;
import com.crossroads.app.domain.dto.Criteria;
import com.crossroads.app.domain.dto.PageDTO;
import com.crossroads.app.domain.dto.PointDTO;
import com.crossroads.app.domain.vo.MemberVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.PerObjectInterfaceTypeMunger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class PointService {
    private final PointDAO pointDAO;

//    현재 포인트
    public Long getPoint(Long memberId) {
        return pointDAO.findPointById(memberId);
    }

//    포인트 수정
    public void modifyPoint(Long memberId){
        pointDAO.setPoint(memberId);
    }

//    마이페이지 포인트 내역
    public List<PointDTO> getListMyPoint(Long memberId) { return pointDAO.findByIdMyPoint(memberId); }

//    포인트 차감
    public void modifyAfterApply(Long memberId, Integer memberPoint) { pointDAO.setAfterApply(memberId, memberPoint);;}


//    관리자 포인트 내역 목록
    public Map<String, Object> getListAdmin(Map<String, Object> requestData, Criteria criteria) {
        Map<String, Object> result = new HashMap<String, Object>();

        String keyword = (String) requestData.get("keyword");
        int page = (int) requestData.get("page");

        if (page == 0) {
            page = 1;
        }
        criteria = criteria.create(page, 6);

        List<PointDTO> points = pointDAO.findAllAdmin(criteria, keyword);


        result.put("points", points);
        result.put("pagination", new PageDTO().createPageDTO(criteria, getCountAdmin(keyword)));

        return result;
    }

//    관리자 포인트 내역 총 수
    public Integer getCountAdmin(String keyword) {
        return pointDAO.findCountAllAdmin(keyword);
    }


//    관리자 포인트 내역 삭제
    @Transactional(rollbackFor = Exception.class)
    public void remove(List<Long> pointIds) {
        pointIds.stream().forEach(pointId -> {
            PointDTO pointDTO = null;
            Integer currentPoint = null;
            pointDTO = getDetail(pointId); // 현재 객체 설정

            // status가 0이면 초보자는 결제, 베테랑은 적립 즉, 대상자는 포인트가 + 됐었다
            if (pointDTO.getPointStatus() == 0) {
                // + 됐었던 포인트를 차감 시켜줌.
                currentPoint = getPoint(pointDTO.getMemberId()).intValue() - pointDTO.getPointPoint();
            } else {
                // + 됐었던 포인트를 증감 시켜줌.
                currentPoint = getPoint(pointDTO.getMemberId()).intValue() + pointDTO.getPointPoint();
            }

            modifyAfterApply(pointDTO.getMemberId(), currentPoint); // 현재 포인트 변경
            pointDAO.deleteById(pointId); // 포인트 내역 삭제
        });
    }
//    일반 포인트 내역 삭제
    @Transactional(rollbackFor = Exception.class)
    public void remove(Long pointId) {
        PointDTO pointDTO = null;
        Integer currentPoint = null;
        pointDTO = getDetail(pointId); // 현재 객체 설정

        // status가 0이면 초보자는 결제, 베테랑은 적립 즉, 대상자는 포인트가 + 됐었다
        if (pointDTO.getPointStatus() == 0) {
            // + 됐었던 포인트를 차감 시켜줌.
            currentPoint = getPoint(pointDTO.getMemberId()).intValue() - pointDTO.getPointPoint();
        } else {
            // + 됐었던 포인트를 증감 시켜줌.
            currentPoint = getPoint(pointDTO.getMemberId()).intValue() + pointDTO.getPointPoint();
        }

        modifyAfterApply(pointDTO.getMemberId(), currentPoint); // 현재 포인트 변경
        pointDAO.deleteById(pointId); // 포인트 내역 삭제
    }


//    포인트 상세
    public PointDTO getDetail(Long pointId) {
        return pointDAO.selectById(pointId);
    }

//    포인트 등록 및 차감
    public void savePoint(PointDTO pointDTO) {
        pointDAO.register(pointDTO); //포인트 내역 등록
        int mypoint = pointDAO.findPointById(pointDTO.getMemberId()).intValue() - pointDTO.getPointPoint();
        log.info(String.valueOf(mypoint));
        pointDAO.setAfterApply(pointDTO.getMemberId(), mypoint);
    }

//    포인트 충전
    public void modifyPointByMemberId(Long memberId, Long memberPoint) {
        pointDAO.setPointByMemberId(memberId, memberPoint);
    }
}
