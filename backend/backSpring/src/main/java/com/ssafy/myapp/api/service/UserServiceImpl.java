package com.ssafy.myapp.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.myapp.api.request.UserRegisterPostReq;
import com.ssafy.myapp.db.entity.Favorite;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.entity.Viewed;
import com.ssafy.myapp.db.mapping.ShowMapping;
import com.ssafy.myapp.db.repository.FavoriteRepository;
import com.ssafy.myapp.db.repository.ShowRepository;
import com.ssafy.myapp.db.repository.UserRepository;
import com.ssafy.myapp.db.repository.ViewedRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

/**
 *
 */
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ShowRepository showRepository;
	
	@Autowired
	ViewedRepository viewedRepository;
	
	@Autowired
	FavoriteRepository favoriteRepository;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	private JavaMailSender sender;


	@Override
	public User findUserByEmail(String email) {
		User user = userRepository.findUserByEmail(email).get();
		return user;
	}

	public boolean chkDplByEmail(String email) {
		if(userRepository.findUserByEmail(email).isPresent())
			return true;
		else return false;
	}

	@Override
	public User addUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();

		user.setEmail(userRegisterInfo.getEmail());
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setNickname(userRegisterInfo.getNickname());
		user.setTelNum(userRegisterInfo.getTelNum());
		user.setCreateDate(LocalDateTime.now());

		return userRepository.save(user);
	}

	@Override
	public void modifyPassword(String email, String password) {
		User updateUser = userRepository.findUserByEmail(email).get();
		updateUser.setPassword(passwordEncoder.encode(password));
		userRepository.save(updateUser);
	}

	@Override
	public String createAuthNum() {
		Random rand = new Random();

		String numStr = ""; //난수가 저장될 변수
		for(int i=0;i<6;i++) {
			//0~9 까지 난수 생성
			String ran = Integer.toString(rand.nextInt(10));
			if(!numStr.contains(ran)) {
				//중복된 값이 없으면 numStr에 append
				numStr += ran;
			}else {
				//생성된 난수가 중복되면 루틴을 다시 실행한다
				i-=1;
			}
		}
		return numStr;
	}

	@Override
	public void removeUser(String email) {
		User deleteUser = userRepository.findUserByEmail(email).get();
		userRepository.deleteById(deleteUser.getId());
	}

	@Override
	public String sendNewPass(String email) {
		String uuid = UUID.randomUUID().toString();
		String setfrom = "artsider_ssafy@naver.com";
		String tomail = email;// 받는사람
		String title = "[Artsider] 임시 비밀번호 이메일 입니다";
		String content =
				System.getProperty("line.separator") + "안녕하세요 회원님"
						+ System.getProperty("line.separator") + "임시 비밀번호는 " + uuid + " 입니다."
						+ System.getProperty("line.separator") + "로그인을 하시고 꼭 비밀번호를 바꿔주세요 :)";

		try {
			SimpleMailMessage simpleMessage = new SimpleMailMessage();
			simpleMessage.setFrom(setfrom);
			simpleMessage.setTo(tomail);
			simpleMessage.setSubject(title);
			simpleMessage.setText(content);
			sender.send(simpleMessage);

			modifyPassword(email, uuid);
			return uuid;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

	@Override
	public String sendAuthNum(String email) {
		String emailNumber = createAuthNum();
		String setfrom = "artsider_ssafy@naver.com";
		String tomail = email;// 받는사람
		String title = "[Artsider] 인증번호가 발송되었습니다.";
		String content =
				System.getProperty("line.separator") + "안녕하세요 "
						+ System.getProperty("line.separator") + "인증 번호는 " + emailNumber + " 입니다."
						+ System.getProperty("line.separator") + "아트사이더에서 인증번호를 입력해주세요.";

		try {
			SimpleMailMessage simpleMessage = new SimpleMailMessage();
			simpleMessage.setFrom(setfrom);
			simpleMessage.setTo(tomail);
			simpleMessage.setSubject(title);
			simpleMessage.setText(content);
			sender.send(simpleMessage);
			return emailNumber;


		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;

		} 
	}

	@Override
	public Favorite addFavorite(Long userId, Long showId) throws Exception {
		// TODO Auto-generated method stub
		Favorite favorite = new Favorite();
		favorite.setUser(userRepository.findById(userId).get());
		favorite.setShow(showRepository.findById(showId).get());
		
		
		favorite=favoriteRepository.save(favorite);
		return favorite;
	}

	
	@Override
	public List<ShowMapping> findPreferShow(User user) {
		List<ShowMapping> favorite =favoriteRepository.findByUser(user);
		return favorite;
	}
	
	@Override
	public Viewed addViewed(Long userId, Long showId) {
		// TODO Auto-generated method stub
		Viewed viewed = new Viewed();
		viewed.setUser(userRepository.findById(userId).get());
		viewed.setShow(showRepository.findById(showId).get());
		
		viewed=viewedRepository.save(viewed);
		return viewed;
	}
	
	@Override
	public List<ShowMapping> findViewedShow(User user) {
		List<ShowMapping> Viewed =viewedRepository.findByUser(user);
		return Viewed;
	}

	@Override
	public void removeFavorite(Long userId, Long showId) throws Exception {
		// TODO Auto-generated method stub
		Favorite favorite=null;
		favorite= favoriteRepository.findTop1ByUserAndShow(userRepository.findById(userId).get(), showRepository.findById(showId).get());
		
		
		favoriteRepository.delete(favorite);
	}
	
	public String saveUploadedFiles(final MultipartFile thumbnail) throws IOException {
		String absolutePath = new File("").getAbsolutePath() + "\\images\\";
        File file = new File("images");
        // 저장할 위치의 디렉토리가 존지하지 않을 경우
        if(!file.exists()){
            // mkdir() 함수와 다른 점은 상위 디렉토리가 존재하지 않을 때 그것까지 생성
            file.mkdirs();
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String current_date = simpleDateFormat.format(new Date());
		final byte[] bytes = thumbnail.getBytes();
		String newFileName=current_date+Long.toString(System.nanoTime()) +thumbnail.getOriginalFilename();
		final Path path = Paths.get(absolutePath + newFileName);
		Files.write(path, bytes);
		
		return newFileName;
	}

	@Override
	public User modifyUserProfileImg(User user,String profileImg) {
		User updateUser= userRepository.findById(user.getId()).get();
		updateUser.setProfileImg(profileImg);
		return userRepository.save(updateUser);
	}

	@Override
	public User modifyNickname(User user, String nickname) {
		// TODO Auto-generated method stub
		User updateUser= userRepository.findById(user.getId()).get();
		updateUser.setNickname(nickname);
		return userRepository.save(updateUser);
	}



}

