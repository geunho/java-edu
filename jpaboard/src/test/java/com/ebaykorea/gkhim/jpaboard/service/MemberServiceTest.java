package com.ebaykorea.gkhim.jpaboard.service;

import com.ebaykorea.gkhim.jpaboard.domain.Member;
import com.ebaykorea.gkhim.jpaboard.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {
  @Autowired
  MemberService memberService;

  @Test
  public void addMemberTest() {
    Member member = new Member();
    member.setId(1L);
    member.setEmail("abc@google.com");
    member.setName("KIM");
    member.setPasswd("Passw@rd!");
    member.setRegdate(LocalDateTime.now());
    Member m = memberService.addMember(member);
    System.out.println("member id = " + m.getId());
  }

  @Test
  public void getMemberByEmailTest() {
    Member member = memberService.getMember("abc@google.com");
    System.out.println(member.getId());
    System.out.println(member.getName());
    System.out.println(member.getMemberRoles());
  }
}
