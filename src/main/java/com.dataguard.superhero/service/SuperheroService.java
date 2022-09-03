package com.dataguard.superhero.service;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SuperheroService {

    Superhero createSuperhero(SuperheroDTO superheroRequestDTO);

    List<SuperheroDTO> getAllSuperheroes();

    List<Superhero> getAllSuperheroesWithIDS();

    Superhero getSuperheroById(Long id);

    Superhero getSuperheroByNameOrAlias(String name, String alias);
}
