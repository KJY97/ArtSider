package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.PopularShow;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PopularShowRepository extends JpaRepository<PopularShow,Long> {
}
