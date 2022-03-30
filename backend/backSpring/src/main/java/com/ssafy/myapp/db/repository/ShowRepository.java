package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.mapping.ShowListMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
    ShowListMapping findByShowId(String showId);
    List<ShowListMapping> findByShowNameContaining(String keyword);
    List<ShowListMapping> findAllBy();
}
