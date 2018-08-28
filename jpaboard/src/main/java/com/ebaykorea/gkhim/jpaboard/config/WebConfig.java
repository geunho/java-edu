package com.ebaykorea.gkhim.jpaboard.config;

import com.ebaykorea.gkhim.jpaboard.security.MemberLoginInfoArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// WebMvcConfigurer 인터페이스를 구현한 클래스는
// 웹과 과년된 기능을 확장할 수 있음
// default 메서드로 이루어진 인터페이스이기 때문에
// 따로 implement할 것은 없이 사용자 정의 메서드를 override하면 된다.
@Configuration

public class WebConfig implements WebMvcConfigurer {
  // ArgumentResolver
  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new MemberLoginInfoArgumentResolver());
  }
}
