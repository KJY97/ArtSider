package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query(value = "select r from Review r ")
    Page<Review> findAllByPerformanceId(Long id, Pageable pageable);
}
