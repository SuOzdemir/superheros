package com.dataguard.superhero.web.controller.requestDTO;

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

    public void setWeaponList(String... strings) {
        this.weaponList = stringsToList ( strings );
    }
    public void setAssociationList(String... strings) {
        this.associationList = stringsToList ( strings );
    }
    public void setPowerList(String... strings) {
        this.powerList = stringsToList ( strings );
    }


    private List<String> stringsToList(String[] strings) {
        return Arrays.stream ( strings ).collect ( Collectors.toList ( ) );
    }

}
