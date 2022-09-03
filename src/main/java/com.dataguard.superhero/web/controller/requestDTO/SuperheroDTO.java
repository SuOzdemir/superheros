package com.dataguard.superhero.web.controller.requestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class SuperheroDTO {

    @NotNull
    @NotEmpty
    private String alias;

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String origin;

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
