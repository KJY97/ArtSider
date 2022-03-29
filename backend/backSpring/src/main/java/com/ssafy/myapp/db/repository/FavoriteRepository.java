package com.ssafy.myapp.db.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ssafy.myapp.db.entity.Favorite;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.mapping.ShowMapping;


public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
	List<ShowMapping> findByUser(User user);
	Favorite findTop1ByUserAndShow(User user, Show show);
}
