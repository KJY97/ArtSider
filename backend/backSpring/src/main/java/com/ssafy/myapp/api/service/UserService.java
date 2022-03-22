package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.db.entity.User;

/**
 *	
 */
public interface UserService {
	User getUserById(Long Id);//pk값 기준으로 유저 찾기
	User getUserByEmail(String email);//email값 기준으로 유저 찾기
	boolean chkDplByEmail(String userEmail);
	User createUser(UserRegisterPostReq userRegisterInfo);
//	void updatePassword(String userId, String password);
//	void deleteUser(String userId);
	
}
