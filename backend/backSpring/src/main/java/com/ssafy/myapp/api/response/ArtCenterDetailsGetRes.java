package com.ssafy.myapp.api.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtCenterDetailsGetRes {

    private Long id;    // 공연장 id

    private String artCenterName;    // 공연장 이름

    private String artCenterAddress;    // 공연장 주소

    private String artCenterTel;    // 공연장 전화번호

    private String artCenterWeb;    // 공연장 홈페이지
}
