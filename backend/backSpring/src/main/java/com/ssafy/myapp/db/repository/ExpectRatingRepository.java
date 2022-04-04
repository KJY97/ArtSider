package com.ssafy.myapp.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.myapp.db.entity.ExpectRating;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.mapping.ExpectRatingMapping;
import com.ssafy.myapp.db.mapping.UserReviewMapping;

public interface ExpectRatingRepository extends JpaRepository<ExpectRating, Long> {
	
	ExpectRatingMapping findByUserAndShow(User user,Show show);
	
}
