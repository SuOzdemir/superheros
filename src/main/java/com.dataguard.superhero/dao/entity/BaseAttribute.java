package com.dataguard.superhero.dao.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.util.Objects;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public class BaseAttribute {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private long id;

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "SUPERHERO_ID", nullable = false)
    @JsonIgnore
    private Superhero superhero;

    @Column(name = "NAME")
    private String name;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass ( ) != o.getClass ( )) return false;
        BaseAttribute that = (BaseAttribute) o;
        return id == that.getId ( ) && superhero.equals ( that.getSuperhero ( ) );
    }

    @Override
    public int hashCode() {
        return Objects.hash ( id, superhero );
    }

//    public BaseAttribute( int id,  String name) {
//        this.id = id;
//        this.name = name;
//    }
}
