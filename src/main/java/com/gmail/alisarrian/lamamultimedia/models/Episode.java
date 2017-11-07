package com.gmail.alisarrian.lamamultimedia.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Episode {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @NotNull
    private String episodeNumber;

    @NotNull
    String dateOfReleased;

    @NotNull
    String description;

    @NotNull
    String iconUrl;

    @ManyToOne
    Season season;
}
