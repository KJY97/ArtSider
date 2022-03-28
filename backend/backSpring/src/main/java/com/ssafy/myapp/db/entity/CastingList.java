package com.ssafy.myapp.db.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "casting_list")
public class CastingList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String showId;

    @Column(nullable = false)
    private String name;

//    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "showId", insertable = false, updatable = false)
//    @JsonIgnore
//    private Show show;
//
//    public void setShow(Show show) {
//        if (this.show != null) {
//            this.show.getCastingLists().remove(this);
//        }
//        this.show = show;
//        show.getCastingLists().add(this);
//    }

}
