package com.geunho.webmark.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Authority {
  private Long categoryId;
  private Long userId;
  private Role role;
}