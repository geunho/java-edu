package com.ebaykorea.gkhim.jpaboard.controller;

import com.ebaykorea.gkhim.jpaboard.domain.Member;
import com.ebaykorea.gkhim.jpaboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/members")
public class MemberController {
  @Autowired
  MemberService memberService;

  // /members/joinform GET
  @GetMapping("/joinform")
  public String joinform(){
    return "members/joinform";
  }

  // /members/join POST
  @PostMapping("/join")
  public String join(@ModelAttribute Member member) {
    member.setRegdate(LocalDateTime.now());
    // 사용자가 입력한 암호를 암호화시켜서 설정
    // addMember() 메소드에서 MemberRole의 이름이 "USER" 가 기본적으로
    // 등록하고 있다.
    PasswordEncoder passwordEncoder =
        PasswordEncoderFactories
            .createDelegatingPasswordEncoder();

    String encode = passwordEncoder.encode(member.getPasswd());
    member.setPasswd(encode);

    member = memberService.addMember(member);
    System.out.println(member.getId());
    System.out.println(member.getName());

    return "redirect:/members/welcome";
  }

  // /members/welcome
  @GetMapping(path="/welcome")
  public String welcome(){
    return "members/welcome";
  }

  // /members/login GET
  // id, password 입력받는 form
  @GetMapping(path = "/login")
  public String login() {
    return "members/login";
  }

}
