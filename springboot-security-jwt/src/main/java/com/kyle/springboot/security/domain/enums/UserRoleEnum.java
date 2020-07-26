package com.kyle.springboot.security.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public enum UserRoleEnum {
  ADMIN("ADMIN"),
  USER("USER");

  private String role;
}
