package com.ebaykorea.gkhim.jpaboard.controller;

import com.ebaykorea.gkhim.jpaboard.domain.Board;
import com.ebaykorea.gkhim.jpaboard.security.AuthUser;
import com.ebaykorea.gkhim.jpaboard.security.MemberLoginInfo;
import com.ebaykorea.gkhim.jpaboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;

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
  // 로그인 사용자를 보여주면 좋을 듯 함 -> Principal 개체를 첫 번째 파라미터로 설정
  // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
  // Object p = auth.getPrincipal();
  // User user = (User)p; // 로 형변환 가능
  @GetMapping("/writeform")
  public String boards(@AuthUser MemberLoginInfo loginInfo/*, ModelMap modelMap*/) {
    // modelMap에 설정할 수 있다. -> MemberLoginInfoArgumentResolver의 resolve 메서드 내에 설정한 코드 확인
    //    modelMap.addAttribute("name", loginInfo.getName());

//    String email = principal.getName();
    // 사용자 이름을 얻을때 UserRepository를 호출하는것은 너무 자주 쓰이는 정보이기 때문에 비효율적
//    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//    MemberLoginInfo p = (MemberLoginInfo)auth.getPrincipal();
    // 요 방식은 코드가 기니.. Principal 처럼 파라미터로 받을 방법은?
    // -> ArgumentResolver로 가능

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
