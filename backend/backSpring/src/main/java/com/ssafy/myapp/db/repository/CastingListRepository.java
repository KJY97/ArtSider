package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.CastingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CastingListRepository extends JpaRepository<CastingList, Long> {
    List<CastingList> findByShowId(String showId);
}
