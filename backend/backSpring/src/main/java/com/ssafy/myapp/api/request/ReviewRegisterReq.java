package com.ssafy.myapp.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReviewRegisterReq {
    Long userId;
    String title;
    String contents;
    int rating;
}
