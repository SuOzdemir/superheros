package com.dataguard.superhero.service.impl;

import com.dataguard.superhero.dao.SuperheroDao;
import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.exception.SuperheroNotFoundException;
import com.dataguard.superhero.mapper.SuperheroDataMapper;
import com.dataguard.superhero.service.SuperheroService;
import com.dataguard.superhero.web.SuperheroAttributeType;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.security.InvalidParameterException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SuperheroServiceImpl implements SuperheroService {

    public final SuperheroDao superheroDao;
    public final SuperheroDataMapper mapper;

    @Override
    public Superhero createSuperhero(SuperheroDTO superheroRequestDTO) {

        Superhero superhero = mapper.createSuperHeroFromRequestDTO ( superheroRequestDTO );

        return superheroDao.saveSuperhero ( superhero );
    }

    @Override
    public List<SuperheroDTO> getAllSuperheroes() {

        List<Superhero> superheroList = superheroDao.getAllSuperheroes ( );

        emptyCheck ( superheroList );

        return mapper.convertDTOAsList ( superheroList );

    }



    @Override
    public List<Superhero> getAllSuperheroesWithIDS() {

        List<Superhero> superheroList = superheroDao.getAllSuperheroes ( );

        emptyCheck ( superheroList );

        return superheroList;
    }

    @Override
    public Superhero getSuperheroById(Long id) {
        return superheroDao.getSuperheroById(id);
    }

    @Override
    public Superhero getSuperheroByNameOrAlias(String name, String alias) {

        if (Strings.isEmpty ( name ) && Strings.isEmpty ( alias ))
            throw new InvalidParameterException ( " At least one parameter is required" );

        return superheroDao.getSuperHeroByNameOrAlias ( name, alias );

    }

    @Override
    public SuperheroDTO addAttributeSuperhero(Long id, String typeStr, String name) {

        SuperheroAttributeType type = SuperheroAttributeType.findByName ( typeStr )
                .orElseThrow ( () -> new InvalidParameterException ( "type parameter is wrong " + typeStr ) );

        Superhero superhero = getSuperheroById ( id );

        superhero.addAttributeInDifferentType ( type, name );

        return mapper.createDTOfromSuperhero ( superheroDao.saveSuperhero ( superhero ) );
    }

    private void emptyCheck(List<Superhero> superheroList) {
        if (CollectionUtils.isEmpty ( superheroList ))
            throw new SuperheroNotFoundException ( "There is no superhero in the system" );
    }

}
