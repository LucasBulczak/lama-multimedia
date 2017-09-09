package com.gmail.alisarrian.lamamultimedia.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @OneToMany
    @JoinColumn(name = "category_id")
    @Setter(AccessLevel.NONE)
    private List<Movie> movies = new ArrayList<>();

    public Category(String name) {
        this.name = name;
    }

}