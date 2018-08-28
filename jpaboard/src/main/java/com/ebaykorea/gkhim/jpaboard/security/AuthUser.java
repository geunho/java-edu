package com.ebaykorea.gkhim.jpaboard.security;

import java.lang.annotation.*;

@Target(ElementType.PARAMETER) // 파라미터에 사용
@Retention(RetentionPolicy.RUNTIME) // 런타임에 검사
@Documented
public @interface AuthUser {
}
