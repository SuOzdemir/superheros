package com.dataguard.superhero.dao;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.dao.repository.SuperheroRepository;
import com.dataguard.superhero.exception.SuperheroNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Superhero getSuperheroById(Long id) {
        return superheroRepository.findById ( id ).orElseThrow ( () -> new SuperheroNotFoundException ( " superhero with id: " + id + " is not found" ) );
    }

    public Superhero getSuperHeroByNameOrAlias(String name, String alias) {

        Optional<Superhero> optionalSuperhero = Optional.empty ( );

        if (Strings.isEmpty ( name ) && !Strings.isEmpty ( alias ))
            optionalSuperhero = superheroRepository.findByName ( name );
        if (!Strings.isEmpty ( name ) && Strings.isEmpty ( alias ))
            optionalSuperhero =  superheroRepository.findByAlias ( alias );
        if (!Strings.isEmpty ( name ) && !Strings.isEmpty ( alias ))
            optionalSuperhero =  superheroRepository.findByAliasAndName ( alias, name );

        return optionalSuperhero.orElseThrow ( ()-> new SuperheroNotFoundException ( " superhero is not found with alias {"+alias+"} + name {"+name+"}" ));

    }

}
