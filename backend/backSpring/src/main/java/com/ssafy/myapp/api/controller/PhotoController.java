package com.ssafy.myapp.api.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.myapp.api.request.UserpassPatchReq;
import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.api.service.UserServiceImpl;
import com.ssafy.myapp.common.auth.SsafyUserDetails;
import com.ssafy.myapp.db.entity.User;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/users/profile/photo")
public class PhotoController {
	
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    
    @Autowired
    public PhotoController(UserService userService,PasswordEncoder passwordEncoder) {

        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
	
	@GetMapping(
			value="/{profileImg}",
			  produces = MediaType.IMAGE_JPEG_VALUE
			)
	@ApiOperation(value = "사용자 프로필 이미지 가져오기", notes = "회원의 프로필 이미지를 가져온다.")
	public byte[] imageWithMediaTypeGet(@PathVariable String profileImg) throws IOException {
		String absolutePath = new File("").getAbsolutePath() + "\\images\\";
		InputStream in = new FileInputStream(absolutePath+profileImg);
		byte[] imageByteArray = IOUtils.toByteArray(in);
		return imageByteArray;
		}
	
	
	
	@PostMapping("")
	@ApiOperation(value = "사용자 프로필 이미지 등록", notes = "회원의 프로필 이미지를 변경한다.")
	public ResponseEntity<Map<String,Object>> handleFileUpload(@ApiIgnore Authentication authentication,
			@RequestPart(value = "file") final MultipartFile uploadfile) throws IOException {
		String profileImg=userService.saveUploadedFiles(uploadfile);
		Map<String,Object> resultMap = new HashMap<>();
		
		SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        
		//user profile_img 수정
		userService.modifyUserProfileImg(user, profileImg);
		
		resultMap.put("message", "success");
		resultMap.put("profileImg",profileImg );

		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
	}


	

}
