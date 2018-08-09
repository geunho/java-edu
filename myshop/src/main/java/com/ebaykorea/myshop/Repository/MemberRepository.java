package com.ebaykorea.myshop.Repository;

import com.ebaykorea.myshop.Domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Long countByNameContains(String name);

    @Query("SELECT COUNT(m) FROM Member m WHERE m.name = :name") // JPQL
    Long countAllBtName(@Param("name") String name);
}
