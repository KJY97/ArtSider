package com.ssafy.myapp.api.request;


import lombok.Getter;
import lombok.Setter;

/**
 * ���� �α��� API ([POST] /api/auth/login) ��û�� �ʿ��� ������Ʈ �ٵ� ����.
 */
@Getter
@Setter

public class UserLoginPostReq {

	String userId;
	String password;
}
