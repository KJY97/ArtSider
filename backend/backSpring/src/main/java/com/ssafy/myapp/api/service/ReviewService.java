package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.db.entity.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ReviewService {
    List<Review> findReview(Long id, Pageable pageable);
    Long addReview(Long id, ReviewRegisterReq reviewInfo);
    boolean modifyReview(Long reviewId, ReviewRegisterReq reviewInfo);
    boolean removeReview(Long reviewId);
}
