package com.ebaykorea.myshop.Controller;

import com.ebaykorea.myshop.Domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

// 화면을 보여주는 것
@Controller
@RequestMapping("/members")
public class MemberController {
	@GetMapping
	public String members(ModelMap modelMap) {
		// count라는 이름으로 500이 전달
		modelMap.addAttribute("count", 500);
		List<Member> list = new ArrayList<>();
		//list.add(new Board);
		//modelMap.addAllAttribute()
		// 이런 경우 tamplate에서는  <tr th:each="board : ${list}"> <td th:text="${board.name}"> 으로 접근
		return "members"; // view name을 리턴한다.
		//template engine이 필요하다.
		// thymeleaf을 쓰면 view name에 해당하는 정보를 resources - templates에서 본다
		// new-file로 추가하기.
		// xml 주소 꼭 지켜야 하니 복사하자.
	}
}
