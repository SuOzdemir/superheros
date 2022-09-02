package com.dataguard.superhero.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "superheros")
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "alias")
    private String alias;

    @Column(name = "name")
    private String name;

    @Column(name = "origin")
    private String origin;

    @OneToMany(mappedBy = "superhero", cascade = CascadeType.ALL)
    private List<SuperheroWeapon> weapons;

    @OneToMany(mappedBy = "superhero", cascade = CascadeType.ALL)
    private List<SuperheroAssociation> associations;

    @OneToMany(mappedBy = "superhero", cascade = CascadeType.ALL)
    private List<SuperheroPower> powers;


}
