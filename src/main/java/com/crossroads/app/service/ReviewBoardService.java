package com.crossroads.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Qualifier("board")
@RequiredArgsConstructor
public class ReviewBoardService implements BoardService {

}
