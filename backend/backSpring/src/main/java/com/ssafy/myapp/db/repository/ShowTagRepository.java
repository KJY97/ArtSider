package com.ssafy.myapp.db.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
			+ "WHERE s.id IN (SELECT subS.id FROM Favorite f INNER JOIN f.user subU INNER JOIN f.show subS WHERE subU.id=:id)  "
			+ "GROUP BY t.tagContent")
    List<?> findFavoriteShowTagCnt(@Param("id") Long userId);

	List<ShowTag> findByShowId(Long showId);
	
	@Query("SELECT new map(t.tagContent as tag) "
			+ "FROM ShowTag t "
			+ "WHERE t.show.id=:id "
			+ "ORDER BY t.weight DESC")
	List<?> findByShowIdLimit3Pageable(@Param("id") Long showId,Pageable pageable);
	default List<?> findByShowIdLimit3(Long showId)
    {
        return findByShowIdLimit3Pageable(showId,PageRequest.of(0, 3));
    }
	
	
	@Query("SELECT new map(s.id as id, s.posterPath as posterPath,s.showName as showName) "
			+ "FROM ShowTag t INNER JOIN t.show s "
			+ "GROUP BY s.id HAVING count(t)>3"
			+ "ORDER BY rand() DESC")
	List<?> findRandomShowExistTagPageable(Pageable pageable);
	
	default List<?> findRandomShowExistTag()
    {
        return findRandomShowExistTagPageable(PageRequest.of(0, 6));
    }
}