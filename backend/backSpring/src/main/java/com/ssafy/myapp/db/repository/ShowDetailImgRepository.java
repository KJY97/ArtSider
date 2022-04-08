package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.ShowDetailImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowDetailImgRepository extends JpaRepository<ShowDetailImg, Long> {
    List<ShowDetailImg> findByShowId(String showId);
}
