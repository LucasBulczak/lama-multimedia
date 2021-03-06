package com.gmail.alisarrian.lamamultimedia.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
////Lombok annotations
@NoArgsConstructor
@Getter
@Setter
public class Serial {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @NotNull
    @Size(min =3, max = 25)
    String title;

    @NotNull
    String year;

    @NotNull
    String releasedDate;

    @NotNull
    String writer;

    @NotNull
    String actors;

    @NotNull
    String serialGenre;

    @NotNull
    @Size(min =3, max = 500)
    String description;

    @NotNull
    String iconUrl;

    @NotNull
    String imdbRating;

    @NotNull
    String watched;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "serial_id")
    private List<Season> seasons = new ArrayList<>();

}
