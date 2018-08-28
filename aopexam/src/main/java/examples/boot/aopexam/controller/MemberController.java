package examples.boot.aopexam.controller;

import examples.boot.aopexam.dto.Member;
import examples.boot.aopexam.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/members")
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping
    public List<Member> getMembers(){
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        System.out.println(memberService.getClass().getName());
        // 실제로 런타임에 MemberService가 아닌 MemberService의 프록시 객체의 클래스명을 반환한다.
        // examples.boot.aopexam.service.MemberServiceImpl$$EnhancerBySpringCGLIB$$f25b71e4
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        return memberService.getMembers();
    }

    @GetMapping(path="/{id}")
    public Member getMember(@PathVariable(name="id") Long id){
        return memberService.getMember(id);
    }
}
