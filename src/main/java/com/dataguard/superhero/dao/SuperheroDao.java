package com.dataguard.superhero.dao;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.dao.repository.SuperheroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SuperheroDao {
    private final SuperheroRepository superheroRepository;
    public Superhero createSuperhero(Superhero superhero) {
        return superheroRepository.save ( superhero);
    }
}
