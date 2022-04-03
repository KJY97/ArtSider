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
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.*;


@Api(value = "공연 API", tags = {"Show"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/show")
public class ShowController {

    private final ShowService showService;

    HashMap<String, String> map = new HashMap<String, String>() {{
        put("MU", "뮤지컬");
        put("CO", "콘서트");
        put("CL", "클래식/오페라");
        put("DA", "무용/전통예술");
        put("DR", "연극");
        put("FA", "아동/가족");
    }};


    // 검색 기능
    @GetMapping("/search")
    @ApiResponses({    // Api가 붙은 annotation은 swagger와 설정과 관련된 Annotation
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<ShowListGetRes>>> searchList(@RequestParam(value = "keyword") String keyword) {
        Map<String, List<ShowListGetRes>> resultMap = new HashMap<>();
        resultMap.put("items", showService.findShowName(keyword));
        return new ResponseEntity<Map<String , List<ShowListGetRes>>>(resultMap, HttpStatus.OK);
    }


    // 카테고리별 전체 공연 목록 조회
    @GetMapping("/{category}/all")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<ShowListGetRes>>> showCategoryList(@PathVariable String category) {
        Map<String, List<ShowListGetRes>> resultMap = new HashMap<>();
        resultMap.put("items", showService.findShowCategoryAllList(map.get(category)));
        return new ResponseEntity<Map<String , List<ShowListGetRes>>>(resultMap, HttpStatus.OK);
    }

    // 개막 예정 공연 목록
    @GetMapping("startDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<ShowListGetRes>>> showStartList() throws ParseException {
        Map<String, List<ShowListGetRes>> resultMap = new HashMap<>();
        try {
            resultMap.put("items", showService.findShowStartList());
            return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.OK);
        } catch (ParseException e) {
            resultMap.put("items", showService.findShowStartList());
            return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.BAD_REQUEST);

        }
    }

    // 카테고리별 개막 예정 공연 목록
    @GetMapping("/{category}/startDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<ShowListGetRes>>> showCategoryStartList(@PathVariable String category) throws ParseException {
        Map<String, List<ShowListGetRes>> resultMap = new HashMap<>();
        try {
            resultMap.put("items", showService.findShowCategoryStartList(map.get(category)));
            return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.OK);
        } catch (ParseException e) {
            resultMap.put("items", showService.findShowCategoryStartList(map.get(category)));
            return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    // 종료 임박 공연 목록
    @GetMapping("endDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<ShowListGetRes>>> showEndList() throws ParseException {
        Map<String, List<ShowListGetRes>> resultMap = new HashMap<>();
        try {
            resultMap.put("items", showService.findShowEndList());
            return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.OK);
        } catch (ParseException e) {
            resultMap.put("items", showService.findShowEndList());
            return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    // 카테고리별 종료 임박 공연 목록
    @GetMapping("/{category}/endDate")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<ShowListGetRes>>> showCategoryEndList(@PathVariable String category) throws ParseException {
        Map<String, List<ShowListGetRes>> resultMap = new HashMap<>();
        try {
            resultMap.put("items", showService.findShowCategoryEndList(map.get(category)));
            return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.OK);
        } catch (ParseException e) {
            resultMap.put("items", showService.findShowCategoryEndList(map.get(category)));
            return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    // 공연 추천(사용자간의 유사도 추천)
    @GetMapping("/{userId}/recommendation")
    @ApiResponses({
            @ApiResponse(code = 200, message = "추천 목록 조회 성공"),
            @ApiResponse(code = 401, message = "추천록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String, List<ShowListGetRes>>> showRecommendationList(@PathVariable Long userId) {
        Map<String, List<ShowListGetRes>> resultMap = new HashMap<>();
        resultMap.put("items", showService.findShowRecommendationList(userId));
        return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.OK);
    }

    // 전체 인기 공연 목록
    @GetMapping("/popular")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인기공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "인기공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<PopularShowListGetRes>>> popularShowAllList() {
        Map<String, List<PopularShowListGetRes>> resultMap = new HashMap<>();
        resultMap.put("items", showService.findPopularShowList());
        return new ResponseEntity<Map<String , List<PopularShowListGetRes>>>(resultMap, HttpStatus.OK);
    }

    // 카테고리별 인기 공연 목록()
    @GetMapping("/{category}/popular")
    @ApiResponses({
            @ApiResponse(code = 200, message = "카테고리별 인기공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "카테고리별 인기공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<PopularShowListGetRes>>> popularShowCategoryList(@PathVariable String category) {
        Map<String, List<PopularShowListGetRes>> resultMap = new HashMap<>();
        resultMap.put("items", showService.findPopularShowCategoryList(map.get(category)));
        return new ResponseEntity<Map<String , List<PopularShowListGetRes>>>(resultMap, HttpStatus.OK);
    }

    // 공연 상세 조회
    @GetMapping("/{id}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연 상세 정보 조회 성공"),
            @ApiResponse(code = 401, message = "공연 상세 정보 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<ShowDetailsGetRes>>> showDetails(@PathVariable(value = "id") Long id) {
        Map<String, List<ShowDetailsGetRes>> resultMap = new HashMap<>();
        try {
            resultMap.put("items", showService.findShowDetails(id));
            return new ResponseEntity<Map<String , List<ShowDetailsGetRes>>>(resultMap, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            resultMap.put("items", showService.findShowDetails(id));
            return new ResponseEntity<Map<String , List<ShowDetailsGetRes>>>(resultMap, HttpStatus.BAD_REQUEST);
        }
    }

    // 공연 시설 조회
    @GetMapping("/{artcentername}/artcenter")
    @ApiResponses({
            @ApiResponse(code = 200, message = "공연시설 상세 정보 조회 성공"),
            @ApiResponse(code = 401, message = "공연시설 상세 정보 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , List<ArtCenterDetailsGetRes>>> artCenterDetails(@PathVariable(value = "artcentername") String artCenterName) {
        Map<String, List<ArtCenterDetailsGetRes>> resultMap = new HashMap<>();
        try {
            resultMap.put("items", showService.findArtCenterDetails(artCenterName));
            return new ResponseEntity<Map<String , List<ArtCenterDetailsGetRes>>>(resultMap, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            resultMap.put("items", showService.findArtCenterDetails(artCenterName));
            return new ResponseEntity<Map<String , List<ArtCenterDetailsGetRes>>>(resultMap, HttpStatus.BAD_REQUEST);
        }
    }
}
