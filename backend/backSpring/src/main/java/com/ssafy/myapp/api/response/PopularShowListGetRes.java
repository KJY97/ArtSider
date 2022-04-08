package com.ssafy.myapp.api.response;

import com.ssafy.myapp.db.entity.*;
import com.ssafy.myapp.db.mapping.ShowListMapping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PopularShowListGetRes {

    private Long id;    // 주의: 공연(show)의 db 에서의 pk 값, PopularShow 테이블의 pk 값이 아니다!!!
    private Integer rank;
    private String showId;
    private String sellRate;
    private String showName;    // 공연이름
    private String startDate;    // 공연시작일
    private String endDate;    // 공연 종료일
    private String posterPath;    // 포스터 경로
    private String category;    // 공연 카테고리
    private String artCenterName;    // 공연장



    public PopularShowListGetRes(PopularShow entity) {
        this.rank = entity.getRank();
        this.sellRate = entity.getSellRate();
        this.showId = entity.getShowId();
    }
}
