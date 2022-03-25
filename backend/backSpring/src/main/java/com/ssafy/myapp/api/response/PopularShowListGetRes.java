package com.ssafy.myapp.api.response;

import com.ssafy.myapp.db.entity.PopularShow;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PopularShowListGetRes {

    private Long id;
    private Integer rank;
    private String showId;
    private String sellRate;


    public PopularShowListGetRes(PopularShow entity) {
        this.id = entity.getId();
        this.rank = entity.getRank();
        this.sellRate = entity.getSellRate();
        this.showId = entity.getShowId();
    }
}
