package com.ebaykorea.gkhim.jpaboard.repository;

import com.ebaykorea.gkhim.jpaboard.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
  Member findByEmail(String email);
}
