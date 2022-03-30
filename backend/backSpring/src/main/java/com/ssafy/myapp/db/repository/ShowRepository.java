package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    Show findByShowId(String showId);
    List<Show> findByShowNameContaining(String keyword);
}
