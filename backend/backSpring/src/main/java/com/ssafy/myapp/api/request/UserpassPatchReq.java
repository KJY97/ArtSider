package com.ssafy.myapp.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserpassPatchReq {

	String currentPass;
	String newPass;
}
