package com.dataguard.superhero.web;

import java.util.Arrays;
import java.util.Optional;

public enum SuperheroAttributeType {
    WEAPON ( "weapon" ),
    ASSOCIATION ( "association" ),
    POWER ( "power" );

    private final String name;

    SuperheroAttributeType(String name) {
       this.name = name;
    }
    public static Optional<SuperheroAttributeType> findByName(String name) {
        return Arrays.stream(values()).filter( type -> type.name.equals(name)).findFirst();
    }
}
