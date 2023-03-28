package com.crossroads.app.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@Slf4j
public class AdminControllerTests {

    @Autowired
    AdminController adminController;

    @Test
    public void deleteBoardTest() {
        List<String> boardIds = new ArrayList<>();
        boardIds.add("88");
        boardIds.add("87");

        adminController.deleteBoard(boardIds);
    }
}
