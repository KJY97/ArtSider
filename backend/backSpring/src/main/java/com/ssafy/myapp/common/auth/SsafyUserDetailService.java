package com.ssafy.myapp.common.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.db.entity.User;


@Component
public class SsafyUserDetailService implements UserDetailsService{
//	@Autowired
//	UserService userService;

	private final UserService userService;
	@Autowired
	public SsafyUserDetailService(UserService userService) {
		this.userService = userService;
	}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    		User user = userService.findUserByEmail(email);
    		if(user != null) {
    			SsafyUserDetails userDetails = new SsafyUserDetails(user);
    			return userDetails;
    		}
    		return null;
    }
}
