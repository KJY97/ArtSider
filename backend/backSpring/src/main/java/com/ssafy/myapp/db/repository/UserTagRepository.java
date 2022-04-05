package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.entity.UserTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTagRepository extends JpaRepository<UserTag, Long> {
    UserTag findByUserAndTag(User user, String tag);
}
