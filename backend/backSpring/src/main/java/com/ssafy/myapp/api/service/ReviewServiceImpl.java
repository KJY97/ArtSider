package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.api.response.ReviewListGetRes;
import com.ssafy.myapp.db.entity.Review;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.repository.ReviewRepository;
import com.ssafy.myapp.db.repository.ShowRepository;
import com.ssafy.myapp.db.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.stream.Collectors;

@Service("reviewService")
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;

    // 조회
    @Override
    public Page<ReviewListGetRes> findReview(Long id, Pageable pageable) {
        Page<Review> page = reviewRepository.findByPerformanceId(id, pageable);
        // stream API를 활용하여 ReviewDto 형태의 List로 맵핑
        return new PageImpl<ReviewListGetRes>(page.getContent().stream().map(ReviewListGetRes::new).collect(Collectors.toList()), pageable, page.getTotalElements());
    }

    @Override
    @Transactional
    public Long addReview(Long id, ReviewRegisterReq reviewInfo) {
        // 엔티티 조회
        Long userId = reviewInfo.getUserId();
        User findUser = userRepository.findById(userId).get();

        Show findShow = showRepository.findById(id).get();

        // 리뷰 생성
        Review review = Review.createReview(findUser, findShow, reviewInfo);
        reviewRepository.save(review);

        return review.getId();
    }

    @Override
    @Transactional
    public boolean modifyReview(Long reviewId, ReviewRegisterReq reviewInfo) {
        Optional<Review> oReview = reviewRepository.findById(reviewId);

        if(!oReview.isPresent()) return false;

        Review review = oReview.get();

        review.setTitle(reviewInfo.getTitle());
        review.setContents(reviewInfo.getContents());
        review.setRating(reviewInfo.getRating());

        reviewRepository.save(review);
        return true;
    }

    @Override
    @Transactional
    public boolean removeReview(Long reviewId) {
        Optional<Review> oReview = reviewRepository.findById(reviewId);
        if(oReview.isPresent()) {
            reviewRepository.delete(oReview.get());
            return true;
        }
        return false;
    }
}
