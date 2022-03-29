package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.NoticeImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeImgRepository extends JpaRepository<NoticeImg, Long> {
    List<NoticeImg> findByShowId(String showId);
}
