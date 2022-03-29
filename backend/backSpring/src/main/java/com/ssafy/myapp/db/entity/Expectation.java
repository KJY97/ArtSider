package com.ssafy.myapp.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.myapp.api.request.ReviewRegisterReq;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter @Getter
@ToString
public class Expectation {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Expect_Id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

//    @Column(columnDefinition = "TEXT", length = 500, nullable = false)
    private String contents;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "User_Id")
    @JsonIgnore // 순환참조 방지
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "show_id")
    @JsonIgnore // 순환참조 방지
    private Show show;

    // === 연관관계 메소드 ===
    public void setUser(User user) {
        if (this.user != null) { // 기존에 이미 유저가 존재한다면
            this.user.getExpectations().remove(this); // 관계를 끊는다.
        }
        this.user = user;
        user.getExpectations().add(this);
    }

    public void setShow(Show show) {
        if (this.show != null) { // 기존에 이미 공연이 존재한다면
            this.show.getExpectations().remove(this); // 관계를 끊는다.
        }
        this.show = show;
        show.getExpectations().add(this);
    }

    // ===생성 메서드===
    public static Expectation createExpect(User user, Show show, ReviewRegisterReq reviewInfo) {
        Expectation expectation = new Expectation();
        expectation.setUser(user);
        expectation.setShow(show);
        expectation.setTitle(reviewInfo.getTitle());
        expectation.setContents(reviewInfo.getContents());
        expectation.setCreateDate(LocalDateTime.now());
        return expectation;
    }

}
