package com.ssafy.myapp.db.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id @GeneratedValue
    @Column(name = "USER_ID")
    private Long id;

    private String email;
    private String password;
    private String nickname;
}
