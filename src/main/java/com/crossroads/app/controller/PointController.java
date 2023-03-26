package com.crossroads.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PointController {
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
    @GetMapping("changePoint")
    public String changePoint() {
        return "point/changePoint";
    }

    //    포인트 환전완료
    @GetMapping("changePointFin")
    public String changePointFin() {
        return "point/changePointFin";
    }
}
