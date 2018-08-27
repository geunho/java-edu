package com.ebaykorea.gkhim.jpaboard.service;

import com.ebaykorea.gkhim.jpaboard.domain.Member;

public interface MemberService {
  Member addMember(Member member);
  Member getMember(String email);

}
