package com.ssafy.myapp.db.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.ShowTag;

@Repository
public interface ShowTagRepository extends JpaRepository<ShowTag, Long> {
	@Query(value = "SELECT new map(count(t) as cnt,t.tagContent as tag) "
			+ "FROM ShowTag t INNER JOIN t.show s "
			+ "WHERE s.id IN (SELECT subS.id FROM Favorite f INNER JOIN f.user subU INNER JOIN f.show subS WHERE subU.id=:id) "
			+ "GROUP BY t.tagContent")
    List<?> findFavoriteShowTagCnt(@Param("id") Long userId);

	List<ShowTag> findByShowId(Long showId);
}