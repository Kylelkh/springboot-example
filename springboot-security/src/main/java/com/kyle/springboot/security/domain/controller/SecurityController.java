package com.kyle.springboot.security.domain.controller;

import com.kyle.springboot.security.domain.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/security")
@Slf4j
public class SecurityController {

  @GetMapping("/user")
  @PreAuthorize("hasAnyRole('USER')")
  public ResponseEntity role() {
    String username = getUsername();
    return ResponseEntity.ok("Hi " + username + ", your have 「USER」 role.");
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity roleA() {
    String username = getUsername();
    return ResponseEntity.ok("Hi " + username + ", your have 「ADMIN」 role.");
  }

  private String getUsername() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (!authentication.isAuthenticated()) {
      return null;
    }
    Object principal = authentication.getPrincipal();
    String username = null;
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    } else {
      username = principal.toString();
    }
    return username;
  }

  @GetMapping("/login")
  public ResponseEntity login(@RequestParam String username, @RequestParam String password) {

    log.info("login...", username);
    return ResponseEntity.ok("ok");
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody UserDto userDto) {

    log.info("login..." + userDto);
    return ResponseEntity.ok("ok");
  }
}
