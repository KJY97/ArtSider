package com.ssafy.myapp.api.response;

import com.ssafy.myapp.db.entity.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class PopularShowListGetRes {

    private Long id;
    private Integer rank;
    private String showId;
    private String sellRate;
    private Show show;    // 공연정보



    public PopularShowListGetRes(PopularShow entity) {
        this.id = entity.getId();
        this.rank = entity.getRank();
        this.sellRate = entity.getSellRate();
        this.showId = entity.getShowId();
    }
}
