package com.ssafy.myapp.api.response;


import lombok.Getter;
import lombok.Setter;

/**
 * ���� �α��� API ([POST] /api/v1/auth) ��û�� ���� ���䰪 ����.
 */
@Getter
@Setter

public class UserLoginPostRes {
//	@ApiModelProperty(name="JWT ���� ��ū", example="eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN...")
	String accessToken;
	
	public static UserLoginPostRes of(Integer statusCode, String message, String accessToken) {
		UserLoginPostRes res = new UserLoginPostRes();
		res.setAccessToken(accessToken);
		return res;
	}
}
