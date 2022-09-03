package com.dataguard.superhero.dao;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.dao.repository.SuperheroRepository;
import com.dataguard.superhero.exception.SuperheroNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SuperheroDao {
    private final SuperheroRepository superheroRepository;

    public Superhero saveSuperhero(Superhero superhero) {
        return superheroRepository.save ( superhero );
    }

    public List<Superhero> getAllSuperheroes() {
        return superheroRepository.findAll ( );
    }

    public Superhero getSuperheroById(@NotNull Long id) {
        return superheroRepository.findById ( id ).orElseThrow ( () -> new SuperheroNotFoundException ( " superhero with id: " + id + " is not found" ) );
    }
    public void deleteSuperhero(Long id) {
        superheroRepository.deleteById ( id );
    }
}
