package com.dataguard.superhero.web.controller;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.service.SuperheroService;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1",
        produces = "application/vnd.api.v1+json")
public class SuperheroRestController {

    private final SuperheroService superheroService;

    @PostMapping (value="/superhero")
    public ResponseEntity<Superhero> createSuperhero(@RequestBody SuperheroDTO
                                                             superheroRequestDTO) {
        log.info ( "Creating superhero with name: {}", superheroRequestDTO.getAlias ( ) );
        Superhero superhero = superheroService.createSuperhero ( superheroRequestDTO );
        return ResponseEntity.status ( HttpStatus.CREATED ).body ( superhero );
    }

    @GetMapping (value="/superheros")
    public ResponseEntity< List<SuperheroDTO> > getAllSuperheroes() {
        log.info ( " Getting all superheros is requested ");
        List<SuperheroDTO> superheroDTOList = superheroService.getAllSuperheroes ();
        return ResponseEntity.status ( HttpStatus.OK ).body ( superheroDTOList );
    }
    @GetMapping (value="/superheros-with-id")
    public ResponseEntity< List<Superhero> > getAllSuperheroesWithIDs() {
        log.info ( " Getting all superheros with IDs is requested ");
        List<Superhero> superheroList = superheroService.getAllSuperheroesWithIDS ();
        return ResponseEntity.status ( HttpStatus.OK ).body ( superheroList );
    }


}
