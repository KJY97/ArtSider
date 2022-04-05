package com.ssafy.myapp.api.service;


import com.ssafy.myapp.api.response.ArtCenterDetailsGetRes;
import com.ssafy.myapp.api.response.PopularShowListGetRes;
import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;
import com.ssafy.myapp.db.entity.*;
import com.ssafy.myapp.db.mapping.ExpectRatingMapping;
import com.ssafy.myapp.db.mapping.ShowListMapping;
import com.ssafy.myapp.db.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService{

    private final ArtCenterRepository artCenterRepository;
    private final CastingListRepository castingListRepository;
    private final NoticeImgRepository noticeImgRepository;
    private final PopularShowRepository popularShowRepository;
    private final UserBasedRepository userBasedRepository;
    private final RelatedShowRepository relatedShowRepository;
    private final ShowRepository showRepository;
    private final ShowDetailImgRepository showDetailImgRepository;
    private final ExpectRatingRepository expectRatingRepository;
    private final UserRepository userRepository;


    // 검색 기능(제목)
    @Override
    @Transactional
    public List<ShowListGetRes> findShowName(String keyword) {
        List<ShowListMapping> shows = showRepository.findByShowNameContaining(keyword);
        List<ShowListGetRes> showList = new ArrayList<>();

        if (shows.isEmpty()) return showList;

        for (ShowListMapping show : shows) {
            ShowListGetRes showInfo = new ShowListGetRes(show);
            showList.add(showInfo);
        }
        return showList;
    }

    // 카테고리별 전체 공연 목록 조회
    @Override
    public List<ShowListGetRes> findShowCategoryAllList(String category) {
        List<ShowListGetRes> showCategoryAllList = new ArrayList<ShowListGetRes>();
        List<ShowListMapping> showAllList = showRepository.findAllBy();

        for (ShowListMapping show : showAllList) {
            if (show.getCategory().equals(category)) {
                ShowListGetRes showInfo = new ShowListGetRes(show);
                showCategoryAllList.add(showInfo);
            }
        }
        return showCategoryAllList;
    }

    // 개막 예정 공연 목록
    @Override
    public List<ShowListGetRes> findShowStartList() throws ParseException {
        List<ShowListGetRes> showStartList = new ArrayList<ShowListGetRes>();
        List<ShowListMapping> showAllList = showRepository.findAllBy();

        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",  Locale.KOREA );
        String dateNow = dateFormat.format(today);
        Date now = new Date(dateFormat.parse(dateNow).getTime());

        // 오늘 날짜 기준으로 걸러내고 추가하기
        for (ShowListMapping show : showAllList) {
            Date startDate = new Date(dateFormat.parse(show.getStartDate()).getTime());

            int compare = startDate.compareTo(now);

            if (compare > 0) {
                ShowListGetRes showInfo = new ShowListGetRes(show);
                showStartList.add(showInfo);
            }
        }
        // 공연 시작 날짜 정렬 -> 같은 날짜는 종료날짜로 다시 정렬, null값은 오픈런
        showStartList.sort(Comparator.comparing(ShowListGetRes::getStartDate).thenComparing(ShowListGetRes::getEndDate, Comparator.nullsFirst(Comparator.naturalOrder())));

        List<ShowListGetRes> showList = showStartList.subList(0, 20);
        return showList;
    }

    // 카테고리별 개막 예정 목록
    @Override
    public List<ShowListGetRes> findShowCategoryStartList(String category) throws ParseException {
        List<ShowListGetRes> showCategoryStartList = new ArrayList<ShowListGetRes>();
        List<ShowListMapping> showAllList = showRepository.findAllBy();

        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",  Locale.KOREA );
        String dateNow = dateFormat.format(today);
        Date now = new Date(dateFormat.parse(dateNow).getTime());

        // 오늘 날짜 기준으로 걸러내고 추가하기
        for (ShowListMapping show : showAllList) {
            if (show.getCategory().equals(category)) {
                Date startDate = new Date(dateFormat.parse(show.getStartDate()).getTime());

                int compare = startDate.compareTo(now);

                if (compare > 0) {
                    ShowListGetRes showInfo = new ShowListGetRes(show);
                    showCategoryStartList.add(showInfo);
                }
            }
        }
        showCategoryStartList.sort(Comparator.comparing(ShowListGetRes::getStartDate).thenComparing(ShowListGetRes::getEndDate, Comparator.nullsLast(Comparator.naturalOrder())));
        List<ShowListGetRes> showList = showCategoryStartList.subList(0, 20);
        return showList;
    }

    // 종료 임박 공연 목록
    @Override
    public List<ShowListGetRes> findShowEndList() throws ParseException {
        List<ShowListGetRes> showEndList = new ArrayList<ShowListGetRes>();
        List<ShowListMapping> showAllList = showRepository.findAllBy();
        // 오늘 날짜
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",  Locale.KOREA );
        String dateNow = dateFormat.format(today);
        Date now = new Date(dateFormat.parse(dateNow).getTime());

        for (ShowListMapping show : showAllList) {
            if (show.getEndDate() != null) {
                Date endDate = new Date(dateFormat.parse(show.getEndDate()).getTime());

                int compare = endDate.compareTo(now);

                if (compare > 0) {
                    ShowListGetRes showInfo = new ShowListGetRes(show);
                    showEndList.add(showInfo);
                }
            }
        }
        showEndList.sort(Comparator.comparing(ShowListGetRes::getEndDate).thenComparing(ShowListGetRes::getStartDate));
        List<ShowListGetRes> showList = showEndList.subList(0, 20);
        return showList;
    }

    // 카테고리별 종료 임박 공연 목록
    @Override
    public List<ShowListGetRes> findShowCategoryEndList(String category) throws ParseException {
        List<ShowListGetRes> showCategoryEndList = new ArrayList<ShowListGetRes>();
        List<ShowListMapping> showAllList = showRepository.findAllBy();
        // 오늘 날짜
        Date today = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",  Locale.KOREA );
        String dateNow = dateFormat.format(today);
        Date now = new Date(dateFormat.parse(dateNow).getTime());

        for (ShowListMapping show : showAllList) {
            if (show.getCategory().equals(category) && show.getEndDate() != null) {
                Date endDate = new Date(dateFormat.parse(show.getEndDate()).getTime());

                int compare = endDate.compareTo(now);

                if (compare > 0) {
                    ShowListGetRes showInfo = new ShowListGetRes(show);

                    showCategoryEndList.add(showInfo);
                }
            }
        }
        showCategoryEndList.sort(Comparator.comparing(ShowListGetRes::getEndDate).thenComparing(ShowListGetRes::getStartDate));
        List<ShowListGetRes> showList = showCategoryEndList.subList(0, 20);
        return showList;
    }

    // 공연 추천(사용자간의 유사도 추천)
    @Override
    public List<ShowListGetRes> findShowRecommendationList(User user) {

        List<ShowListGetRes> showRecommendationList = new ArrayList<>();
        List<UserBased> recommendationList = userBasedRepository.findByUserId(user);

        for (UserBased recommendation : recommendationList) {
            ShowListMapping show = showRepository.findByIdEquals(recommendation.getShow().getId());
            ShowListGetRes recommendationShowInfo = new ShowListGetRes(show);
            showRecommendationList.add(recommendationShowInfo);

        }
        return showRecommendationList;
    }

    // 연관 공연 추천
    @Override
    public List<ShowListGetRes> findShowRelatedList(Long showId) {
        List<ShowListGetRes> showRelatedList = new ArrayList<>();
        List<RelatedShow> showList = relatedShowRepository.findByShowId(showId);
        for (RelatedShow show : showList) {
            ShowListMapping showInfo = showRepository.findByIdEquals(show.getRelatedShowId().getId());
            ShowListGetRes relatedShowInfo = new ShowListGetRes(showInfo);
            showRelatedList.add(relatedShowInfo);
        }
        return showRelatedList;
    }

    // 전체 인기 공연 목록
    @Override
    public List<PopularShowListGetRes> findPopularShowList() {

        List<PopularShowListGetRes> popularShowAllList = new ArrayList<PopularShowListGetRes>();
        List<PopularShow> popularShowList = popularShowRepository.findAll();

        for (PopularShow popularShow : popularShowList) {
            if (popularShow.getRank() < 5) {
                ShowListMapping showInfo = showRepository.findByShowId(popularShow.getShowId());
                PopularShowListGetRes popularShowInfo = new PopularShowListGetRes(popularShow);

                popularShowInfo.setId(showInfo.getId());
                popularShowInfo.setShowName(showInfo.getShowName());
                popularShowInfo.setPosterPath(showInfo.getPosterPath());
                popularShowInfo.setCategory(showInfo.getCategory());
                popularShowInfo.setStartDate(showInfo.getStartDate());
                popularShowInfo.setEndDate(showInfo.getEndDate());
                popularShowInfo.setArtCenterName(showInfo.getArtCenterName());

                popularShowAllList.add(popularShowInfo);
            }
        }

        // 정렬하기(랭크, pk(뮤지컬 < 콘서트 < 연극 < 클래식/무용 < 아동/가족))
        popularShowAllList.sort(Comparator.comparing(PopularShowListGetRes::getRank).thenComparing(PopularShowListGetRes::getId));

        return popularShowAllList;
    }

    // 카테고리별 인기 공연 목록
    @Override
    public List<PopularShowListGetRes> findPopularShowCategoryList(String category) {

        List<PopularShowListGetRes> popularShowCategoryAllList = new ArrayList<PopularShowListGetRes>();
        List<PopularShow> popularShowCategoryList = popularShowRepository.findAll();

        for (PopularShow popularShow : popularShowCategoryList) {
            ShowListMapping showInfo = showRepository.findByShowId(popularShow.getShowId());
            if (showInfo.getCategory().equals(category)) {

                PopularShowListGetRes popularShowInfo = new PopularShowListGetRes(popularShow);

                popularShowInfo.setId(showInfo.getId());
                popularShowInfo.setShowName(showInfo.getShowName());
                popularShowInfo.setPosterPath(showInfo.getPosterPath());
                popularShowInfo.setCategory(showInfo.getCategory());
                popularShowInfo.setStartDate(showInfo.getStartDate());
                popularShowInfo.setEndDate(showInfo.getEndDate());
                popularShowInfo.setArtCenterName(showInfo.getArtCenterName());

                popularShowCategoryAllList.add(popularShowInfo);
            }
        }
        popularShowCategoryAllList.sort(Comparator.comparing(PopularShowListGetRes::getRank));

        return popularShowCategoryAllList;
    }

    // 공연 상세 조회
    @Override
    public List<ShowDetailsGetRes> findShowDetails(Long id) throws NoSuchElementException {

        ShowListMapping show = showRepository.findByIdEquals(id);
        ArtCenter artCenter = artCenterRepository.findByArtCenterName(show.getArtCenterName());
        List<CastingList> casting = castingListRepository.findByShowId(show.getShowId());
        List<NoticeImg> notice = noticeImgRepository.findByShowId(show.getShowId());
        List<ShowDetailImg> showDetail = showDetailImgRepository.findByShowId(show.getShowId());
        List<ShowDetailsGetRes> showList = new ArrayList<>();
        ShowDetailsGetRes showInfo = new ShowDetailsGetRes(show);

        showInfo.setCastingLists(casting);
        showInfo.setNoticeImg(notice);
        showInfo.setShowDetailImg(showDetail);
        showInfo.setArtCenter(artCenter);

        showList.add(showInfo);
        return showList;
    }

    // 공연장 시설 조회
    @Override
    public List<ArtCenterDetailsGetRes> findArtCenterDetails(String artCenterName) throws NoSuchElementException {
        List<ArtCenterDetailsGetRes> artCenterList = new ArrayList<>();

        ArtCenter artCenter = artCenterRepository.findByArtCenterName(artCenterName);
        ArtCenterDetailsGetRes artCenterInfo = new ArtCenterDetailsGetRes();

        artCenterInfo.setId(artCenter.getId());
        artCenterInfo.setArtCenterName(artCenter.getArtCenterName());
        artCenterInfo.setArtCenterAddress(artCenter.getArtCenterAddress());
        artCenterInfo.setArtCenterTel(artCenter.getArtCenterTel());
        artCenterInfo.setArtCenterWeb(artCenter.getArtCenterWeb());

        artCenterList.add(artCenterInfo);
        return artCenterList;
    }

	@Override
	public ExpectRatingMapping findExpectRating(Long userId, Long showId) {
		
		ExpectRatingMapping rating=expectRatingRepository.findByUserAndShow(userRepository.findById(userId).get(), showRepository.findById(showId).get());
		
		return rating;
	}

}
