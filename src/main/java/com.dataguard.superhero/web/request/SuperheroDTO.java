package com.dataguard.superhero.web.request;

import com.dataguard.superhero.dao.entity.Superhero;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

//@Data
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class SuperheroDTO extends SuperheroBaseDTO {

    private List<String> weaponList;

    private List<String> associationList;

    private List<String> powerList;

    public static SuperheroDTO of(Superhero superhero) {

        SuperheroDTO superheroDTO = SuperheroDTO.builder ( )
                .name ( superhero.getName ( ) )
                .alias ( superhero.getAlias ( ) )
                .origin ( superhero.getOrigin ( ) )
                .build ( );

        superheroDTO.setWeaponList ( new ArrayList<> ( superhero.getWeaponList ( ) ) );
        superheroDTO.setPowerList ( new ArrayList<> ( superhero.getPowerList ( ) ) );
        superheroDTO.setAssociationList ( new ArrayList<> ( superhero.getAssociationList ( ) ) );

        return superheroDTO;
    }

}
