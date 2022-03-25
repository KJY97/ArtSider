package com.ssafy.myapp.api.service;


import com.ssafy.myapp.api.response.ArtCenterDetailsGetRes;
import com.ssafy.myapp.api.response.PopularShowListGetRes;
import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;
import com.ssafy.myapp.db.entity.ArtCenter;
import com.ssafy.myapp.db.entity.PopularShow;
import com.ssafy.myapp.db.entity.Show;
import com.ssafy.myapp.db.repository.ArtCenterRepository;
import com.ssafy.myapp.db.repository.PopularShowRepository;
import com.ssafy.myapp.db.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowServiceImpl implements ShowService{

    private final ArtCenterRepository artCenterRepository;
    private final PopularShowRepository popularShowRepository;
    private final ShowRepository showRepository;

    // 전체 공연 목록 조회
    @Override
    public List<ShowListGetRes> getShowAllList() {
        return showRepository.findAll().stream().map(ShowListGetRes::new).collect(Collectors.toList());
    }

    // 카테고리별 전체 공연 목록 조회
    @Override
    public List<ShowListGetRes> getShowCategoryAllList(String category) {
        List<ShowListGetRes> showCategoryAllList = new ArrayList<ShowListGetRes>();
        List<Show> ShowAllList = showRepository.findAll();

        for (Show show : ShowAllList) {
            if (show.getCategory().equals(category)) {
                ShowListGetRes showInfo = new ShowListGetRes(show);
                showCategoryAllList.add(showInfo);
            }
        }
        return showCategoryAllList;
    }

    // 전체 인기 공연 목록
    @Override
    public List<PopularShowListGetRes> getPopularShowList() {

        List<PopularShowListGetRes> popularShowAllList = new ArrayList<PopularShowListGetRes>();
        List<PopularShow> popularShowList = popularShowRepository.findAll();

        for (PopularShow popularShow : popularShowList) {
            if (popularShow.getRank() < 5) {
                PopularShowListGetRes popularShowInfo = new PopularShowListGetRes(popularShow);
                popularShowAllList.add(popularShowInfo);
            }
        }

        // 정렬하기(랭크, pk(뮤지컬 < 콘서트 < 연극 < 클래식/무용 < 아동/가족))
        popularShowAllList.sort(Comparator.comparing(PopularShowListGetRes::getRank).thenComparing(PopularShowListGetRes::getId));

        return popularShowAllList;
    }

    // 카테고리별 인기 공연 목록()
    @Override
    public List<PopularShowListGetRes> getPopularShowCategoryList(String category) {

        List<PopularShowListGetRes> popularShowCategoryAllList = new ArrayList<PopularShowListGetRes>();
        List<PopularShow> popularShowCategoryList = popularShowRepository.findAll();

//        for (PopularShow popularShow : popularShowCategoryList) {
//            if (popularShow.getCategory().equals(category)) {
//                PopularShowListGetRes showInfo = new PopularShowListGetRes(popularShow);
//                popularShowCategoryAllList.add(showInfo);
//            }
//        }

//        popularShowCategoryAllList.sort(Comparator.comparing(PopularShowListGetRes::getRank));

        return popularShowCategoryAllList;
    }

    // 공연 상세 조회
    @Override
    public ShowDetailsGetRes getShowDetails(Long id) throws NoSuchElementException {

        Show show = showRepository.findById(id).get();
        ShowDetailsGetRes showInfo = new ShowDetailsGetRes();

        showInfo.setId(show.getId());
        showInfo.setShowId(show.getShowId());
        showInfo.setShowName(show.getShowName());
        showInfo.setStartDate(show.getStartDate());
        showInfo.setEndDate(show.getEndDate());
        showInfo.setOpenRun(show.getOpenRun());
        showInfo.setProducer(show.getProducer());
        showInfo.setAge(show.getAge());
        showInfo.setRuntime(show.getRuntime());
        showInfo.setPrice(show.getPrice());
        showInfo.setPosterPath(show.getPosterPath());
        showInfo.setShowDay(show.getShowDay());
        showInfo.setCategory(show.getCategory());
        showInfo.setArtCenterName(show.getArtCenterName());
        showInfo.setMenRate(show.getMenRate());
        showInfo.setWomenRate(show.getWomenRate());
        return showInfo;
    }

    // 공연장 시설 조회
    @Override
    public ArtCenterDetailsGetRes getArtCenterDetails(String artCenterName) throws NoSuchElementException {

        ArtCenter artCenter = artCenterRepository.findByArtCenterName(artCenterName);
        ArtCenterDetailsGetRes artCenterInfo = new ArtCenterDetailsGetRes();

        artCenterInfo.setId(artCenter.getId());
        artCenterInfo.setArtCenterName(artCenter.getArtCenterName());
        artCenterInfo.setArtCenterAddress(artCenter.getArtCenterAddress());
        artCenterInfo.setArtCenterTel(artCenter.getArtCenterTel());
        artCenterInfo.setArtCenterWeb(artCenter.getArtCenterWeb());

        return artCenterInfo;
    }

}
