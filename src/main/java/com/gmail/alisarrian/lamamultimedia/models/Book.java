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
@NoArgsConstructor
@Getter
@Setter
public class Book {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @NotNull
    @Size(min =3, max = 25)
    String title;

    @NotNull
    String releasedDate;

    @NotNull
    String writer;

    @NotNull
    @Size(min =3, max = 500)
    String description;

    @NotNull
    String iconUrl;
}
