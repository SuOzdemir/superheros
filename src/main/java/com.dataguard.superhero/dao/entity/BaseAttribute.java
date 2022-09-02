package com.dataguard.superhero.dao.entity;

import lombok.*;

import javax.persistence.*;



@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUPERHERO_ID")
    private Superhero superhero;

    private String name;
}
