package com.dataguard.superhero.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponse {
    private List<String> errors = new ArrayList<> ();

    public RestErrorResponse(String error) {
        errors.add(error);
    }
}
