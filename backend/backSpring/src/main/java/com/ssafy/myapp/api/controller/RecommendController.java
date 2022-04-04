package com.ssafy.myapp.api.controller;

import java.util.HashMap;



import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.api.service.KakaoAuthService;
import com.ssafy.myapp.api.service.RecommendationService;
import com.ssafy.myapp.api.service.ShowService;
import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.common.util.JwtTokenUtil;
import com.ssafy.myapp.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;

@Api(value = "추천 API", tags = {"Recommend"})
@RestController
@RequestMapping("/api/show/recommend")
public class RecommendController {
	
	private final RecommendationService recommendService;


	@Autowired
	public RecommendController(RecommendationService recommendService) {
		this.recommendService = recommendService;
	}
	
	@ResponseBody
    @PostMapping("/{userId}/probability")
	@ApiOperation(value = "예상 평점 분석 실행  ", notes = "사용자의 공연 예상 평점분석을 실행한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "실행 성공"),
            @ApiResponse(code = 401, message = "실행 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> expectRatingRun(@PathVariable String userId) {
        String msg=recommendService.runExpectRating(userId);
        
        Map<String, Object> resultMap = new HashMap<>();
        
        resultMap.put("message", msg);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        
    }

}
