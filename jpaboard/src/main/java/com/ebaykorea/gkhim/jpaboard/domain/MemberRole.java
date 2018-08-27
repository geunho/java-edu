package com.ebaykorea.gkhim.jpaboard.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "member_role")
@Getter
@Setter
public class MemberRole {
  @Id
  @GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;

  // MemberRole이 영속화될 때, Member도 영속화 시킨다. cascade = CascadeType.ALL
  @JsonBackReference
  @ManyToOne(targetEntity = Member.class, fetch = FetchType.LAZY)
  @JoinColumn(name="member_id")
  private Member member;

  private String name;

  // helper
  public void setMember(Member member){
    this.member = member;
    if(!member.getMemberRoles().contains(this)){
      member.getMemberRoles().add(this);
    }
  }
}
