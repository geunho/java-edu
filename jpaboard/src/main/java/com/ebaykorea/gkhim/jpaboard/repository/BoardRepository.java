package com.ebaykorea.gkhim.jpaboard.repository;

import com.ebaykorea.gkhim.jpaboard.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
  Page<Board> findAllBy(Pageable pageable);
  Page<Board> findAllByOrderByIdDesc(Pageable pageable);
  Page<Board> findAllByTitleContains(String title, Pageable pageable);
  Page<Board> findAllByContentContains(String content, Pageable pageable);
}
