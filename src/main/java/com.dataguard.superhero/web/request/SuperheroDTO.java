package com.dataguard.superhero.web.request;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.dao.entity.SuperheroAssociation;
import com.dataguard.superhero.dao.entity.SuperheroPower;
import com.dataguard.superhero.dao.entity.SuperheroWeapon;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//@Data
@Getter
@Setter
@SuperBuilder(toBuilder = true)
@NoArgsConstructor
public class SuperheroDTO extends SuperheroBaseDTO {

    @Singular("weapon")
    private List<String> weaponList;

    @Singular("association")
    private List<String> associationList;

    @Singular("power")
    private List<String> powerList;

    public static SuperheroDTO of(Superhero superhero) {

        SuperheroDTO superheroDTO = SuperheroDTO.builder ( )
                .name ( superhero.getName ( ) )
                .alias ( superhero.getAlias ( ) )
                .origin ( superhero.getOrigin ( ) )
                .build ( );

        superheroDTO.setWeaponList ( superhero.getWeaponList ( ).stream ( )
                .map ( SuperheroWeapon::getName )
                .collect ( Collectors.toList ( )));
        superheroDTO.setPowerList ( superhero.getPowerList ( ).stream ( )
                .map ( SuperheroPower::getName )
                .collect ( Collectors.toList ( )));
        superheroDTO.setAssociationList ( superhero.getAssociationList ( ).stream ( )
                .map ( SuperheroAssociation::getName )
                .collect ( Collectors.toList ( )));

        return superheroDTO;
    }

}
