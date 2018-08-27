package com.ebaykorea.gkhim.jpaboard.service;

import com.ebaykorea.gkhim.jpaboard.domain.Board;
import com.ebaykorea.gkhim.jpaboard.domain.Member;
import com.ebaykorea.gkhim.jpaboard.repository.BoardRepository;
import com.ebaykorea.gkhim.jpaboard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardServiceImpl implements BoardService {
  @Autowired
  BoardRepository boardRepository;

  @Autowired
  MemberRepository memberRepository;

  @Override
  @Transactional
  public Board addBoard(String email, Board board) {
    Member member = memberRepository.findByEmail(email);
    board.setMember(member);
    boardRepository.save(board);
    return null;
  }

  @Override
  @Transactional(readOnly = true)
  // default readOnly = false이기 때문에 true로 설정
  // false인 경우 쿼리 후 commit을 호출하는데,
  // 읽기 작업만 하더라도 commit이 일어나게 되어 성능이 하락한다.
  public Page<Board> getBoards(int page) {
    PageRequest pageRequest = PageRequest.of(page - 1, 3);
    Page<Board> boardPage = boardRepository.findAllByOrderByIdDesc(pageRequest);
    return boardPage;
  }
}
