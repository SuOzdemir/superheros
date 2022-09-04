package com.dataguard.superhero.service;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.web.request.SuperheroBaseDTO;
import com.dataguard.superhero.web.request.SuperheroDTO;
import com.dataguard.superhero.web.response.SuperheroResponseList;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SuperheroService {

    Superhero createSuperhero(SuperheroDTO superheroRequestDTO);

    SuperheroResponseList getAllSuperheroes(int pageNumber, int pageSize);


    Superhero getSuperHeroById(Long id);

    Superhero addAttributeSuperhero(Long id, String typeStr, String name);

    Superhero removeAttributeSuperhero(Long id, String typeStr, String name);

    Superhero putAttributeListToSuperhero(Long id, String typeStr, List<String> attributeList);

    Superhero updateSuperhero(Long id, SuperheroBaseDTO superheroBaseDTO);

    void deleteSuperhero(Long id);
}
