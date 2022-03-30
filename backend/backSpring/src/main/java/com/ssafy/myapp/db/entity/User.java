package com.ssafy.myapp.db.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;

import javax.persistence.*;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String email;
    private String password;
    private String nickname;
    private String telNum;

    private String profileImg;
    
    @OneToMany(mappedBy = "user")//뒤의 fetch 안했을 시에 에러나서 추가해줌
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference 
    private List<Favorite> favorite = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")//뒤의 fetch 안했을 시에 에러나서 추가해줌
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference 
    private List<Viewed> viewed = new ArrayList<>();
    
    @OneToMany(mappedBy = "user")//뒤의 fetch 안했을 시에 에러나서 추가해줌
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference //무한 호출 방지
    private List<UserTag> userTag = new ArrayList<>();

 
    private LocalDateTime createDate;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Expectation> expectations = new ArrayList<>();

}
