package com.dataguard.superhero.web.controller.requestDTO;

import lombok.Builder;
import lombok.Getter;
import lombok.Singular;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
public class SuperheroDTO {

    private String alias;
    private String name;
    private String origin;

    @Singular("weapon")
    private List<String> weaponList;

    @Singular("association")
    private List<String> associationList;

    @Singular("power")
    private List<String> powerList;

    public void setWeaponList(String... strings) {
        this.weaponList = Arrays.stream ( strings ).collect ( Collectors.toList ( ) );
    }

    public void setAssociationList(String... strings) {
        this.associationList = Arrays.stream ( strings ).collect ( Collectors.toList ( ) );
    }

    public void setPowerList(String... strings) {
        this.powerList = Arrays.stream( strings ).collect( Collectors.toList());
    }
}
