package com.ssafy.myapp.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ssafy.myapp.api.request.ReviewRegisterReq;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "review_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", length = 500, nullable = false)
//    @Column(length = 500, nullable = false)
    private String contents;

    private int rating;

    @Column(nullable = false)
    private LocalDateTime createDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore // 순환참조 방지
    private User user;

    // show 테이블의 기본키와 연결
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    @JsonIgnore // 순환참조 방지
    private Show show;

    // === 연관관계 메소드 ===
    public void setUser(User user) {
        if (this.user != null) { // 기존에 이미 유저가 존재한다면
            this.user.getReviews().remove(this); // 관계를 끊는다.
        }
        this.user = user;
        user.getReviews().add(this);
    }

    public void setShow(Show show) {
        if (this.show != null) { // 기존에 이미 공연이 존재한다면
            this.show.getReviews().remove(this); // 관계를 끊는다.
        }
        this.show = show;
        show.getReviews().add(this);
    }

    // ===생성 메서드===
    public static Review createReview(User user, Show show, ReviewRegisterReq reviewInfo) {
        Review review = new Review();
        review.setUser(user);
        review.setShow(show);
        review.setTitle(reviewInfo.getTitle());
        review.setContents(reviewInfo.getContents());
        review.setRating(reviewInfo.getRating());
        review.setCreateDate(LocalDateTime.now());
        return review;
    }
}
