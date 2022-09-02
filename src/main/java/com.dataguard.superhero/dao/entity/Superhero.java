package com.dataguard.superhero.dao.entity;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "superheros")
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "alias")
    private String alias;

    @Column(name = "name")
    private String name;

    @Column(name = "origin")
    private String origin;

    @OneToMany(mappedBy = "superhero", cascade = CascadeType.ALL)
    @Singular("weapon")
    private List<SuperheroWeapon> weaponList;

    @OneToMany(mappedBy = "superhero", cascade = CascadeType.ALL)
    @Singular("association")
    private List<SuperheroAssociation> associationList;

    @OneToMany(mappedBy = "superhero", cascade = CascadeType.ALL)
    @Singular("power")
    private List<SuperheroPower> powerList;

    public void setWeaponList(List<String> strList) {
        if (strList == null) return;
        this.weaponList = strList.stream ( ).map ( (s) -> SuperheroWeapon.builder ( ).name ( s ).superhero ( this ).build ( ) ).collect ( Collectors.toList ( ) );
    }

    public void setPowerList(List<String> strList) {
        if (strList == null) return;
        this.powerList = strList.stream ( ).map ( (s) -> SuperheroPower.builder ( ).name ( s ).superhero ( this ).build ( ) ).collect ( Collectors.toList ( ) );
    }

    public void setAssociationList(List<String> strList) {
        if (strList == null) return;
        this.associationList = strList.stream ( ).map ( (s) -> SuperheroAssociation.builder ( ).name ( s ).superhero ( this ).build ( ) ).collect ( Collectors.toList ( ) );
    }

}
