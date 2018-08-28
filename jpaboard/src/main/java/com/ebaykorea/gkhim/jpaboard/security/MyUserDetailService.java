package com.ebaykorea.gkhim.jpaboard.security;

import com.ebaykorea.gkhim.jpaboard.domain.Member;
import com.ebaykorea.gkhim.jpaboard.domain.MemberRole;
import com.ebaykorea.gkhim.jpaboard.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


// [Open Session in View Pattern]
// @Controller -> @Service -> @Repository
// Tx가 Controller에서 시작, Controller에서 끝남
// 그런데 UserDetailService이지만, @Service 레이어에 속하지 않음
// 일반적인 @Component에서 Tx에 포함시키려면 @Transactional을 메서드에 붙여줘야 함
// Tx에 포함되지 않으면 엔티티의 lazy load가 일어나지 않아 개체를 불러오지 않는 문제가 발생함
@Component
public class MyUserDetailService implements UserDetailsService {
  @Autowired
  MemberService memberService;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    // email로 Member 엔티티 찾고,
    Member member = memberService.getMember(email);

    if(member == null){
      throw new UsernameNotFoundException("사용자를 찾지 못했습니다: " + email);
    }

    // [Lazy Load]
    // 사용자의 권한 리스트 생성해서 UserDetails 객체에 설정
    // 그냥 읽어오려고 하면 Lazy load가 되지 않음 (Tx에 속하지 않으므로)
    // 따라서 메서드에 @Transactional(readOnly = true)를 붙어줘야 함
    Set<MemberRole> memberRoles = member.getMemberRoles();

    // UserDetails를 구현하는 객체에 Member의 id, pwd를 저장
    List<GrantedAuthority> list = new ArrayList<>();
    for(MemberRole memberRole: memberRoles) {
      list.add(new SimpleGrantedAuthority(
          "ROLE_" + memberRole.getName())); // Spring Security에서 권한은 반드시 `ROLE_` prefix가 있어야 함
    }
    // User의 파생클래스를 만들어서 Member의 추가 정보를 함께 저장할 수 있다.
    // 즉, 이렇게 되면 추가로 저장한 정보를 컨트롤러에서 ArgumentViewResolver를 통해 접근할 수 있다.
    MemberLoginInfo user = new MemberLoginInfo(email, member.getPasswd(), list);
    user.setId(member.getId());
    user.setName(member.getName());
    return user;
  }
}
