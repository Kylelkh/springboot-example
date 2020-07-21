package com.kyle.springboot.security.domain.service;

import com.kyle.springboot.security.domain.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserSevice {
  UserDetails register(UserDto userDto);

  List<String> getPermissionList(String userId);

  String login(String username, String password);
}
