package com.gmail.alisarrian.lamamultimedia.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
    String releasedData;

    @NotNull
    String writer;

    @NotNull
    String actors;

    @NotNull
    String serialGenre;

    @NotNull
    String description;

    @NotNull
    String iconUrl;

    @NotNull
    String imdbRating;

    @NotNull
    String watched;
}
