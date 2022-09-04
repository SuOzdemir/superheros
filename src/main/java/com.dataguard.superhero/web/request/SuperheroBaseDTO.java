package com.dataguard.superhero.web.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class SuperheroBaseDTO {

    @NotBlank
    private String alias;

    @NotBlank
    private String name;

    private String origin;

}
