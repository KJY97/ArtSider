package com.ssafy.myapp.api.controller;

import com.ssafy.myapp.api.response.ArtCenterDetailsGetRes;
import com.ssafy.myapp.api.response.PopularShowListGetRes;
import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;
import com.ssafy.myapp.api.service.ShowService;
import com.ssafy.myapp.common.auth.SsafyUserDetails;
import com.ssafy.myapp.db.entity.ExpectRating;
import com.ssafy.myapp.db.entity.Favorite;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.mapping.ExpectRatingMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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


    @GetMapping("/search")
    @ApiOperation(value = "공연 검색", notes = "검색하는 키워드가 포함된 제목의 공연을 찾는다")
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


    @GetMapping("/{category}/all")
    @ApiOperation(value = "카테고리별 전체 공연 목록 조회", notes = "해당 카테고리의 공연을 모두 조회")
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

    @GetMapping("startDate")
    @ApiOperation(value = "개막 예정 공연 목록 조회", notes = "개막 예정인 공연들을 전체 중 오늘 날짜 기준으로 순서대로 가져온다.")
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

    @GetMapping("/{category}/startDate")
    @ApiOperation(value = "카테고리별 개막 예정 공연 목록 조회", notes = "개막 예정 순으로 해당 카테고리의 공연들을 조회한다.")
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

    @GetMapping("endDate")
    @ApiOperation(value = "종료 임박 공연 목록 조회", notes = "종료날짜가 빠른 순으로 전체 공연을 조회한다.")
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

    @GetMapping("/{category}/endDate")
    @ApiOperation(value = "카테고리별 종료 임박 공연 목록 조회", notes = "종료임박 순으로 해당 카테고리의 공연을 조회한다.")
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

    @GetMapping("/recommend/similarity")
    @ApiOperation(value = "사용자간의 유사도 공연 추천", notes = "사용자간의 유사도를 통해(리뷰 평점) 추천하는 공연 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "추천 목록 조회 성공"),
            @ApiResponse(code = 401, message = "추천 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String, List<ShowListGetRes>>> showRecommendationList(@ApiIgnore Authentication authentication) {
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        Map<String, List<ShowListGetRes>> resultMap = new HashMap<>();
        resultMap.put("items", showService.findShowRecommendationList(user));
        return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/recommend/{showId}/relatedShow")
    @ApiOperation(value = "연관 공연 목록", notes = "해당 공연에 연관된 공연을 가져온다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "연관 공연 목록 조회 성공"),
            @ApiResponse(code = 401, message = "연관 공연 목록 조회 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String, List<ShowListGetRes>>> relatedShowList(@PathVariable Long showId) {
        Map<String, List<ShowListGetRes>> resultMap = new HashMap<>();
        resultMap.put("items", showService.findShowRelatedList(showId));
        return new ResponseEntity<Map<String, List<ShowListGetRes>>>(resultMap, HttpStatus.OK);
    }

    @GetMapping("/popular")
    @ApiOperation(value = "전체 인기 공연 목록", notes = "각 카테고리별 상위 4개씩 가져온다.")
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

    @GetMapping("/{category}/popular")
    @ApiOperation(value = "카테고리별 인기 공연 목록", notes = "카테고리별 인기 공연 목록을 조회한다.")
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

    @GetMapping("/{id}")
    @ApiOperation(value = "공연 상세 조회", notes = "공연의 상세정보를 가져온다.")
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


    @GetMapping("/{artcentername}/artcenter")
    @ApiOperation(value = "공연 시설 조회", notes = "공연 시설 정보를 조회한다.")
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

    @GetMapping("recommend/{showId}/probability")
    @ApiOperation(value = "공연을 좋아할 확률 제공", notes = "해당 공연을 좋아할 확률을 제공한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "사용자와 공연을 좋아할 확률 제공 "),
            @ApiResponse(code = 401, message = "사용자와 공연을 좋아할 확률 제공 실패"),
            @ApiResponse(code = 500, message = "서버 에러")
    })
    public ResponseEntity<Map<String , Object>> expectRatingGet(@ApiIgnore Authentication authentication,@PathVariable(value = "showId") Long showId) {
        Map<String, Object> resultMap = new HashMap<>();
        
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        
        ExpectRatingMapping rating=showService.findExpectRating(user.getId(), showId);
        if(rating!=null) {
        	resultMap.put("message","success");
            resultMap.put("rating",rating.getRating());
            return new ResponseEntity<Map<String , Object>>(resultMap, HttpStatus.ACCEPTED);
        }
        resultMap.put("message","rating doesn't exist");
        resultMap.put("rating",rating);
        return new ResponseEntity<Map<String , Object>>(resultMap, HttpStatus.ACCEPTED);
    }
    
}
