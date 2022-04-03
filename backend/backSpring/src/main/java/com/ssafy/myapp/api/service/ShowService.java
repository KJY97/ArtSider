package com.ssafy.myapp.api.service;


import com.ssafy.myapp.api.response.ArtCenterDetailsGetRes;
import com.ssafy.myapp.api.response.PopularShowListGetRes;
import com.ssafy.myapp.api.response.ShowDetailsGetRes;
import com.ssafy.myapp.api.response.ShowListGetRes;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

public interface ShowService {
    List<ShowListGetRes> findShowName(String keyword);
    List<ShowListGetRes> findShowCategoryAllList(String category);
    List<ShowListGetRes> findShowStartList() throws ParseException;
    List<ShowListGetRes> findShowCategoryStartList(String category) throws ParseException;
    List<ShowListGetRes> findShowEndList() throws ParseException;
    List<ShowListGetRes> findShowCategoryEndList(String category) throws ParseException;
    List<ShowListGetRes> findShowRecommendationList(Long userId);
    List<PopularShowListGetRes> findPopularShowList();
    List<PopularShowListGetRes> findPopularShowCategoryList(String category);
    List<ShowDetailsGetRes> findShowDetails(Long id) throws NoSuchElementException;
    List<ArtCenterDetailsGetRes> findArtCenterDetails(String artCenterName) throws NoSuchElementException;
}
