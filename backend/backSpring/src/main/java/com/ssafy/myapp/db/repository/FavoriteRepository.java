package com.ssafy.myapp.db.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.ssafy.myapp.db.entity.Favorite;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.mapping.ShowMapping;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long>{
	List<ShowMapping> findByUser(User user);
	Optional<Favorite> findTop1ByUserAndShow(User user, Show show);
	Favorite findByUserAndShow(User user, Show show);
}
