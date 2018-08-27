package com.ebaykorea.gkhim.jpaboard.controller;

import com.ebaykorea.gkhim.jpaboard.domain.Member;
import com.ebaykorea.gkhim.jpaboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class TestApiController {
  @Autowired
  MemberService memberService;

  @GetMapping
  public Member getMember(){
    return memberService.getMember("abc@google.com");
  }

}
