package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.Expectation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpectRepository extends JpaRepository<Expectation, Long> {
    @Query("SELECT e FROM Expectation e INNER JOIN e.show s WHERE s.id = :id")
    Page<Expectation> findById(@Param("id") Long id, Pageable pageable);
}
