package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.api.response.ReviewListGetRes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ReviewService {
    Page<ReviewListGetRes> findReview(Long id, Pageable pageable);
    Long addReview(Long id, ReviewRegisterReq reviewInfo);
    boolean modifyReview(Long reviewId, ReviewRegisterReq reviewInfo);
    boolean removeReview(Long reviewId);
}
