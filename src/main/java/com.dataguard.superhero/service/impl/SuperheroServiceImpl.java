package com.dataguard.superhero.service.impl;

import com.dataguard.superhero.dao.SuperheroDao;
import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.exception.CustomInvalidArgumentException;
import com.dataguard.superhero.exception.SuperheroNotFoundException;
import com.dataguard.superhero.mapper.SuperheroDataMapper;
import com.dataguard.superhero.service.SuperheroService;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;


@Service
@RequiredArgsConstructor
public class SuperheroServiceImpl implements SuperheroService {

    public final SuperheroDao superheroDao;
    public final SuperheroDataMapper dataMapper;

    @Override
    public Superhero createSuperhero(SuperheroDTO superheroRequestDTO) {
        Superhero superhero = dataMapper.createSuperHeroFromRequestDTO ( superheroRequestDTO );
        return superheroDao.createSuperhero ( superhero );
    }

    @Override
    public List<SuperheroDTO> getAllSuperheroes() {

        List<Superhero> superheroList = superheroDao.getAllSuperheroes ( );

        emptyCheck ( superheroList );

        return dataMapper.convertDTOAsList( superheroList );

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


        if(Strings.isEmpty (name) && Strings.isEmpty (alias))
                throw new CustomInvalidArgumentException (" At least one parameter is required");

        return superheroDao.getSuperHeroByNameOrAlias ( name, alias );

    }

    private void emptyCheck(List<Superhero> superheroList) {
        if (CollectionUtils.isEmpty ( superheroList ))
            throw new SuperheroNotFoundException ( "There is no superhero in the system" );
    }

}
