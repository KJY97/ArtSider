package com.ssafy.myapp.db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(indexes = @Index(name="unique_user_id_show_id", columnList = "user_id, show_id", unique = true))
public class Favorite {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonBackReference //추가
    private User user;

    @ManyToOne
    @JoinColumn(name = "show_id")
    @JsonBackReference //추가
    private Show show;


}
