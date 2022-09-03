package com.dataguard.superhero.dao;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.dao.repository.SuperheroRepository;
import com.dataguard.superhero.exception.SuperheroNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
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

    public Superhero getSuperheroById(@NotNull Long id) {
        return superheroRepository.findById ( id ).orElseThrow ( () -> new SuperheroNotFoundException ( " superhero with id: " + id + " is not found" ) );
    }
//
//    public Superhero getSuperHeroByIdOrNameOrAlias(Long id, String name, String alias) {
//
//
//        if (id != null && id > 0) // there is priority for ID
//            return superheroRepository.findById ( id )
//                    .orElseThrow ( () -> new SuperheroNotFoundException ( " superhero is not found with id {" + id + "}" ));
//
//
//        if (!Strings.isEmpty ( name ) )
//            return superheroRepository.findByName ( name )
//                    .orElseThrow ( () -> new SuperheroNotFoundException ( " superhero is not found with name {" + name + "}" ));
//
//
//        if (!Strings.isEmpty ( alias ))
//            return superheroRepository.findByAlias ( alias )
//                    .orElseThrow ( () -> new SuperheroNotFoundException ( " superhero is not found with alias {" + alias + "}" ));
//
//        throw new SuperheroNotFoundException(" Superhero is not found");
//    }

    public void deleteSuperhero(Long id) {
        superheroRepository.deleteById ( id );
    }
}
