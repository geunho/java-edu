package com.ebaykorea.myshop.Config;

import com.ebaykorea.myshop.Security.MemberInfoArgumentResolver;
import com.ebaykorea.myshop.Utils.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// java config 클래스는 해당 애노테이션이 붙어 있어야 한다.
// interceptor 정의를 위해서는 아래거 override
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 사용자가 작성한 인터셉터를 추가한다.
        registry.addInterceptor(new LogInterceptor());
    }

    // ArgumentResolver를 추가한다.
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new MemberInfoArgumentResolver());
    }
}
