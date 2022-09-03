package com.dataguard.superhero.dao.entity;

import com.dataguard.superhero.web.SuperheroAttributeType;
import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "superheros")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,    property = "id")
public class Superhero {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private long id;

    @Column(name = "alias", unique = true)
    @NotBlank
    private String alias;

    @Column(name = "name", unique = true)
    @NotBlank
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
        if (CollectionUtils.isEmpty ( strList)) return;
        this.weaponList = strList.stream ( ).map ( this::weaponFrom ).collect ( Collectors.toList ( ) );
    }

    public void setPowerList(List<String> strList) {
        if (CollectionUtils.isEmpty ( strList)) return;
        this.powerList = strList.stream ( ).map ( this::powerFrom ).collect ( Collectors.toList ( ) );
    }

    public void setAssociationList(List<String> strList) {
        if (CollectionUtils.isEmpty ( strList)) return;
        this.associationList = strList.stream ( ).map ( this::associationFrom ).collect ( Collectors.toList ( ) );
    }

    public void addAttributeInDifferentType(SuperheroAttributeType type, String nameParam) {
        switch (type) {
            case ASSOCIATION:
                this.associationList.add ( associationFrom ( nameParam ) );
                break;
            case WEAPON:
                this.weaponList.add ( weaponFrom ( nameParam ) );
                break;
            case POWER:
                this.powerList.add ( powerFrom ( nameParam ) );
                break;
        }
    }

    private SuperheroPower powerFrom(String name) {
        return SuperheroPower.builder ( ).name ( name ).superhero ( this ).build ( );
    }

    private SuperheroWeapon weaponFrom(String name) {
        return SuperheroWeapon.builder ( ).name ( name ).superhero ( this ).build ( );
    }

    private SuperheroAssociation associationFrom(String name) {
        return SuperheroAssociation.builder ( ).name ( name ).superhero ( this ).build ( );
    }
}
