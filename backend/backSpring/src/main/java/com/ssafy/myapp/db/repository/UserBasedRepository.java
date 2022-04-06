package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.entity.UserBased;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBasedRepository extends JpaRepository<UserBased, Long> {
    List<UserBased> findByUserId(Long userId);
}
