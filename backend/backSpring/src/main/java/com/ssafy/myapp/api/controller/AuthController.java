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

/**
 * ���� ���� API ��û ó���� ���� ��Ʈ�ѷ� ����.
	http://localhost:8080/swagger-ui/
 */

@Api(value = "���� API", tags = {"Auth."})
@RestController
@RequestMapping("/api/auth/login")
public class AuthController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public AuthController(UserService userService,PasswordEncoder passwordEncoder) {
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}
	
	@PostMapping("/login")
	@ApiOperation(value = "�α���", notes = "<strong>���̵�� �н�����</strong>�� ���� �α��� �Ѵ�.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "����", response = UserLoginPostRes.class),
        @ApiResponse(code = 401, message = "��ȿ���� ���� ���̵�/�н�����"),
        @ApiResponse(code = 404, message = "����� ����"),
        @ApiResponse(code = 500, message = "���� ����")
    })
	public ResponseEntity<UserLoginPostRes> login(@RequestBody @ApiParam(value="�α��� ����", required = true) UserLoginPostReq loginInfo) {
		System.out.println(loginInfo.toString());
		String email = loginInfo.getEmail();
		String password = loginInfo.getPassword();
		User user ;
		try {
			user = userService.getUserByEmail(email);
		} catch (NoSuchElementException e) {
			return ResponseEntity.status(404).body(UserLoginPostRes.of(404, "user doesn't exist", null));
		}
		
		// �α��� ��û�� �����κ��� �Էµ� �н����� �� ��� ����� ������ ��ȣȭ�� �н����尡 ������ Ȯ��.(��ȿ�� �н��������� ���� Ȯ��)
		if(passwordEncoder.matches(password, user.getPassword())) {
			// ��ȿ�� �н����尡 �´� ���, �α��� �������� ����.(�׼��� ��ū�� �����Ͽ� ���䰪 ����)
			return ResponseEntity.ok(UserLoginPostRes.of(200, "Success", JwtTokenUtil.getToken(email)));
		}
		// ��ȿ���� �ʴ� �н������� ���, �α��� ���з� ����.
		return ResponseEntity.status(401).body(UserLoginPostRes.of(401, "Invalid Password", null));
	}
}
