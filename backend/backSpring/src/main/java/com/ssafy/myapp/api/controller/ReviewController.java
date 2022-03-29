package com.ssafy.myapp.api.controller;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.api.response.ReviewListGetRes;
import com.ssafy.myapp.api.service.ReviewService;
import com.ssafy.myapp.db.entity.Review;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Api(value = "리뷰 API", tags = {"Review"})
@RestController
@RequestMapping("/api/show")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @ApiOperation(value = "관람후기 목록 조회", notes = "<strong>공연번호 id</strong>의 관람후기 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "리뷰목록조회 성공"),
            @ApiResponse(code = 401, message = "리뷰목록조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/reviews/{id}")
    public ResponseEntity<Map<String, Object>> reviewList(
            @ApiParam(value = "공연번호", required = true) @PathVariable("id") Long id,
            @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable) {
        Map<String, Object> map = new HashMap<>();
        Page<ReviewListGetRes> review = reviewService.findReview(id, pageable);

        map.put("length", review.getTotalElements());
        map.put("items", review.getContent());

        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }

    @ApiOperation(value = "관람후기 작성", notes = "<strong>공연번호 id</strong>의 관람후기를 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "후기작성 성공"),
            @ApiResponse(code = 401, message = "후기작성 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/reviews/{id}")
    public ResponseEntity<Map<String, Object>> reviewAdd(
            @ApiParam(value = "공연번호", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "작성한 리뷰객체", required = true) @RequestBody ReviewRegisterReq reviewInfo) {
        Map<String, Object> resultMap = new HashMap<>();

        if(reviewService.addReview(id, reviewInfo) != null) {
            resultMap.put("message", "success");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        }
        resultMap.put("message", "fail");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "관람후기 수정", notes = "<strong>공연번호 id</strong>의 관람후기를 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "후기수정 성공"),
            @ApiResponse(code = 401, message = "후기수정 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PutMapping("/reviews/{reviewId}")
    public ResponseEntity<Map<String, Object>> reviewModify(
            @ApiParam(value = "리뷰번호", required = true) @PathVariable("reviewId") Long reviewId,
            @ApiParam(value = "수정할 리뷰객체", required = true) @RequestBody ReviewRegisterReq reviewInfo) {
        Map<String, Object> resultMap = new HashMap<>();

        if(reviewService.modifyReview(reviewId, reviewInfo)) {
            resultMap.put("message", "success");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        }
        resultMap.put("message", "fail");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "관람후기 삭제", notes = "<strong>공연번호 id</strong>의 관람후기를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "후기삭제 성공"),
            @ApiResponse(code = 401, message = "후기삭제 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @DeleteMapping("/reviews/{reviewId}")
    public ResponseEntity<Map<String, Object>> reviewRemove(
            @ApiParam(value = "리뷰번호", required = true) @PathVariable("reviewId") Long reviewId) {
        Map<String, Object> resultMap = new HashMap<>();

        if(reviewService.removeReview(reviewId)) {
            resultMap.put("message", "success");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        }
        resultMap.put("message", "fail");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }
}
