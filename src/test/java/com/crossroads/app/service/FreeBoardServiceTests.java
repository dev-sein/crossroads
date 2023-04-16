//package com.crossroads.app.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest
//@Slf4j
//public class FreeBoardServiceTests {
//
//    @Autowired
//    FreeBoardService freeBoardService;
//
//    @Test
//    public void getListAdminTest() {
////        log.info(freeBoardService.getListAdmin().toString());
////        assertThat(freeBoardService.getListAdmin().get(0).getBoardTitle()).isEqualTo("테스트제목1");
////        assertThat(freeBoardService.getListAdmin().get(1).getReplyCount()).isEqualTo(0);
//    }
//
//    @Test
//    public void removeTest() {
//        List<String> boardIds = new ArrayList<>();
//        boardIds.add("90");
//        boardIds.add("89");
//
////        boardIds.stream().map(boardId -> Long.parseLong(boardId)).forEach(boardDAO::deleteById);
//        freeBoardService.remove(boardIds);
//    }
//
//
//}
