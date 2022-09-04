package com.dataguard.superhero.dao.constant;

public class ApiPathValues {

    public static final String SUPERHEROS_PATH = "/superheros";
//    public static final String SUPERHEROS_DETAILS_PATH = "/superheros/details";
    public static final String SUPERHEROS_ID_PATH = "/superheros/{id}";
    public static final String SUPERHEROS_ID_PUT_ATTRIBUTE_PATH = "/superheros/{id}/attributes/{type}";
    public static final String SUPERHEROS_ID_ATTRIBUTE_PATH = "/superheros/{id}/attributes/{type}/{name}";


    public static final String BASE_PATH = "/api/v1";

    private ApiPathValues() {
        throw new IllegalStateException("Utility class");
    }

}
