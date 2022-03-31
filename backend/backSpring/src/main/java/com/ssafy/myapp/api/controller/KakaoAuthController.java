package com.ssafy.myapp.api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myapp.api.service.KakaoAuthService;
import com.ssafy.myapp.api.service.UserService;

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
    public void kakaoCallback(@RequestParam String code) {
        System.out.println(code);
        String token=kakaoAuthService.getKakaoAccessToken(code);
        System.out.println("tocken: "+ token);
        kakaoAuthService.createKakaoUser(token);
        // user 존재하는지 확인-> email로 확인
    }
   
}