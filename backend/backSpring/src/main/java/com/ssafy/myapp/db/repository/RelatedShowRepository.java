package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.RelatedShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RelatedShowRepository extends JpaRepository<RelatedShow, Long> {
    List<RelatedShow> findByShowId(Long showId);
}
