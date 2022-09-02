package com.dataguard.superhero.web.controller.requestDTO;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
public class SuperheroRequestDTO {

    private String alias;
    private String name;
    private String origin;

    private List<String> weaponList;

    private List<String> associationList;

    private List<String> powerList;

    public void setWeaponList(List<String> weaponList) {
        if(weaponList ==null)
            this.weaponList = new ArrayList<> ();

        this.weaponList =  new ArrayList<>( weaponList );
    }

    public void setAssociationList(List<String> associationList) {
        if(associationList ==null)
            this.associationList = new ArrayList<> ();

        this.associationList =  new ArrayList<>( associationList );
    }

    public void setPowerList(List<String> powerList) {
        if(powerList ==null)
            this.powerList = new ArrayList<> ();

        assert powerList != null;
        this.weaponList =  new ArrayList<>( powerList );
    }
}
