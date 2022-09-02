package com.dataguard.superhero.service.impl;

import com.dataguard.superhero.dao.SuperheroDao;
import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroRequestDTO;
import com.dataguard.superhero.mapper.SuperheroDataMapper;
import com.dataguard.superhero.service.SuperheroService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


    @Service
    @RequiredArgsConstructor
    public class SuperheroServiceImpl implements SuperheroService {

        public final SuperheroDao superheroDao;
        public final SuperheroDataMapper dataMapper;
        @Override
        public Superhero createSuperhero(SuperheroRequestDTO superheroRequestDTO) {
           Superhero superhero = dataMapper.createSuperHeroFromResuestDTO(superheroRequestDTO);

            return  superheroDao.createSuperhero(superhero);
        }



}
