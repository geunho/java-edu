package com.ebaykorea.gkhim.jpaboard.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebApplicationSecurity extends WebSecurityConfigurerAdapter {
  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .requestMatchers(PathRequest.toStaticResources()
            .atCommonLocations())
        .requestMatchers(
            new AntPathRequestMatcher("/**.html"));

  }

  // 특정 ROLE을 가진 사용자가 접근할 수 있는 경로를 지정
  // logout경로를 지정한다던지 대부분의 인증 처리에 대한 설정
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .httpBasic()
        .and()

        .authorizeRequests()
        .and()

        .logout() // start - logout 설정 (logout을 할 수 있는 개체를 반환)
          .logoutRequestMatcher(
              new AntPathRequestMatcher("/logout"))
          .logoutSuccessUrl("/boards")
        .and() // end - logout 설정 (다시 HttpSecurity 가체 반환, chaining 기법)

        .authorizeRequests() // 인가 설정
          .antMatchers("/").permitAll()
          .antMatchers("/members/joinform").permitAll()
          .antMatchers(HttpMethod.POST,
              "/members/join").permitAll()
          .antMatchers("/members/welcome").permitAll()
          .antMatchers("/members/login").permitAll()
          .antMatchers("/members/**").hasRole("USER") // 권한 설정 순서를 눈여겨 본다. 좁은 범위로 시작해서 큰 범위 설정을 해야함.
          .antMatchers(HttpMethod.GET,"/boards").permitAll()
          .antMatchers(HttpMethod.POST,"/boards").hasRole("USER")
          .antMatchers("/boards/**").hasRole("USER")
          .antMatchers("/api/**").hasRole("USER")
          .antMatchers("/h2-console/**").permitAll()
          .anyRequest().fullyAuthenticated()
        .and()

        .headers().frameOptions().disable()
        .and()

        .csrf().ignoringAntMatchers("/**")// post방식으로 값을 전달할 때 csrf를 무시 (개발시 불편하므로 무시. production 환경에서는 무시해서는 안됨
        .and()

        .formLogin() // 사용자 정의 로그인 화면
          .loginPage("/members/login") // 로그인 페이지 URL (사용자 정의 경로)
          .loginProcessingUrl("/members/login") // post 요청이 날아가는 요청 URL (사용자 정의 경로가 아님)
          .usernameParameter("id") // form의 사용자명 name 필드 이름
          .passwordParameter("password"); // form의 패스워드 name 필드 이름
  }
}
