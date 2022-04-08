package com.ssafy.myapp.api.response;

import com.ssafy.myapp.db.entity.Review;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class ReviewListGetRes {
    private Long reviewId;
    private Long userId;
    private String userName;
    private String title;
    private String contents;
    private int rating;
    private LocalDateTime createdDate;

    public ReviewListGetRes(Review review) {
        this.reviewId = review.getId();
        this.userId = review.getUser().getId();
        this.userName = review.getUser().getNickname();
        this.title = review.getTitle();
        this.contents = review.getContents();
        this.rating = review.getRating();
        this.createdDate = review.getCreateDate();
    }
}
