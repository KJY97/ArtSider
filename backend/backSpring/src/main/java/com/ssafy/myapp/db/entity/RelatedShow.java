package com.ssafy.myapp.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;


import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "related_show")
public class RelatedShow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "show_id")
    private Show show;

    @ManyToOne
    @JoinColumn(name = "related_show_id")
    private Show relatedShowId;
}
