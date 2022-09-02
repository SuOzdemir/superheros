
package com.dataguard.superhero.mapper;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroRequestDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;


@Component
public class SuperheroDataMapper {
    public Superhero createSuperHeroFromResuestDTO(SuperheroRequestDTO superheroRequestDTO) {
        Superhero superhero = new Superhero();
        BeanUtils.copyProperties ( superheroRequestDTO,superhero);
        return superhero;
    }
}
