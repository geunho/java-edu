package com.ebaykorea.gkhim.jpaboard.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "member")
@Getter
@Setter
public class Member implements Serializable {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  private String name;
  private String email;
  private String passwd;

  // 만약 camel 표기법으로 되어 있다면,
  // Spring boot에서 자동으로 _ (underbar)로 바꿔준다.
  @Column(name="reg_date")
  private LocalDateTime regdate;

  // Member가 영속성을 가질때 memberRoles도 영속성을 가지도록 한다.
  @JsonManagedReference
  @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private Set<MemberRole> memberRoles;
  /*
Hibernate: select member0_.id as id1_1_1_, member0_.email as email2_1_1_, member0_.name as name3_1_1_, member0_.passwd as passwd4_1_1_, member0_.reg_date as reg_date5_1_1_, memberrole1_.member_id as member_i1_2_3_, memberrole2_.id as member_r2_2_3_, memberrole2_.id as id1_3_0_, memberrole2_.member_id as member_i3_3_0_, memberrole2_.name as name2_3_0_ from member member0_ left outer join member_member_roles memberrole1_ on member0_.id=memberrole1_.member_id left outer join member_role memberrole2_ on memberrole1_.member_roles_id=memberrole2_.id where member0_.id=?
Hibernate: insert into member_role (id, member_id, name) values (null, ?, ?)
Hibernate: update member set email=?, name=?, passwd=?, reg_date=? where id=?
Hibernate: insert into member_member_roles (member_id, member_roles_id) values (?, ?)
===>  mappedBy 항목이 없으면 다대다 관계로 인식해서 member_member_roles 라는 테이블을 하나 더 생성하게 된다.
제대로 설정한 후 테스트 하면,
Hibernate: select member0_.id as id1_1_1_, member0_.email as email2_1_1_, member0_.name as name3_1_1_, member0_.passwd as passwd4_1_1_, member0_.reg_date as reg_date5_1_1_, memberrole1_.member_id as member_i3_2_3_, memberrole1_.id as id1_2_3_, memberrole1_.id as id1_2_0_, memberrole1_.member_id as member_i3_2_0_, memberrole1_.name as name2_2_0_ from member member0_ left outer join member_role memberrole1_ on member0_.id=memberrole1_.member_id where member0_.id=?
Hibernate: insert into member_role (id, member_id, name) values (null, ?, ?)
Hibernate: update member set email=?, name=?, passwd=?, reg_date=? where id=?
이렇게 한번의 insert만 일어난다.
   */

  // helper method
  public void addMemberRole(MemberRole memberRole){
    if (memberRoles == null) {
      memberRoles = new HashSet<MemberRole>();
    }
    // 상호 참조 관계이기 때문에 role 추가시 role.member를 설정해야 한다.ㄴ
    memberRoles.add(memberRole);
    if(memberRole.getMember() != this){
      memberRole.setMember(this);
    }
  }
}