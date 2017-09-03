package com.gmail.alisarrian.lamamultimedia.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
//Lombok annotations
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue
    private int id;

    String movieTitle;
//    int year;
//    String genre;
//    String director;
//    String actors;
//    String plot;
//    String language;
//    Double imdbRating;
//    String imdbID;


}
