package com.crossroads.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/")
@Slf4j
public class testController {

    @GetMapping("/")
    public void deviceCheck(Device device) {
        if (device.isMobile()) {
            log.info("Hello mobile user!");
        } else if (device.isTablet()) {
            log.info("Hello tablet user!");
        } else {
            log.info("Hello desktop user!");
        }
    }
}