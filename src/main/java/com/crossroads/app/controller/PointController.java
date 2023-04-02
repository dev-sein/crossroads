package com.crossroads.app.controller;

import com.crossroads.app.service.PointService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/points/*")
@RequiredArgsConstructor
@Slf4j
public class PointController {
    private final PointService pointService;
    //    포인트 구입
    @GetMapping("buyPoint")
    public String buyPoint() {
        return "point/buyPoint";
    }

    //    포인트 구입완료
    @GetMapping("buyPointFin")
    public String buyPointFin() {
        return "point/buyPointFin";
    }


    //    포인트 환전
    @GetMapping("/exchange-point")
    public String changePoint(Model model, HttpServletRequest request){
        request.getSession().setAttribute("memberId",6L);   // 세션에 임시로 값 담아둠
        Long memberId = (Long)request.getSession().getAttribute("memberId");
        log.info(pointService.getPoint(memberId).toString());
        model.addAttribute("point", pointService.getPoint(memberId));
        return "point/changePoint";
    }

    //    포인트 환전 완료
    @GetMapping("/exchange-complete")
    public String exchangeComplete(){
        return "point/changePointFin";
    }

    //    포인트 환전 모바일
    @GetMapping("/exchange-point-mobile")
    public String changePointMobile(@RequestParam(value = "exchange", required = false) String exchange, HttpServletRequest request, Model model){
        request.getSession().setAttribute("memberId",6L);   // 세션에 임시로 값 담아둠
        Long memberId = (Long)request.getSession().getAttribute("memberId");
        log.info(pointService.getPoint(memberId).toString());
        model.addAttribute("exchange",exchange);
        model.addAttribute("point", pointService.getPoint(memberId));
        return "mobile/change-point-mobile";
    }

    //    포인트 환전 완료
    @PostMapping("/exchange-to-money")
    public RedirectView changeToMoney(HttpServletRequest request){
        Long memberId = (Long)request.getSession().getAttribute("memberId");
        pointService.modifyPoint(memberId);
        return new RedirectView("/points/exchange-complete");
    }

    //    포인트 환전 완료 모바일
    @PostMapping("/exchange-to-money-mobile")
    public RedirectView changeToMoneyMobile(HttpServletRequest request){
        Long memberId = (Long)request.getSession().getAttribute("memberId");
        pointService.modifyPoint(memberId);
        return new RedirectView("/points/exchange-point-mobile?exchange=ok");
    }
}
