package com.dataguard.superhero.dao.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Objects;

@Getter
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

}
