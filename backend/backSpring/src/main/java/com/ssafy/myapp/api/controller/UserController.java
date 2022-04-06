package com.ssafy.myapp.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myapp.api.request.UserModifyProfilReq;
import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.api.request.UserpassPatchReq;
import com.ssafy.myapp.api.service.UserService;
import com.ssafy.myapp.common.auth.SsafyUserDetails;
import com.ssafy.myapp.db.entity.Favorite;
import com.ssafy.myapp.db.entity.Review;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.entity.Viewed;
import com.ssafy.myapp.db.mapping.ShowMapping;
import com.ssafy.myapp.db.mapping.UserReviewMapping;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Api(value = "유저 API", tags = {"User"})
@RestController
@RequestMapping("/api/users")
public class UserController {
	
	
	private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    
    
    @Autowired
    public UserController(UserService userService,PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @PostMapping()
    @ApiOperation(value = "회원 가입", notes = "<strong>아이디와 패스워드</strong>를 통해 회원가입 한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "회원가입 성공"),
            @ApiResponse(code = 401, message = "회원가입 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> userAdd(
            @RequestBody @ApiParam(value = "회원가입 정보", required = true) UserRegisterPostReq registerInfo) {

        String userEmail = registerInfo.getUserEmail();
        Map<String, Object> resultMap = new HashMap<>();
        
        if(userService.chkDplByEmail(userEmail)) {
        	resultMap.put("message", "fail");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        User user = userService.addUser(registerInfo);
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }
    
    
    @GetMapping("/{userEmail}")
    @ApiOperation(value = "이메일 중복검사 ", notes = "<strong>이메일</strong>중복여부를 확인한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "중복 없음"),
            @ApiResponse(code = 401, message = "중복"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> emailChk(
            @PathVariable @ApiParam(value = "회원가입 정보", required = true) String userEmail) {
        Map<String, Object> resultMap = new HashMap<>();
        
        if(userService.chkDplByEmail(userEmail)) {
        	resultMap.put("message", "fail");
        	resultMap.put("emailCheck",false);
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
       
        resultMap.put("message", "success");
        resultMap.put("emailCheck",true);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }
    
    @PostMapping("/{userEmail}/password")
    @ApiOperation(value = "비밀번호 찾기", notes = "로그인한 회원의 비밀번호를 찾습니다")
    @ApiResponses({
            @ApiResponse(code = 200, message = "임시 비밀번호 발급 성공"),
            @ApiResponse(code = 401, message = "임시 비밀번호 발급 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> findPassword(
            @PathVariable @ApiParam(value = "비밀번호 찾기 정보", required = true) String userEmail
    ) {
    	Map<String, Object> resultMap = new HashMap<>();
    	User user=null;
    	try {
    		user = userService.findUserByEmail(userEmail);
		} catch (NoSuchElementException e) {
			resultMap.put("message", "invailed user");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
		}

        String newPass=userService.sendNewPass(userEmail);
        if(newPass==null) {
        	resultMap.put("message", "Failed to send email");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }
    
    
    
    @PostMapping("/{userEmail}")
    @ApiOperation(value = "이메일 본인 인증", notes = "이메일이 본인 이메일인지 인증합니다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "인증번호 생성및 메일전송 성공"),
            @ApiResponse(code = 401, message = "인증번호 생성 및 메일전송 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> emailAuth(
            @PathVariable @ApiParam(value = "인증번호 발송 이메일", required = true) String userEmail
    ) {
    	Map<String, Object> resultMap = new HashMap<>();
    	String emailNumber=userService.sendAuthNum(userEmail);
    	if(emailNumber==null) {
    		resultMap.put("message", "Failed to send email");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
    	}
        resultMap.put("message", "success");
        resultMap.put("emailNumber", emailNumber);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
    }
    
    
    
    
//    @DeleteMapping("/{userEmail}")
//    @ApiOperation(value = "유저 정보 삭제", notes = "유저 정보를 삭제 후 응답한다")
//    @ApiResponses({
//            @ApiResponse(code = 200, message = "유저 정보 삭제(탈퇴) 성공"),
//            @ApiResponse(code = 401, message = "유저 정보 삭제(탈퇴) 실패"),
//            @ApiResponse(code = 500, message = "서버 오류")
//    })
//    public ResponseEntity<Map<String, Object>> userRemove(
//            @PathVariable("userEmail") String userEmail
//    ) {
//    	Map<String, Object> resultMap = new HashMap<>();
//
//        userService.removeUser(userEmail);
//        resultMap.put("message", "success");
//        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
//
//    }
    
    @PostMapping("/password")
    @ApiOperation(value = "회원 비밀번호 변경", notes = "회원의 비밀번호를 변경한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "비밀번호 변경 "),
            @ApiResponse(code = 401, message = "비밀번호 변경 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> passwordModify(@ApiIgnore Authentication authentication,
            @RequestBody @ApiParam(value = "비밀번호 정보", required = true) UserpassPatchReq userpassPatchReq) {

    	Map<String, Object> resultMap = new HashMap<>();
    	
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();

        // 비밀번호 변경 요청한 유저로부터 입력된 패스워드 와 디비에 저장된 유저의 암호화된 패스워드가 같은지 확인.(유효한 패스워드인지 여부 확인)
        if(passwordEncoder.matches(userpassPatchReq.getCurrentPassWord(), user.getPassword())) {
            // 유효한 패스워드가 맞는 경우
            userService.modifyPassword(user.getEmail(), userpassPatchReq.getNewPassWord());
            resultMap.put("message", "success");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        }
        resultMap.put("message", "fail");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);

    }
    
    @PostMapping("/show/{id}/preference")
    @ApiOperation(value = "회원 관심목록 추가 및 유저 선호 태그 추가",
            notes = "회원의 관심 공연과 해당 공연의 태그를 유저 선호 태그로 저장한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "추가 성공"),
            @ApiResponse(code = 400, message = "추가 실패/중복된 공연 등록"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> FavoriteAdd(@ApiIgnore Authentication authentication,@PathVariable("id") Long showId) {

    	Map<String, Object> resultMap = new HashMap<>();
    	
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        
        Favorite favorite=null;
        try {
			favorite=userService.addFavorite(user.getId(), showId);
		}catch(Exception e) {
			resultMap.put("message", "duplicated favorite show");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
		}
        
        if(favorite==null) {
        	 resultMap.put("message", "fail");
             return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }

        try {
            userService.addUserTag(user.getId(), showId);
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.put("message", "error saving userTag");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
       
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    @GetMapping("/profile/preference")
    @ApiOperation(value = "회원 관심목록 조회 ", notes = "회원의 관심 공연을 조회한다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공  "),
            @ApiResponse(code = 401, message = "조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> FavoriteList(@ApiIgnore Authentication authentication) {

    	Map<String, Object> resultMap = new HashMap<>();
    	
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        List<ShowMapping> shows=userService.findPreferShow(user);
        if(shows==null) {
        	resultMap.put("message", "fail");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        resultMap.put("message", "success");
        resultMap.put("items",shows);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    
    @PostMapping("/show/{id}")
    @ApiOperation(value = "회원이 조회한 공연목록 추가 ", notes = "회원의 조회한 공연목록을 추가한다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "추가 성공  "),
            @ApiResponse(code = 401, message = "추가 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> ViewedAdd(@ApiIgnore Authentication authentication,@PathVariable("id") Long showId) {

    	Map<String, Object> resultMap = new HashMap<>();
    	
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
      
        Viewed viewed=userService.addViewed(user.getId(), showId);
        if(viewed==null) {
        	 resultMap.put("message", "fail");
             return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
       
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    @GetMapping("/profile/watchHistory")
    @ApiOperation(value = "회원조회한 공연목록 조회 ", notes = "회원이 조회한 공연목록을 조회한다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회성공  "),
            @ApiResponse(code = 401, message = "조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> ViewedList(@ApiIgnore Authentication authentication) {
    	Map<String, Object> resultMap = new HashMap<>();
    	
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
//        List<ShowMapping> shows=userService.findViewedShow(user);
        List<?> shows=userService.findViewedShow(user);
        if(shows==null) {
        	resultMap.put("message", "fail");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        resultMap.put("message", "success");
        resultMap.put("items",shows);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    @DeleteMapping("/show/{id}/preference")
    @ApiOperation(value = "회원 관심목록 및 유저 선호 태그 삭제", notes = "회원의 관심 공연 및 해당 공연의 선호 태그를 삭제한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "삭제 성공  "),
            @ApiResponse(code = 400, message = "삭제 실패/중복된 공연 등록"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> FavoriteRemove(@ApiIgnore Authentication authentication,@PathVariable("id") Long showId) {

    	Map<String, Object> resultMap = new HashMap<>();
    	
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();

        System.out.println(user.getId()+"   "+ showId);
        try {
			userService.removeFavorite(user.getId(), showId);
            userService.removeUserTag(user.getId(), showId);
		} catch (Exception e) {
			e.printStackTrace();
			resultMap.put("message", "fail");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
		}
       
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    @GetMapping("/show/{id}/preference")
    @ApiOperation(value = "회원 관심목록 여부 확인 ", notes = "회원이 해당하는 공연을 관심표시했는지 아닌지 여부를 확인한다.  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "여부확인 성공  "),
            @ApiResponse(code = 400, message = "여부확인 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> FavoriteGet(@ApiIgnore Authentication authentication,@PathVariable("id") Long showId) {

    	Map<String, Object> resultMap = new HashMap<>();
    	
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        
        boolean result=userService.findFavoriteByShowAndUser(user.getId(), showId);
        resultMap.put("message", "success");
		resultMap.put("isFavorite", result);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    
    @GetMapping("/profile")
    @ApiOperation(value = "프로필 정보 조회 ", notes = "회원의 프로필 정보를 조회한다.")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공  "),
            @ApiResponse(code = 400, message = "조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> userDetail(@ApiIgnore Authentication authentication) {

    	Map<String, Object> resultMap = new HashMap<>();
    	
        SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        
        user=userService.findUserByEmail(user.getEmail());
        if(user==null) {
        	resultMap.put("message", "fail");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
       
        resultMap.put("userId", user.getId());
        resultMap.put("nickname", user.getNickname());
        resultMap.put("preferTag", userService.findUserTagByUserId(user.getId()));
        resultMap.put("profileImg", user.getProfileImg());
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);
        }
    
    
    @PatchMapping("/profile")
    @ApiOperation(value = "프로필 정보 수정", notes = "유저의 프로필 정보를 수정한다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 정보 수정 성공"),
            @ApiResponse(code = 401, message = "유저 정보 수정 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> userModify(@ApiIgnore Authentication authentication,
    		@RequestBody @ApiParam(value = "수정할 닉네임 정보", required = true) UserModifyProfilReq userModifyProfilReq) {
    	Map<String, Object> resultMap = new HashMap<>();
    	
    	SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        
        user=userService.findUserByEmail(user.getEmail());
        
        user=userService.modifyNickname(user, userModifyProfilReq.getNewNickname());
        if(user==null) {
        	resultMap.put("message", "fail");
            return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.BAD_REQUEST);
        }
        
        resultMap.put("message", "success");
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    
    @GetMapping("/profile/reviewList")
    @ApiOperation(value = "유저 리뷰 조회", notes = "유저가 작성한 리뷰를 조회한다. ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "유저 정보 조회 성공"),
            @ApiResponse(code = 401, message = "유저 정보 조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> userReviewList(@ApiIgnore Authentication authentication) {
    	Map<String, Object> resultMap = new HashMap<>();
    	
    	SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
             
        List<UserReviewMapping> reviews=userService.findUserReview(user.getId());
        resultMap.put("message", "success");
        resultMap.put("items",reviews);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    @GetMapping("/review/ratingStars")
    @ApiOperation(value = "유저 리뷰 별점대 별 개수 목록 조회 ", notes = "유저가 작성한 리뷰의 평점별 개수를 조회한다.  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 401, message = "조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> userReviewRatingCntList(@ApiIgnore Authentication authentication) {
    	Map<String, Object> resultMap = new HashMap<>();
    	
    	SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        
        user=userService.findUserByEmail(user.getEmail());
        
        List<?> result=userService.findUserReviewRatingCnt(user);
        resultMap.put("message", "success");
        resultMap.put("items",result);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    @GetMapping("/show/preference/tag")
    @ApiOperation(value = "관심 공연 태그별 개수 목록 조회 ", notes = "유저의 관심공연의 태그별 개수를 조회한다.  ")
    @ApiResponses({
            @ApiResponse(code = 200, message = "조회 성공"),
            @ApiResponse(code = 401, message = "조회 실패"),
            @ApiResponse(code = 500, message = "서버 오류")
    })
    public ResponseEntity<Map<String, Object>> userFavoriteShowTagList(@ApiIgnore Authentication authentication) {
    	Map<String, Object> resultMap = new HashMap<>();
    	
    	SsafyUserDetails userDetails = (SsafyUserDetails) authentication.getDetails();
        User user =userDetails.getUser();
        
        user=userService.findUserByEmail(user.getEmail());
        
        List<?> result=userService.findFavoriteShowTagCnt(user);
        resultMap.put("message", "success");
        resultMap.put("items",result);
        return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.ACCEPTED);

    }
    
    
    
}
