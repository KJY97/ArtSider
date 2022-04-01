package com.ssafy.myapp.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.api.response.UserLoginPostRes;
import com.ssafy.myapp.api.service.KakaoAuthService;
import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.common.util.JwtTokenUtil;
import com.ssafy.myapp.db.entity.User;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/auth")
public class KakaoAuthController {

    /**
     * 카카오 callback
     * [GET] /oauth/kakao/callback
     */
	
	private final UserService userService;
	private final KakaoAuthService kakaoAuthService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public KakaoAuthController(UserService userService,PasswordEncoder passwordEncoder,KakaoAuthService kakaoAuthService) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
		this.kakaoAuthService=kakaoAuthService;
	}
	
    @ResponseBody
    @GetMapping("/kakao")
    public ResponseEntity<Map<String, Object>> kakaoCallback(@RequestParam String code) {
        System.out.println(code);
        String token=kakaoAuthService.getKakaoAccessToken(code);
        Map<String, String> userInfo=kakaoAuthService.createKakaoUser(token);
        
        Map<String, Object> resultMap = new HashMap<>();
        
        //user테이블에 존재하지 않으면 회원가입 처리 
        if(!userService.chkDplByEmail(userInfo.get("email"))) {
//        	System.out.println("없는 회원입니다. 회원가입을 진행합니다.");
        	UserRegisterPostReq user= new UserRegisterPostReq();
            user.setUserEmail(userInfo.get("email"));
            user.setUserName(userInfo.get("nickname"));
            user.setPassword("");
            User signUser = userService.addUser(user);
        }
        //user테이블에 email로 찾은 user정보 accesstoken 보내줌
        User findUser;
		try {
			findUser = userService.findUserByEmail(userInfo.get("email"));
		} catch (NoSuchElementException e) {
			resultMap.put("message", "noSuchElement exist");
	        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
		}
		
		resultMap.put("accessToken",JwtTokenUtil.getToken(userInfo.get("email")));
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        // user 존재하는지 확인-> email로 확인
    }
   
}