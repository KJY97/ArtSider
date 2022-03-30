package com.ssafy.myapp.api.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myapp.api.request.UserLoginPostReq;
import com.ssafy.myapp.api.response.UserLoginPostRes;
import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.common.util.JwtTokenUtil;
import com.ssafy.myapp.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;



@Api(value = "인증 API", tags = {"Auth."})
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AuthController(UserService userService,PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "로그인", notes = "<strong>아이디와 패스워드</strong>를 통해 로그인 한다.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "성공", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "유효하지 않은 아이디/패스워드"),
        @ApiResponse(code = 404, message = "사용자 없음"),
        @ApiResponse(code = 500, message = "서버 오류")
    })
	public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value="로그인 정보", required = true) UserLoginPostReq loginInfo) {
//		System.out.println(loginInfo.toString());
		String email = loginInfo.getEmail();
		String password = loginInfo.getPassword();
		User user ;
		try {
			user = userService.findUserByEmail(email);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).body(UserLoginPostRes.of(404, "user doesn't exist", null));
		}
		
		if(passwordEncoder.matches(password, user.getPassword())) {

			return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(email)));
		}
		return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));
	}
}
