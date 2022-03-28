package com.ssafy.myapp.api.response;

import com.ssafy.myapp.db.entity.ArtCenter;
import com.ssafy.myapp.db.entity.CastingList;
import com.ssafy.myapp.db.entity.NoticeImg;
import com.ssafy.myapp.db.entity.ShowDetailImg;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ShowDetailsGetRes {
    private Long id;    // id
    private String showName;    // 공연이름
    private String showId;    // 인터파크 기준 공연 id => 리뷰, 출연진 join 하기 위함.
    private String startDate;    // 공연시작일
    private String endDate;    // 공연 종료일
    private String openRun;    // 오픈런 여부
    private String producer;    // 제작사
    private String age;    // 관람등급
    private String runtime;    // 공연시간
    private String price;    // 가격정보
    private String posterPath;    // 포스터 경로
    private String showDay;    // 공연상세일시
    private String category;    // 공연 카테고리
    private String artCenterName;    // 공연장
    private String menRate;    // 남성이용자 예매율
    private String womenRate;    // 여성 이용자 예매율
    private ArtCenter artCenter;    // 공연장 정보
    private List<CastingList> castingLists;    // 출연진
    private List<NoticeImg> noticeImg;    // 공지사항 이미지
    private List<ShowDetailImg> showDetailImg;    // 공연 상세 정보 이미지
}



