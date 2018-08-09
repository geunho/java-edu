package com.ebaykorea.myshop.Security;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
// toString이 없으면 object의 tostring이 호출돼서 멤버 클래스 이름이랑 hashcode가 출력된다.
@ToString
public class MemberInfo {
    private Long id;
    private String email;
}
