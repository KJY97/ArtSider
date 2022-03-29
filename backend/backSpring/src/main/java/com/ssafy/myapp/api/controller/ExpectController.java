package com.ssafy.myapp.api.controller;

import com.ssafy.myapp.api.request.ReviewRegisterReq;
import com.ssafy.myapp.api.response.ExpectListGetRes;
import com.ssafy.myapp.api.service.ExpectService;
import com.ssafy.myapp.db.entity.Expectation;
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

@Api(value = "기대평 API", tags = {"Expectation"})
@RestController
@RequestMapping("/api/show")
public class ExpectController {

    private final ExpectService expectService;

    @Autowired
    public ExpectController(ExpectService expectService) {
        this.expectService = expectService;
    }

    @ApiOperation(value = "기대평 목록 조회", notes = "<strong>공연번호 showId</strong>의 기대평 목록을 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "기대평목록조회 성공"),
            @ApiResponse(code = 401, message = "기대평목록조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @GetMapping("/expectations/{id}")
    public ResponseEntity<Map<String, Object>> expectationList(
            @ApiParam(value = "공연번호", required = true) @PathVariable("id") Long id,
            @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC)Pageable pageable) {
        Map<String, Object> resultMap = new HashMap<>();

        Page<ExpectListGetRes> expectation = expectService.findExpectation(id, pageable);
        resultMap.put("length", expectation.getTotalElements());
        resultMap.put("items", expectation.getContent());
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
    }

    @ApiOperation(value = "기대평 작성", notes = "<strong>공연번호 showId</strong>의 기대평을 작성한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "기대평 작성 성공"),
            @ApiResponse(code = 401, message = "기대평 작성 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PostMapping("/expectations/{id}")
    public ResponseEntity<Map<String, Object>> expectationAdd(
            @ApiParam(value = "공연번호", required = true) @PathVariable("id") Long id,
            @ApiParam(value = "작성한 기대평 객체", required = true) @RequestBody ReviewRegisterReq expectInfo) {
        Map<String, Object> resultMap = new HashMap<>();

        if(expectService.addExpectation(id, expectInfo) != null) {
            resultMap.put("message", "success");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        }
        resultMap.put("message", "fail");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "기대평 수정", notes = "<strong>expectationId를 번호로 갖는 기대평</strong>을 수정한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "기대평 수정 성공"),
            @ApiResponse(code = 401, message = "기대평 수정 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @PutMapping("/expectations/{expectationId}")
    public ResponseEntity<Map<String, Object>> expectationModify(
            @ApiParam(value = "기대평 번호", required = true) @PathVariable("expectationId") Long expectationId,
            @ApiParam(value = "수정할 기대평 객체", required = true) @RequestBody ReviewRegisterReq expectInfo) {
        Map<String, Object> resultMap = new HashMap<>();

        if(expectService.modifyExpectation(expectationId, expectInfo)) {
            resultMap.put("message", "success");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        }
        resultMap.put("message", "fail");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "기대평 삭제", notes = "<strong>expectationId를 번호로 갖는 기대평</strong>을 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "기대평 삭제 성공"),
            @ApiResponse(code = 401, message = "기대평 삭제 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    @DeleteMapping("/expectations/{expectationId}")
    public ResponseEntity<Map<String, Object>> expectationRemove(
            @ApiParam(value = "리뷰번호", required = true) @PathVariable("expectationId") Long expectationId) {
        Map<String, Object> resultMap = new HashMap<>();

        if(expectService.removeExpectation(expectationId)) {
            resultMap.put("message", "success");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
        }
        resultMap.put("message", "fail");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    }
}
