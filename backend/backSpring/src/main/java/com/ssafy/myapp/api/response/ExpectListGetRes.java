package com.ssafy.myapp.api.response;

import com.ssafy.myapp.db.entity.Expectation;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ExpectListGetRes {
    private Long expectationId;
    private Long userId;
    private String userName;
    private String title;
    private String contents;
    private LocalDateTime createDate;

    public ExpectListGetRes(Expectation expectation) {
        this.expectationId = expectation.getId();
        this.userId = expectation.getUser().getId();
        this.userName = expectation.getUser().getNickname();
        this.title = expectation.getTitle();
        this.contents = expectation.getContents();
        this.createDate = expectation.getCreateDate();
    }
}
