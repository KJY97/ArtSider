package com.ssafy.myapp.api.service;


/**
 *	
 */
public interface UserService {
	boolean chkDplByUserId(String userId);
	boolean chkDplByUserEmail(String userEmail);
	void updatePassword(String userId, String password);
	void deleteUser(String userId);
	
}
