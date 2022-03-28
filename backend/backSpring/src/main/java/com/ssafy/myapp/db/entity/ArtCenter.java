package com.ssafy.myapp.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "art_center")
public class ArtCenter {

    // 공연장 정보는 수정하지 않기 때문에 setter 생성x

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String artCenterName;

    private String artCenterAddress;

    private String artCenterTel;

    private String artCenterWeb;

}
