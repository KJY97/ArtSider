package com.ssafy.myapp.db.repository;

import com.ssafy.myapp.db.entity.ArtCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtCenterRepository extends JpaRepository<ArtCenter, Long> {
    ArtCenter findByArtCenterName(String artCenterName);
}
