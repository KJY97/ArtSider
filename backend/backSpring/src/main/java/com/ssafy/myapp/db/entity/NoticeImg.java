package com.ssafy.myapp.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "notice_img")
public class NoticeImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String showId;

    @Column(length = 500, nullable = false)
    private String imgUrl;
}
