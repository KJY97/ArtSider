package com.ssafy.myapp.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "show_detail_img")
public class ShowDetailImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String showId;    // 인터파크 기준 공연 id => 출연진, 주의사항 이미지, 상세정보 이미지 찾기 위함

    @Column(length = 500, nullable = false)
    private String imgUrl;
}
