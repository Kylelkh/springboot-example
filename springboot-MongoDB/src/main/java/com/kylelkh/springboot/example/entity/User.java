package com.kylelkh.springboot.example.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
public class User {
    private String userName;
    private String password;
    private boolean active;
    @CreatedDate
    private Date createDate;
    @LastModifiedDate
    private Date lastModifiedDate;
}
