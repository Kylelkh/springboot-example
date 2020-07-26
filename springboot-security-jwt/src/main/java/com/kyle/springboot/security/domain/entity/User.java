package com.kyle.springboot.security.domain.entity;

import com.kyle.springboot.security.domain.enums.UserRoleEnum;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Document
public class User implements UserDetails {
  @Id private String id;
  @Indexed private String username;
  private String password;
  private String mobile;
  private String fullName;
  private List<UserRoleEnum> roles;
  private boolean locked;
  @CreatedDate private Date createdDate;
  @LastModifiedDate private Date lastModifiedDate;

  @Transient
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return roles.stream()
        .map(userRoleEnum -> new SimpleGrantedAuthority(userRoleEnum.getRole()))
        .collect(Collectors.toList());
  }

  @Override
  @Transient
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  @Transient
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  @Transient
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  @Transient
  public boolean isEnabled() {
    return true;
  }
}
