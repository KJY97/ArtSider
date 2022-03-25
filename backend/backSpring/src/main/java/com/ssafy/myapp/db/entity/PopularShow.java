package com.ssafy.myapp.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "popular_show")
public class PopularShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    // pk

    @Column(nullable = false)
    private Integer rank;    // 판매 순위

    @Column(nullable = false)
    private String showId;    // 공연 아이디(인터파크 기준)

    @Column(nullable = false)
    private String sellRate;    // 판매점유율
}
