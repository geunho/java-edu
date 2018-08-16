package com.boot.stickershop.repository;

import com.boot.stickershop.domain.Board;
import com.boot.stickershop.domain.BoardCategory;
import com.boot.stickershop.domain.BoardFile;
import com.boot.stickershop.domain.User;
import com.boot.stickershop.repository.BoardRepository;
import com.vividsolutions.jts.util.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest // JPA에 대한 것만 테스트 하겠다는 annotation. 모든 bean을 등록하진 않으므로 실행속도 빨라짐
public class BoardRepositoryTest {
	@Autowired
	BoardRepository boardRepository; // 테스트할 대상

  @Autowired
  BoardCategoryRepository boardCategoryRepository;

  @Autowired
  UserRepository userRepository;

  BoardCategory boardCategory;
  User user;

	@Before // 각 @Test 메서드마다 매번 수행된다.
  public void initTest() {
	  boardCategory = boardCategoryRepository.getOne(1L);
	  user = userRepository.getOne(2L);

	  addBoard("테스트 게시물 1번");
	  addBoard("2번 게시물");
	  addBoard("3번 입니다.");
	  addBoard("우우웅");
  }

	@Test
	public void contextLoads() {
	}

	private void addBoard(String title) {
	  Board b = new Board();
	  b.setTitle(title);
	  b.setBoardCategory(boardCategory);
	  b.setUser(user);
	  b.setHit(0);
	  b.setRegtime(LocalDateTime.now());
	  b.setContent("테스트 게시입니다. TEST TEST TEST ");
    Board savedBoard = boardRepository.save(b);

    System.out.println(savedBoard);
  }

  @Test
  public void getBoard() {
	  Board board = boardRepository.getOne(1L);
    Board board2 = boardRepository.findBoardById(1L);

    Assert.isTrue(board == board2);

	  System.out.println(board.hashCode());
	  System.out.println(board.getTitle());
	  System.out.println(board.getContent());
  }

	@Test
	public void getBoards() {
		List<Board> boards = boardRepository.fetchBoards(); // productRepository.findAll(); 과 비교해본다. findAll()을 하는 순간 아래 중첩 for문을 돌면서 계속 쿼리가 날아간다..
//    List<Product> products = productRepository.findAll();
		printBoards(boards);
	}

	@Test
  public void getBoardByLike() {
    List<Board> boards = boardRepository.searchBoards("테스트");

    List<Board> boards2 = boardRepository.findByTitleContaining("테스트");

    printBoards(boards);
    printBoards(boards2);
  }

  private void printBoards(List<Board> boards) {
    for(Board b : boards){
      System.out.println("===============================");
      System.out.println(b.getTitle());
      System.out.println("--");
      System.out.println(b.getUser().getId());
      System.out.println("**");
      System.out.println(b);
      List<BoardFile> boardFiles = b.getBoardFiles();
      for(BoardFile bf : boardFiles){
        System.out.println(bf.getFileName());
      }
      System.out.println("===============================");
    }
  }

  @Test
  public void findById() {
    Board b = boardRepository.findBoardByIdFromQuery(1L);
    Board b2 = boardRepository.findBoardByIdFromQuery(2L);
    List<Board> boards = new ArrayList<Board>();
    boards.add(b);
    boards.add(b2);

    printBoards(boards);
  }
}
