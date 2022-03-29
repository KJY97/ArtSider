package com.ssafy.myapp.db.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.entity.Viewed;
import com.ssafy.myapp.db.mapping.ShowMapping;


public interface ViewedRepository extends JpaRepository<Viewed, Long>{
	List<ShowMapping> findByUser(User user);
}
