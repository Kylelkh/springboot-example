package com.kyle.springboot.security.domain.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class User {
  @Id private String id;
  @Indexed
  private String username;
  private String password;
  private String mobile;
  private String fullName;
  private String role;
  private boolean locked;
  @CreatedDate private Date createdDate;
  @LastModifiedDate private Date lastModifiedDate;
}
