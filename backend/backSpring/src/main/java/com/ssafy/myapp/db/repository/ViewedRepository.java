package com.ssafy.myapp.db.repository;


import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.entity.Viewed;
import com.ssafy.myapp.db.mapping.ShowMapping;


public interface ViewedRepository extends JpaRepository<Viewed, Long>{
	List<ShowMapping> findByUser(User user);
	
	@Query("SELECT new map(s.id as id,s.showName as showName,s.posterPath as posterPath) FROM Viewed v INNER JOIN v.user u INNER JOIN v.show s  WHERE u.id = :id GROUP BY s.id ORDER BY MAX(v.id) DESC")
	List<?> findByUserIdDistinctOderByIdPageable(@Param("id") Long id, Pageable pageable);
	
	default List<?> findByUserIdDistinctOderById(Long id)
    {
        return findByUserIdDistinctOderByIdPageable(id, PageRequest.of(0, 19));
    }
}
