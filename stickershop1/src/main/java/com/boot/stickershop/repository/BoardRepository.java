package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Board;
import com.boot.stickershop.repository.custom.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom {
  @Query("select b from Board b where b.id = :id")
  Board findBoardByIdFromQuery(@Param("id")Long boardId);
  Board findBoardById(Long boardId); // boardRepository.getOne(long)과 같다.
  // 위 두 메서드는 같은 역할을 한다.

  @Query("select distinct b from Board b left join fetch b.user")
  List<Board> fetchBoards();

  @Query("select b from Board b where b.title like CONCAT('%',:title,'%')")
  List<Board> searchBoards(@Param("title") String title);
  // 위 쿼리는 아래와 같이 메서드 쿼리로 작성할 수 있다. findBy필드명Containing
  List<Board> findByTitleContaining(String title);
}
