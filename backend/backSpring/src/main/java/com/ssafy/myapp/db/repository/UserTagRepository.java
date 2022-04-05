package com.ssafy.myapp.db.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.entity.UserTag;

@Repository
public interface UserTagRepository extends JpaRepository<UserTag, Long>{
	List<UserTag> findTop3ByUserIdOrderByWeightDesc(Long userId);
	UserTag findByUserAndTag(User user, String tag);
}

