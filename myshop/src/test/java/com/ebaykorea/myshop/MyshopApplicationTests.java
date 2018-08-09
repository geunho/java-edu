package com.ebaykorea.myshop;

import com.ebaykorea.myshop.Domain.Member;
import com.ebaykorea.myshop.Repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MyshopApplicationTests {
	@Autowired // member repository 객체의 bean이 있다면 자동으로 할당된다.
	MemberRepository memberRepository;

	@Test
	public void contextLoads() {
	} // 빈 테스트 메서드. Spring 환경 설정이 제대로 되어있는지 확인, 만약 잘 못 됐다면 예외와 함꼐 실패할 것이다.

	@Test
    public void testNotNull() {
	    Assert.assertNotNull(memberRepository);
    }

    @Test
    public void testSaveMember() {
	    Member member = new Member();
        member.setName("kim");
        member.setEmail("gkhim@ebay.com");
        member.setPassword("1234");

        memberRepository.save(member);
        System.out.println(member);

        Member member2 = memberRepository.getOne(member.getId());

        if (member == member2) {
            System.out.println("member == member2");
        } else {
            System.out.println("member != member2");
        }
    }
}
