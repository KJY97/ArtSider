package com.ssafy.myapp.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.ArrayList;
import java.util.List;

@Getter    // getter 자동 추가
@Entity    // entity
@NoArgsConstructor    // 기본생성자 자동 추가
@Table(name = "performance",indexes = @Index(columnList = "showId"))
public class Show {

    // 공연정보는 수정하지 않기 때문에 setter 생성x

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // 기본키 생성을 DB에 위임한다.MySQL의 경우 AUTO_INCREMENT를 사용하여 기본키를 생성한다.
    private Long id;    // PK(auto increment) 공연 id

    @Column(nullable = false)    // null 허용 불가능
    private String showName;    // 공연이름

    @Column(nullable = false)
    private String showId;    // 인터파크 기준 공연 id => 리뷰, 출연진 join 하기 위함.

    @Column(nullable = false)
    private String startDate;    // 공연시작일

    private String endDate;    // 공연 종료일

    private String openRun;    // 오픈런 여부

    @Column(nullable = false)
    private String producer;    // 제작사

    @Column(nullable = false)
    private String age;    // 관람등급

    @Column(nullable = false)
    private String runtime;    // 공연시간

    @Column(length = 500)
    private String price;    // 가격정보

    @Column(length = 500, nullable = false)
    private String posterPath;    // 포스터 경로

    @Column(columnDefinition = "TEXT")
    private String showDay;    // 공연상세일시

    @Column(nullable = false)
    private String category;    // 공연 카테고리

    private String artCenterName;    // 공연장

    @Column(nullable = false)
    private String menRate;    // 남성 이용자 예매율

    @Column(nullable = false)
    private String womenRate;    // 여성 이용자 예매율

    @OneToMany(mappedBy = "show")
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "show")
    private List<Expectation> expectations = new ArrayList<>();

    @OneToMany(mappedBy = "show")
    private List<ShowTag> showTags = new ArrayList<>();


    @OneToMany(mappedBy = "show")
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference //추가
    private List<Favorite> favorite = new ArrayList<>();

    @OneToMany(mappedBy = "show")//뒤의 fetch 안했을 시에 에러나서 추가해줌
    @LazyCollection(LazyCollectionOption.FALSE)
    @JsonBackReference //추가
    private List<Viewed> viewed = new ArrayList<>();

}

