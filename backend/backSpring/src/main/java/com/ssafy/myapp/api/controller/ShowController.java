package com.ssafy.myapp.api.controller;

import com.ssafy.myapp.api.response.ArtCenterDetailsGetRes;
import com.ssafy.myapp.api.response.PopularShowListGetRes;
import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;
import com.ssafy.myapp.api.service.ShowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;


@Api(value = "공연 API", tags = {"Show"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/show")
public class ShowController {

    private final ShowService showService;

    HashMap<String,String> map = new HashMap<String,String>(){{
        put("MU","뮤지컬");
        put("CO","콘서트");
        put("CL","클래식/오페라");
        put("DA","무용/전통예술");
        put("DR","연극");
        put("FA","아동/가족");
    }};

    // 전체 공연 목록 조회
    @GetMapping("/all")
    @ApiResponses({    // Api가 붙은 annotation은 swagger와 설정과 관련된 Annotation
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> showAllList() {
        return new ResponseEntity<List<ShowListGetRes>>(showService.findShowAllList(),HttpStatus.OK);
    }

    // 카테고리별 전체 공연 목록 조회
    @GetMapping("/{category}/all")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> showCategoryList(@PathVariable String category) {
        return new ResponseEntity<List<ShowListGetRes>>(showService.findShowCategoryAllList(map.get(category)),HttpStatus.OK);
    }

    // 개막 예정 공연 목록
    @GetMapping("startDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> showStartList() throws ParseException {
        return new ResponseEntity<List<ShowListGetRes>>(showService.findShowStartList(),HttpStatus.OK);
    }

    // 카테고리별 개막 예정 공연 목록
    @GetMapping("/{category}/startDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> showCategoryStartList(@PathVariable String category) throws ParseException {
        return new ResponseEntity<List<ShowListGetRes>>(showService.findShowCategoryStartList(map.get(category)), HttpStatus.OK);
    }

    // 종료 임박 공연 목록
    @GetMapping("endDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> showEndList() throws ParseException {
        return new ResponseEntity<List<ShowListGetRes>>(showService.findShowEndList(), HttpStatus.OK);
    }

    // 카테고리별 종료 임박 공연 목록
    @GetMapping("/{category}/endDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<ShowListGetRes>> showCategoryEndList(@PathVariable String category) throws ParseException {
        return new ResponseEntity<List<ShowListGetRes>>(showService.findShowCategoryEndList(map.get(category)), HttpStatus.OK);
    }

    // 전체 인기 공연 목록
    @GetMapping("/popular")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인기공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "인기공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<PopularShowListGetRes>> popularShowAllList() {
        List<PopularShowListGetRes> popularShowList = new ArrayList<PopularShowListGetRes>();
        popularShowList = showService.findPopularShowList();
        return new ResponseEntity<List<PopularShowListGetRes>>(showService.findPopularShowList(), HttpStatus.OK);
    }


    // 카테고리별 인기 공연 목록()
    @GetMapping("/{category}/popular")
    @ApiResponses({
            @ApiResponse(code = 200, message = "카테고리별 인기공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "카테고리별 인기공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<List<PopularShowListGetRes>> popularShowCategoryList(@PathVariable String category) {
        return new ResponseEntity<List<PopularShowListGetRes>>(showService.findPopularShowCategoryList(map.get(category)), HttpStatus.OK);
    }


    // 공연 상세 조회
    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 상세 정보 조회 성공"),
            @ApiResponse(code = 401, message = "공연 상세 정보 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<ShowDetailsGetRes> showDetails(@PathVariable(value = "id") Long id) {

        ShowDetailsGetRes showDetails = new ShowDetailsGetRes();

        try {
            return new ResponseEntity<ShowDetailsGetRes>(showService.findShowDetails(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ShowDetailsGetRes>(showService.findShowDetails(id), HttpStatus.BAD_REQUEST);
        }
    }


    // 공연 시설 조회
    @GetMapping("/{artcentername}/artcenter")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연시설 상세 정보 조회 성공"),
            @ApiResponse(code = 401, message = "공연시설 상세 정보 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<ArtCenterDetailsGetRes> artCenterDetails(@PathVariable(value = "artcentername") String artCenterName) {
        try {
            return new ResponseEntity<ArtCenterDetailsGetRes>(showService.findArtCenterDetails(artCenterName), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<ArtCenterDetailsGetRes>(showService.findArtCenterDetails(artCenterName), HttpStatus.BAD_REQUEST);
        }
    }
}
