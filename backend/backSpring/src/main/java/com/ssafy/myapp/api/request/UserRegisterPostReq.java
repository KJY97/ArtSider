package com.ssafy.myapp.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegisterPostReq {
	private String email;
    private String password;
    private String nickname;
    private String telNum;
}
