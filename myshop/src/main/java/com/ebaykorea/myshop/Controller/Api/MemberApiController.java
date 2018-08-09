package com.ebaykorea.myshop.Controller.Api;

import com.ebaykorea.myshop.Security.MemberInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
public class MemberApiController {
    // 이 파라미터에 자동으로 값을 넣어 주는걸 Argument Resolver
    @GetMapping
    public String members(MemberInfo memberInfo) {
        // memberInfo에 id = 1, email = 이메일주소를 세팅에서 넣어주도록 하고 싶다.
        System.out.println("member!!!!!");
        System.out.println("member :" + memberInfo);
        return "members";
    }
}
