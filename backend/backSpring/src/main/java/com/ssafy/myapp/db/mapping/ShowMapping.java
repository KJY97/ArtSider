package com.ssafy.myapp.db.mapping;

import com.ssafy.myapp.db.entity.Show;


public interface ShowMapping {
	showRemoveList getShow();
	
	interface showRemoveList{
		Long getId();
		String getShowName();
		String getShowId();
		String getStartDate();
		String getEndDate();
		String getOpenRun();
		String getProducer();
		String getAge();
		String getRuntime();
		String getPrice();
		String getPosterPath();
		String getShowDay();
		String getCategory();
		String getArtCenterName();
	}

}
