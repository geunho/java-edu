package com.ebaykorea.gkhim.jpaboard.security;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

// Spring의 버전이 올라가면서 @ModelAttribute 를 붙이지 않더라도 URL 쿼리파라미터를 메서드 파라미터로
// 자동으로 덮어씌우는 버그가 생길 수 있음
// 요런 상황을 막기 위해 사용자 정의 annotation을 정의해서 사용하자
public class MemberLoginInfoArgumentResolver implements HandlerMethodArgumentResolver {
  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    // methodParameter의 정보를 이용, 타입을 체크
    // MemberLoginInfo ? true : false

    AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
    if(authUser != null
        && parameter.getParameterType() == MemberLoginInfo.class) return true;

    return false;
  }

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    // 로그인을 했다면 SecurityContextHolder를 이용, 결과를 얻어서 반환
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    Object loginInfo = auth.getPrincipal(); // MemberLoginInfo 형식
    if (loginInfo == null || loginInfo.getClass() == String.class) return WebArgumentResolver.UNRESOLVED;
    // loginInfo.getClass() == String.class 로 잘못 User 개체가 들어가는 경우가 있기 때문에 예외처리 해준다.

    // 아래 내용은 Intercepter 클래스에서 해주는 것이 더 좋을듯 하다.
    HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
    request.setAttribute("memberLoginInfo", loginInfo);

    return loginInfo;
  }
}
