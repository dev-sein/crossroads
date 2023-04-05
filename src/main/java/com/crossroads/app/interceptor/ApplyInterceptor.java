//package com.crossroads.app.interceptor;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//@Slf4j
//public class ApplyInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("ApplyInterceptor preHandler");
//
//        HttpSession session = request.getSession();
//        if (session.getAttribute("memberId") == null) {
//            response.sendRedirect("/member/login");
//        }
//        return true;
//    }
//}