package com.kyle.springboot.security.domain.dto;

import lombok.Data;

@Data
public class UserDto {
  private String id;
  private String username;
  private String password;
  private String fullName;
  private String mobile;
}
