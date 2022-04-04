package com.ssafy.myapp.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterPostReq {
	private String userEmail;
    private String password;
    private String userName;
    private String telnum;
}
