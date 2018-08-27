package com.ebaykorea.gkhim.jpaboard.service;

import com.ebaykorea.gkhim.jpaboard.domain.Member;
import com.ebaykorea.gkhim.jpaboard.domain.MemberRole;
import com.ebaykorea.gkhim.jpaboard.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {
  @Autowired
  MemberRepository memberRepository;

  @Override
  @Transactional
  public Member addMember(Member member) {
    // member의 암호를 암호화 -> Service or Controller에서 암호화 할지 결정
    PasswordEncoder passwordEncoder =
        PasswordEncoderFactories
            .createDelegatingPasswordEncoder();

    String encode = passwordEncoder.encode(member.getPasswd());
    member.setPasswd(encode);

    System.out.println("encode: " + encode);

    // MemberRole("USER")을 Member에 추가
    MemberRole memberRole = new MemberRole();
    memberRole.setName("USER");
    member.addMemberRole(memberRole);

    // Member를 저장
    member = memberRepository.save(member);

    // Member ID를 return
    return member;
  }

  @Override
  @Transactional(readOnly = true)
  public Member getMember(String email) {
    return memberRepository.findByEmail(email);
  }
}
