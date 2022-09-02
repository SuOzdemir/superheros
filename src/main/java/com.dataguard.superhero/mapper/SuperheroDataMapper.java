
package com.dataguard.superhero.mapper;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.dao.entity.SuperheroAssociation;
import com.dataguard.superhero.dao.entity.SuperheroPower;
import com.dataguard.superhero.dao.entity.SuperheroWeapon;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;


@Component
public class SuperheroDataMapper {
   public Superhero createSuperHeroFromRequestDTO(SuperheroDTO superheroRequestDTO) {

      Superhero superhero = Superhero.builder ( )
              .name ( superheroRequestDTO.getName ( ) )
              .alias ( superheroRequestDTO.getAlias ( ) )
              .origin ( superheroRequestDTO.getOrigin ( ) )
              .build ( );

      superhero.setWeaponList ( superheroRequestDTO.getWeaponList ( ) );
      superhero.setPowerList ( superheroRequestDTO.getPowerList ( ) );
      superhero.setAssociationList ( superheroRequestDTO.getAssociationList ( ) );

      return superhero;
   }

   public SuperheroDTO createDTOfromSuperhero(Superhero superhero) {

      SuperheroDTO superheroDTO = SuperheroDTO.builder ( )
              .name ( superhero.getName ( ) )
              .alias ( superhero.getAlias ( ) )
              .origin ( superhero.getOrigin ( ) )
              .build ( );

      superheroDTO.setWeaponList ( superhero.getWeaponList ( ).stream ( )
              .map ( SuperheroWeapon::getName )
              .collect ( Collectors.joining ( "," ) ) );
      superheroDTO.setPowerList ( superhero.getPowerList ( ).stream ( )
              .map ( SuperheroPower::getName )
              .collect ( Collectors.joining ( "," ) ) );
      superheroDTO.setAssociationList ( superhero.getAssociationList ( ).stream ( )
              .map ( SuperheroAssociation::getName )
              .collect ( Collectors.joining ( "," ) ) );

      return superheroDTO;
   }
}
