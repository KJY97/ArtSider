package com.ssafy.myapp.api.service;


import com.ssafy.myapp.db.entity.*;
import com.ssafy.myapp.db.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.ssafy.myapp.api.request.UserRegisterPostReq;

import com.ssafy.myapp.db.entity.Favorite;
import com.ssafy.myapp.db.entity.Review;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.entity.ShowTag;
import com.ssafy.myapp.db.entity.User;
import com.ssafy.myapp.db.entity.UserTag;
import com.ssafy.myapp.db.entity.Viewed;
import com.ssafy.myapp.db.mapping.ShowMapping;
import com.ssafy.myapp.db.mapping.UserReviewMapping;
import com.ssafy.myapp.db.repository.FavoriteRepository;
import com.ssafy.myapp.db.repository.ReviewRepository;
import com.ssafy.myapp.db.repository.ShowRepository;
import com.ssafy.myapp.db.repository.ShowTagRepository;
import com.ssafy.myapp.db.repository.UserRepository;
import com.ssafy.myapp.db.repository.UserTagRepository;
import com.ssafy.myapp.db.repository.ViewedRepository;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
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
	ReviewRepository reviewRepository;
	
	@Autowired
	ShowTagRepository showTagRepository;

	@Autowired
	UserTagRepository userTagRepository;

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
		List<UserTag> userTag=userTagRepository.findTop3ByUserIdOrderByWeightDesc(user.getId());
//		user.setUserTag(userTag); 
		return user;
	}
	
	@Override
	public List<UserTag> findUserTagByUserId(Long userId) {
		List<UserTag> userTag=userTagRepository.findTop3ByUserIdOrderByWeightDesc(userId);
		return userTag;
	}
	

	public boolean chkDplByEmail(String email) {
		if(userRepository.findUserByEmail(email).isPresent())
			return true;
		else return false;
	}

	@Override
	@Transactional
	public User addUser(UserRegisterPostReq userRegisterInfo) {
		User user = new User();

		user.setEmail(userRegisterInfo.getUserEmail());
		user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
		user.setNickname(userRegisterInfo.getUserName());
		user.setTelNum(userRegisterInfo.getTelnum());
		user.setCreateDate(LocalDateTime.now());

		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void modifyPassword(String email, String password) {
		User updateUser = userRepository.findUserByEmail(email).get();
		updateUser.setPassword(passwordEncoder.encode(password));
		userRepository.save(updateUser);
	}

	@Override
	public String createAuthNum() {
		Random rand = new Random();

		String numStr = ""; //????????? ????????? ??????
		for(int i=0;i<6;i++) {
			//0~9 ?????? ?????? ??????
			String ran = Integer.toString(rand.nextInt(10));
			if(!numStr.contains(ran)) {
				//????????? ?????? ????????? numStr??? append
				numStr += ran;
			}else {
				//????????? ????????? ???????????? ????????? ?????? ????????????
				i-=1;
			}
		}
		return numStr;
	}

	@Override
	@Transactional
	public void removeUser(String email) {
		User deleteUser = userRepository.findUserByEmail(email).get();
		userRepository.deleteById(deleteUser.getId());
	}

	@Override
	public String sendNewPass(String email) {
		String uuid = UUID.randomUUID().toString();
		String setfrom = "artsider_ssafy@naver.com";
		String tomail = email;// ????????????
		String title = "[Artsider] ?????? ???????????? ????????? ?????????";
		String content =
				System.getProperty("line.separator") + "??????????????? ?????????"
						+ System.getProperty("line.separator") + "?????? ??????????????? " + uuid + " ?????????."
						+ System.getProperty("line.separator") + "???????????? ????????? ??? ??????????????? ??????????????? :)";

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
		String tomail = email;// ????????????
		String title = "[Artsider] ??????????????? ?????????????????????.";
		String content =
				System.getProperty("line.separator") + "??????????????? "
						+ System.getProperty("line.separator") + "?????? ????????? " + emailNumber + " ?????????."
						+ System.getProperty("line.separator") + "????????????????????? ??????????????? ??????????????????.";

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
	@Transactional
	public Favorite addFavorite(Long userId, Long showId) throws Exception {
		Favorite favorite = new Favorite();
		favorite.setShow(showRepository.findById(showId).get());
		favorite.setUser(userRepository.findById(userId).get());

		favorite=favoriteRepository.save(favorite);

		return favorite;
	}

	
	@Override
	public List<ShowMapping> findPreferShow(User user) {
		List<ShowMapping> favorite =favoriteRepository.findByUser(user);
		return favorite;
	}
	
	@Override
	@Transactional
	public Viewed addViewed(Long userId, Long showId) {
		// TODO Auto-generated method stub
		Viewed viewed = new Viewed();
		viewed.setUser(userRepository.findById(userId).get());
		viewed.setShow(showRepository.findById(showId).get());
		
		viewed=viewedRepository.save(viewed);
		return viewed;
	}
	
	@Override
	public List<?> findViewedShow(User user) {
//		List<ShowMapping> Viewed =viewedRepository.findByUser(user);
		List<?> Viewed = viewedRepository.findByUserIdDistinctOderById(user.getId());
		return Viewed;
	}

	@Override
	@Transactional
	public void removeFavorite(Long userId, Long showId) throws Exception {
		Show show=showRepository.findById(showId).get();
		User user=userRepository.findById(userId).get();
		Optional<Favorite> favorite=favoriteRepository.findTop1ByUserAndShow(user, show);

		if(favorite.isPresent()) {
			favoriteRepository.deleteById(favorite.get().getId());
		}
	}


	public String saveUploadedFiles(final MultipartFile thumbnail) throws IOException {
		String absolutePath = new File("").getAbsolutePath() + "\\images\\";
        File file = new File("images");
        // ????????? ????????? ??????????????? ???????????? ?????? ??????
        if(!file.exists()){
            // mkdir() ????????? ?????? ?????? ?????? ??????????????? ???????????? ?????? ??? ???????????? ??????
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
	@Transactional
	public User modifyUserProfileImg(User user,String profileImg) {
		User updateUser= userRepository.findById(user.getId()).get();
		updateUser.setProfileImg(profileImg);
		return userRepository.save(updateUser);
	}

	@Override
	@Transactional
	public User modifyNickname(User user, String nickname) {
		// TODO Auto-generated method stub
		User updateUser= userRepository.findById(user.getId()).get();
		updateUser.setNickname(nickname);
		return userRepository.save(updateUser);
	}

	@Override
	public List<UserReviewMapping> findUserReview(Long userId) {
		List<UserReviewMapping> reviews=reviewRepository.findTop20ByUserIdOrderByIdDesc(userId);
		return reviews;
	}

	@Override
	public List<?> findUserReviewRatingCnt(User user) {
		List<?> result=reviewRepository.findReviewRatingCnt(user.getId());
		
		return result;
	}

	@Override
	public List<?> findFavoriteShowTagCnt(User user) {
		List<?> result=showTagRepository.findFavoriteShowTagCnt(user.getId());
		
		return result;
	}

	@Override
	public boolean findFavoriteByShowAndUser(Long userId, Long showId) {
		Favorite favorite=favoriteRepository.findByUserAndShow(userRepository.findById(userId).get(), showRepository.findById(showId).get());
		if(favorite==null) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional
	public void addUserTag(Long userId, Long showId) throws Exception {

		List<ShowTag> showTagList = showTagRepository.findByShowId(showId);
		User findUser = userRepository.findById(userId).get();

		for (ShowTag showTag : showTagList) {
			String tag = showTag.getTagContent();

			UserTag userTag = userTagRepository.findByUserAndTag(findUser, tag);
			if(userTag != null) {
				userTag.setWeight(userTag.getWeight() + 1);
			}
			else {
				userTag = UserTag.createShowTag(findUser, tag, 1);
			}
			userTagRepository.save(userTag);
		}
	}

	@Override
	@Transactional
	public void removeUserTag(Long userId, Long showId) throws Exception {
		User user=userRepository.findById(userId).get();
		List<ShowTag> showTags=showTagRepository.findByShowId(showId);
		for (int i = 0; i < showTags.size(); i++) {
			UserTag userTag= userTagRepository.findByUserAndTag(user, showTags.get(i).getTagContent());
			//weight -1
			int newWeight=userTag.getWeight()-1;
			if(newWeight==0) {
				userTagRepository.delete(userTag);
			}else {
				userTag.setWeight(newWeight);
				userTagRepository.save(userTag);
			}
		}
	}
}

