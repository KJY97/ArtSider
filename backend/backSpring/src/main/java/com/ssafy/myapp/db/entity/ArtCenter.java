package com.ssafy.myapp.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "art_center")
public class ArtCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String artCenterName;

    private String artCenterAddress;

    private String artCenterTel;

    private String artCenterWeb;

}
