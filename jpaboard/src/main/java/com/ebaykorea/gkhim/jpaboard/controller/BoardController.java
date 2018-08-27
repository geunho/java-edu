package com.ebaykorea.gkhim.jpaboard.controller;

import com.ebaykorea.gkhim.jpaboard.domain.Board;
import com.ebaykorea.gkhim.jpaboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
@RequestMapping("/boards")
public class BoardController {
  @Autowired
  BoardService boardService;

  @GetMapping
  public String boards(
      @RequestParam(name = "page", required = false, defaultValue = "1") int page,
      ModelMap modelMap) {
    Page<Board> boardPage = boardService.getBoards(page);
    modelMap.addAttribute("pageBoard", boardPage);
    // Dispatcher Servlet -> ModelMap -> Controller -> ModelMap 설정 -> ViewResolver -> View
    return "list"; // list.html을 찾아서 던져준다
  }

  // /boards/writeform 글쓰기 폼
  // 로그인이 되어있어야 함
  // 제목, 내용만 입력하는 폼
  @GetMapping("/writeform")
  public String boards() {
    return "writeform";
  }

  // /board POST
  // 인증 정보로부터 email 입력받아 Member를 구해야 함.
  // Board는 Member를 ManyToOne 관계로 갖기 때문
  // java.security.Principal을 파라미터로 넣으면 인증 정보를 얻을 수 있다. (DispatcherService가 넣어주는 것)
  //
  public String write(Principal principal, @ModelAttribute Board board) {
    return null;
  }
}
