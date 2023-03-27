package com.crossroads.app.controller;

import com.crossroads.app.service.ApplyService;
import com.crossroads.app.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/applies/*")
@RequiredArgsConstructor
@Slf4j
public class MobileController {
    private final ApplyService applyService;
    private final PointService pointService;

    @GetMapping("join-mobile")
    public String joinMobile(){
        return "mobile/join-mobile";
    }

    @GetMapping("list-mobile")
    public String listMobile(Model model, HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        session.setAttribute("memberId", 1L);
        model.addAttribute("applies", applyService.getList());
        model.addAttribute("others", applyService.getCount((Long)session.getAttribute("memberId")));
        return "mobile/list-mobile";
    }

    @GetMapping("list-mobile/search")
    public String listMobileSearch(){
        return "mobile/list-mobile/search";
    }

    @PostMapping("list-mobile/change-status")
    @ResponseBody
    public void changeStatus(Long applyId, HttpServletRequest request){
        HttpSession session = request.getSession();
        applyService.modifyStatus(applyId);
        Map<String, Object> info = new HashMap<>();
        info.put("memberId", session.getAttribute("memberId"));
        info.put("applyId",applyId);

        applyService.modifyVeteranId(info);
    }

    @GetMapping("point/change-point")
    public String changePoint(){
        return "point/changePoint";
    }

    @GetMapping("point/change-point-mobile")
    public String changePointMobile(Model model, HttpServletRequest request){
        request.getSession().setAttribute("memberId",3L);
        Long memberId = (Long)request.getSession().getAttribute("memberId");
        log.info(pointService.getPoint(memberId).toString());
        model.addAttribute("point", pointService.getPoint(memberId));
        return "mobile/change-point-mobile";
    }



}
