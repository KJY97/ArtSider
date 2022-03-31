package com.ssafy.myapp.db.mapping;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ssafy.myapp.db.entity.User;

public interface UserReviewMapping {
	Long getId();
	String getTitle();
	String getContents();
	int getRating();
	LocalDateTime getCreateDate();
	Long getShowId();

}
