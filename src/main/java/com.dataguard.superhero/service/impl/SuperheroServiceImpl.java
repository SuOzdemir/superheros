package com.dataguard.superhero.service.impl;

import com.dataguard.superhero.dao.SuperheroDao;
import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.mapper.SuperheroDataMapper;
import com.dataguard.superhero.service.SuperheroService;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


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
        return superheroList.stream ( )
                .map ( (superhero) -> dataMapper.createDTOfromSuperhero ( superhero ) )
                .collect ( Collectors.toList ( ) );

    }


}
