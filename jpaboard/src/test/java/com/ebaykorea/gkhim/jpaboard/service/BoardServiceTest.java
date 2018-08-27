package com.ebaykorea.gkhim.jpaboard.service;

import com.ebaykorea.gkhim.jpaboard.domain.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class BoardServiceTest {
  @Autowired
  BoardService boardService;

  @Test
  public void getBoardsTest() {
    Page<Board> boards = boardService.getBoards(1);
    System.out.println(boards.getTotalPages());
    System.out.println(boards.getTotalElements());

    for (Board board : boards) {
      System.out.println(board.getTitle());
    }
  }
}
