package com.ssafy.myapp.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;

/**
 *	
 */
@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User getUserById(Long id) {
		User user = userRepository.findUserById(id).get();
		return user;
	}

	@Override
	public User getUserByEmail(String email) {
		User user = userRepository.findUserByEmail(email).get();
		return user;
	}

	public boolean chkDplByEmail(String email) {
		if(userRepository.findUserByEmail(email).isPresent()) // 이메일이 존재하면
			return true;
		else return false;
	}

	@Override
	public User createUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();

		user.setEmail(userRegisterInfo.getEmail());
		// 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setNickname(userRegisterInfo.getNickname());
		user.setTelNum(userRegisterInfo.getTelNum());
		user.setCreateDate(Calendar.getInstance().getTime());

		return userRepository.save(user);
	}





}
