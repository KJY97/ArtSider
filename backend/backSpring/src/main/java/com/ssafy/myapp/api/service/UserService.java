package com.ssafy.myapp.api.service;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.db.entity.Favorite;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.entity.Viewed;
import com.ssafy.myapp.db.mapping.ShowMapping;


public interface UserService {
	User findUserByEmail(String email);
	User addUser(UserRegisterPostReq userRegisterInfo);
	User modifyNickname(User user,String nickname);
	void modifyPassword(String userId, String password);
	void removeUser(String userId);
	String createAuthNum();
	boolean chkDplByEmail(String userEmail);
	String sendNewPass(String email);
	String sendAuthNum(String email);
	Favorite addFavorite(Long userId, Long showId) throws Exception;
	void removeFavorite(Long userId, Long showId) throws Exception;
	Viewed addViewed(Long userId, Long showId);
	List<ShowMapping> findPreferShow(User user);
	List<ShowMapping> findViewedShow(User user);
	String saveUploadedFiles(final MultipartFile thumbnail) throws IOException;
	User modifyUserProfileImg(User user,String profileImg);
}
