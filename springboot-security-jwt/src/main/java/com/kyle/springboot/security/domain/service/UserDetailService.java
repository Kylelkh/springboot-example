package com.kyle.springboot.security.domain.service;

import com.kyle.springboot.security.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailService implements UserDetailsService {

  @Autowired UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserDetails user = userRepository.findByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("User not found...");
    }
    return user;
  }
}
