package com.ssafy.myapp.db.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class UserTag {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "tag_content")
	private String tag;
	private int weight;
	
	@ManyToOne
    @JoinColumn(name = "user_id")
	@JsonBackReference //추가
    private User user;

	public static UserTag createShowTag(User user, String tag, int weight) {
		UserTag userTag = new UserTag();
		userTag.setUser(user);
		userTag.setTag(tag);
		userTag.setWeight(weight);
		return userTag;
	}
}
