package com.kyle.springboot.security.domain.service;

import cn.hutool.core.bean.BeanUtil;
import com.kyle.springboot.security.domain.dto.UserDto;
import com.kyle.springboot.security.domain.entity.User;
import com.kyle.springboot.security.domain.repository.UserRepository;
import com.kyle.springboot.security.utils.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserSevice {
  @Autowired UserRepository userRepository;
  @Autowired UserDetailService userDetailService;
  @Autowired private JwtTokenUtil jwtTokenUtil;
  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails register(UserDto userDto) {
    User user = new User();
    BeanUtil.copyProperties(userDto, user);
    user.setPassword(passwordEncoder.encode(userDto.getPassword()));
    userRepository.save(user);
    return null;
  }

  @Override
  public List<String> getPermissionList(String userId) {
    User user = userRepository.findByUsername(userId);
    if (user != null) {
      return Arrays.asList(user.getRole());
    }
    return new ArrayList<>();
  }

  @Override
  public String login(String username, String password) {
    String token = null;
    try {
      UserDetails userDetails = userDetailService.loadUserByUsername(username);
      if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        throw new BadCredentialsException("密码不正确");
      }
      UsernamePasswordAuthenticationToken authentication =
          new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
      SecurityContextHolder.getContext().setAuthentication(authentication);
      token = jwtTokenUtil.generateToken(userDetails);
    } catch (AuthenticationException e) {
      log.warn("登录异常:{}", e.getMessage());
    }
    return token;
  }
}
