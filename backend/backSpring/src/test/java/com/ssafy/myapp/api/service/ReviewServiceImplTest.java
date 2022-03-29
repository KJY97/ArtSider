package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.db.entity.Review;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.repository.ReviewRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@Transactional
class ReviewServiceImplTest {

    @Autowired EntityManager em;
    @Autowired ReviewService reviewService;
    @Autowired ReviewRepository reviewRepository;

    @Test
    public void 리뷰작성() throws Exception {
        //given
        User user = new User();
        user.setEmail("test@artsider.com");
        user.setNickname("test");
        user.setPassword("test");
        em.persist(user);

        ReviewRegisterReq reviewInfo = new ReviewRegisterReq();
        reviewInfo.setTitle("제목");
        reviewInfo.setContents("내용");
        reviewInfo.setRating(5);
        reviewInfo.setUserId(user.getId());

        Show show = new Show();
        show.setShowName("레베카");
        em.persist(show);

        //when
        Long reviewId = reviewService.addReview(show.getId(), reviewInfo);

        //then
        Review getReview = reviewRepository.findById(reviewId).get();

        assertEquals("리뷰 작성자는 test이다.", "test", getReview.getUser().getNickname());
        assertEquals("공연의 이름은 레베카이다", "레베카", getReview.getShow().getShowName());
    }

    @Test
    public void 리뷰수정() throws Exception {
        Optional<Review> oReview = reviewRepository.findById(1L);
        // 값이 존재한다면
        if(oReview.isPresent()) {
            Review beforeReview = oReview.get();
            String beforeTitle = beforeReview.getTitle();
            String beforeContents = beforeReview.getContents();
            int beforeRating = beforeReview.getRating();

            beforeReview.setTitle("제목2");
            beforeReview.setContents("내용2");
            beforeReview.setRating(10);

            Review afterReview = reviewRepository.save(beforeReview);
            String afterTitle = afterReview.getTitle();
            String afterContents = afterReview.getContents();
            int afterRating = afterReview.getRating();

            assertEquals("제목이 변경되었습니다.", true, !beforeTitle.equals(afterTitle));
            assertEquals("내용이 변경되었습니다.", true, !beforeContents.equals(afterContents));
            assertEquals("평점이 변경되었습니다.", true, beforeRating != afterRating);
        }
    }

    @Test
    public void 리뷰삭제() throws Exception {
        Optional<Review> oReview = reviewRepository.findById(1L);
        if(oReview.isPresent()) {
            boolean flag = reviewService.removeReview(1L);
            assertEquals("삭제되었습니다.", true, flag);
        }
    }
}