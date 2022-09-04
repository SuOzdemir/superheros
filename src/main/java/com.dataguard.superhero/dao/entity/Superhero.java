package com.dataguard.superhero.dao.entity;

import com.dataguard.superhero.web.SuperheroAttributeType;
import com.dataguard.superhero.web.request.SuperheroDTO;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@Entity
public class Superhero extends BaseEntity {

    @ElementCollection
    private List<String> weaponList;

    @ElementCollection
    private List<String> associationList;

    @ElementCollection
    private List<String> powerList;


    public void setWeapons(List<String> strList) {
        this.weaponList = copyListIfExists ( strList );
    }

    public void setPowerList(List<String> strList) {
        this.powerList = copyListIfExists ( strList );
    }

    public void setAssociations(List<String> strList) {
        associationList = copyListIfExists ( strList );
    }

    private List<String> copyListIfExists(List<String> strList) {
        if (CollectionUtils.isEmpty ( strList ))  //checks both of null and  empty
            return new ArrayList<> ( );
        return new ArrayList<> ( strList );
    }

    public void addAttributeInDifferentType(SuperheroAttributeType type, String nameParam) {
        List<String> attributeList = getAttributeList ( type );
        attributeList.add ( nameParam );
    }

    private List<String> getAttributeList(SuperheroAttributeType type) {
        switch (type) {
            case ASSOCIATION:
                return this.associationList;
            case WEAPON:
                return this.weaponList;
            case POWER:
                return this.powerList;
        }
        return new ArrayList<> ( );
    }

    public void removeAttributeInDifferentType(SuperheroAttributeType type, String nameParam) {
        List<String> attributeList = getAttributeList ( type );
        attributeList.remove ( nameParam );
    }


    public void addAttributeListInDifferentType(SuperheroAttributeType type, List<String> attributeListParam) {
        switch (type) {
            case ASSOCIATION:
                associationList = copyListIfExists ( attributeListParam );
                break;
            case WEAPON:
                this.weaponList = copyListIfExists ( attributeListParam );
                break;
            case POWER:
                this.powerList = copyListIfExists ( attributeListParam );
                break;
        }
    }

    public static Superhero of(SuperheroDTO entity) {

        Superhero superhero = Superhero.builder ( )
                .name ( entity.getName ( ) )
                .alias ( entity.getAlias ( ) )
                .origin ( entity.getOrigin ( ) )
                .build ( );

        superhero.setWeapons ( entity.getWeaponList ( ) );
        superhero.setPowerList ( entity.getPowerList ( ) );
        superhero.setAssociations ( entity.getAssociationList ( ) );


        return superhero;
    }
}
