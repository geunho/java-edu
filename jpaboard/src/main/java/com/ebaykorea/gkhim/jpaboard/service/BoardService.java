package com.ebaykorea.gkhim.jpaboard.service;

import com.ebaykorea.gkhim.jpaboard.domain.Board;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BoardService {
  Board addBoard(String email, Board board);
  Page<Board> getBoards(int page);
}
