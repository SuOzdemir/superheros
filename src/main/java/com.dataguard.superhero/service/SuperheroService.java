package com.dataguard.superhero.service;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface SuperheroService {

    Superhero createSuperhero(SuperheroRequestDTO superheroRequestDTO);
}
