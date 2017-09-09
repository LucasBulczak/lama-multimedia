package com.gmail.alisarrian.lamamultimedia.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
////Lombok annotations
@NoArgsConstructor
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @NotNull
    @Size(min =3, max = 25)
    String title;

    @NotNull
    @Pattern(regexp = "^(19|20)\\d{2}$", message = "Add date from 1900 to 2099")
    String year;

    @ManyToOne
    Category category;

//    String director;
//    String actors;
//    String plot;
//    String language;
//    Double imdbRating;
//    String imdbID;

    public Movie(String title, String year) {
        this.title = title;
        this.year = year;
    }



}
