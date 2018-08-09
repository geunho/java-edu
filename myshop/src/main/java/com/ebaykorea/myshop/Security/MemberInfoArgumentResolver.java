package com.ebaykorea.myshop.Security;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

// AR은 아래 메소드 2개가 짝으로 이루어진다.
// Controller method(여러 개의 Argument) > 3개 있으면 supportsParameter가 3번 호출되고 운하는 타입인지 검사함.
// true가 리턴되면 현재 파라미터 타입이 해당 메소드인 것.
public class MemberInfoArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        // class 정보는 메모리에 한 번만 올라가니 ==로 비교 가능
        if (parameter.getParameterType() == MemberInfo.class) {
            return true;
        }

        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        MemberInfo memberInfo = new MemberInfo(5L, "hyubae@ebay.com");

        return memberInfo;
    }
}