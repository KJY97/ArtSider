package com.ssafy.myapp.api.service;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.db.entity.User;


public interface UserService {
//	User getUserById(Long Id);
	User findUserByEmail(String email);
	User addUser(UserRegisterPostReq userRegisterInfo);
	void modifyPassword(String userId, String password);
	void removeUser(String userId);
	String createAuthNum();
	boolean chkDplByEmail(String userEmail);
	String sendNewPass(String email);
	String sendAuthNum(String email);
//	void updatePassword(String userId, String password);
//	void deleteUser(String userId);
	
}
