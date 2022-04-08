package com.ssafy.myapp.api.response;

import com.ssafy.myapp.db.mapping.ShowListMapping;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ShowListGetRes {

    private Long id;    // id
    private String showName;    // 공연이름
    private String showId;    // 인터파크 기준 공연 id => 리뷰, 출연진 join 하기 위함.
    private String startDate;    // 공연시작일
    private String endDate;    // 공연 종료일
    private String posterPath;    // 포스터 경로
    private String category;    // 공연 카테고리
    private String artCenterName;    // 공연장


    public ShowListGetRes(ShowListMapping entity)
    {
        this.id = entity.getId();
        this.showName = entity.getShowName();
        this.showId = entity.getShowId();
        this.startDate = entity.getStartDate();
        this.endDate = entity.getEndDate();
        this.posterPath = entity.getPosterPath();
        this.category = entity.getCategory();
        this.artCenterName = entity.getArtCenterName();
    }
}
