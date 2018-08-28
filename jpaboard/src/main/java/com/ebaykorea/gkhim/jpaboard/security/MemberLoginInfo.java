package com.ebaykorea.gkhim.jpaboard.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class MemberLoginInfo extends User {
  private Long id;
  private String name;

  public MemberLoginInfo(String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
  }

  public Long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public void setId(Long userId) {
    this.id = userId;
  }

  public void setName(String userDisplayName) {
    this.name = userDisplayName;
  }
}
