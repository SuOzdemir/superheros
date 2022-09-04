package com.dataguard.superhero.web.controller;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.service.SuperheroService;
import com.dataguard.superhero.validation.Type;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroBaseDTO;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/api/v1")

public class SuperheroRestController {

    public static final String SUPERHEROS_PATH = "/superheros";
    public static final String SUPERHEROS_DETAILS_PATH = "/superheros/details";
    public static final String SUPERHEROS_ID_PATH = "/superheros/{id}";
    public static final String SUPERHEROS_ID_PUT_ATTRIBUTE_PATH = "/superheros/{id}/attributes/{type}";
    public static final String SUPERHEROS_ID_ATTRIBUTE_PATH = "/superheros/{id}/attributes/{type}/{name}";
    private final SuperheroService superheroService;

    @PostMapping(value = SUPERHEROS_PATH, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "CREATED", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Superhero.class))})})
    @Operation(summary = "creates Superhero Object ")
    public ResponseEntity<Superhero> createSuperhero(@Valid @RequestBody SuperheroDTO superheroRequestDTO) {
        log.info ( "Creating superhero with name: {}", superheroRequestDTO.getAlias ( ) );
        Superhero superhero = superheroService.createSuperhero ( superheroRequestDTO );
        return ResponseEntity.status ( HttpStatus.CREATED ).body ( superhero );
    }


    @GetMapping(value = SUPERHEROS_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Superhero.class))})})
    @Operation(summary = "get Superhero Object with id")
    public ResponseEntity<Superhero> getSuperHeroById( @Parameter(description = "superhero Id")@PathVariable @NotNull Long id) {
        log.info ( "Get superhero by id {}", id );
        Superhero superhero = superheroService.getSuperHeroById ( id );
        return ResponseEntity.status ( HttpStatus.OK ).body ( superhero );
    }

    @GetMapping(value = SUPERHEROS_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuperheroDTO.class))})})
    @Operation(summary = "Get All Superheros ")
    public ResponseEntity<List<SuperheroDTO>> getAllSuperheroes() {
        log.info ( " Getting all superheros is requested " );
        List<SuperheroDTO> superheroDTOList = superheroService.getAllSuperheroes ( );
        return ResponseEntity.status ( HttpStatus.OK ).body ( superheroDTOList );
    }

    @GetMapping(value = SUPERHEROS_DETAILS_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Superhero.class))})})
    @Operation(summary = "Get All Superheros with ID and attributes with ID")
    public ResponseEntity<List<Superhero>> getAllSuperheroesWithIDs() {
        log.info ( " Getting all superheros with IDs is requested " );
        List<Superhero> superheroList = superheroService.getAllSuperheroesWithIDS ( );
        return ResponseEntity.status ( HttpStatus.OK ).body ( superheroList );
    }


    @DeleteMapping(value = SUPERHEROS_ID_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Superhero.class))})})
    @Operation(summary = "Delete superhero with given ID")
    public ResponseEntity<?> deleteSuperhero( @Parameter(description = "superhero Id") @PathVariable @NotNull Long id) {
        log.info ( " Delete superhero with id: {}  is requested ", id );
        superheroService.deleteSuperhero ( id );
        return ResponseEntity.ok ( "Superhero deleted" );
    }

    @PatchMapping(value = SUPERHEROS_ID_PATH, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuperheroDTO.class))})})

    @Operation(summary = "Changes superhero's some attributes limited with (name,alias or origin attributes)")
    public ResponseEntity<SuperheroDTO> updateSuperHero(@Parameter(description = "superhero Id") @PathVariable @NotNull Long id, @RequestBody @NotNull SuperheroBaseDTO superheroBaseDTO) {
        log.info ( " Change Superhero with id: {} - {} is requested ", id, superheroBaseDTO.toString ( ) );
        SuperheroDTO superheroDTO = superheroService.updateSuperhero ( id, superheroBaseDTO );
        return ResponseEntity.status ( HttpStatus.OK ).body ( superheroDTO );
    }

    @PutMapping(value = SUPERHEROS_ID_PUT_ATTRIBUTE_PATH, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuperheroDTO.class))})})

    @Operation(summary = "Changes superhero's attributes with the exact set of attributes.Removes not exists, adds new ones")
    public ResponseEntity<SuperheroDTO> putAttributeListToSuperhero(@PathVariable @NotNull Long id,
                                                                    @PathVariable @Valid @Type String type,
                                                                    @RequestBody List<String> attributeList) {
        log.info ( " To Superhero with id: {} , Change {}s, with exact list \"{}\"  is requested ", id, type, String.join ( " ", attributeList ) );
        SuperheroDTO superheroDTO = superheroService.putAttributeListToSuperhero ( id, type, attributeList );
        return ResponseEntity.status ( HttpStatus.OK ).body ( superheroDTO );
    }

    @PostMapping(value = SUPERHEROS_ID_ATTRIBUTE_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuperheroDTO.class))})})

    @Operation(summary = "Add one power/weapon/association to a superhero")
    public ResponseEntity<SuperheroDTO> addAttribute(@Parameter(description = "superhero Id") @PathVariable @NotNull Long id,
                                                     @Parameter(description = "choose one of weapon/power/association") @PathVariable @Valid @Type String type,
                                                     @Parameter(description = "attribute name")  @PathVariable @NotNull String name) {
        log.info ( " To Superhero with id: {} , Adding \"{}\"  to {}s list is requested ", id, name, type );
        SuperheroDTO superheroDTO = superheroService.addAttributeSuperhero ( id, type, name );
        return ResponseEntity.status ( HttpStatus.OK ).body ( superheroDTO );
    }

    @DeleteMapping(value = SUPERHEROS_ID_ATTRIBUTE_PATH,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = SuperheroDTO.class))})})

    @Operation(summary = "Remove one power/weapon/association attribute from a superhero")
    public ResponseEntity<SuperheroDTO> removeAttribute(@Parameter(description = "superhero Id") @PathVariable @NotNull Long id,
                                                        @Parameter(description = "choose one of weapon/power/association") @PathVariable @Valid @Type String type,
                                                        @Parameter(description = "attribute name") @PathVariable @NotNull String name) {
        log.info ( " To Superhero with id: {} , removing \"{}\"  from {}s list is requested ", id, name, type );
        SuperheroDTO superheroDTO = superheroService.removeAttributeSuperhero ( id, type, name );
        return ResponseEntity.status ( HttpStatus.OK ).body ( superheroDTO );
    }
}
