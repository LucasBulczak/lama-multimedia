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
@NoArgsConstructor
@Getter
@Setter
public class Season {

    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private int id;

    @NotNull
    @Size(min = 3, max = 15)
    private String name;

    @ManyToOne
    Serial serial;

    @OneToMany(orphanRemoval = true)
    @JoinColumn(name = "season_id")
    private List<Episode> episodes = new ArrayList<>();
}
