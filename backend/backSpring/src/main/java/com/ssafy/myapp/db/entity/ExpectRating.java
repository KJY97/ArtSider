package com.ssafy.myapp.db.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = @Index(name="unique_user_id_show_id", columnList = "user_id, show_id", unique = true))
public class ExpectRating {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float rating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonBackReference  // 순환참조 방지
    private User user;

    // show 테이블의 기본키와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id") // 공연 테이블의 pk
    @JsonBackReference  // 순환참조 방지
    private Show show;

}
