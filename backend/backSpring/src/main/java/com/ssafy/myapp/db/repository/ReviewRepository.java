package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r INNER JOIN r.show s WHERE s.id = :id")
    Page<Review> findByPerformanceId(@Param("id") Long id, Pageable pageable);
}
