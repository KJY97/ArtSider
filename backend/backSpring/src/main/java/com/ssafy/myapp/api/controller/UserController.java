package com.ssafy.myapp.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.db.entity.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "���� API", tags = {"User"})
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public UserController(UserService userService,PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    
    @PostMapping()
    @ApiOperation(value = "ȸ�� ����", notes = "<strong>���̵�� �н�����</strong>�� ���� ȸ������ �Ѵ�.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "ȸ������ ����"),
            @ApiResponse(code = 401, message = "ȸ������ ����"),
            @ApiResponse(code = 500, message = "���� ����")
    })
    public ResponseEntity<Map<String, Object>> createUser(
            @RequestBody @ApiParam(value = "ȸ������ ����", required = true) UserRegisterPostReq registerInfo) {

        String userId = registerInfo.getEmail();
        Map<String, Object> resultMap = new HashMap<>();
        
        if(userService.chkDplByEmail(userId)) { //���� ������ �����ϸ�
        	resultMap.put("message", "fail");
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<Map<String, Object>>(resultMap, status);
        }
        //���Ƿ� ���ϵ� User �ν��Ͻ�. ���� �ڵ�� ȸ�� ���� ���� ���θ� �Ǵ��ϱ� ������ ���� Insert �� ���� ������ �������� ����.
        User user = userService.createUser(registerInfo);
        resultMap.put("message", "success");
        HttpStatus status = HttpStatus.ACCEPTED;
        return new ResponseEntity<Map<String, Object>>(resultMap, status);
    }
    
    
}
