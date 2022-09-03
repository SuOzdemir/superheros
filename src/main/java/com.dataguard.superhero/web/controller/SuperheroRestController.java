package com.dataguard.superhero.web.controller;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.service.SuperheroService;
import com.dataguard.superhero.validation.Type;
import com.dataguard.superhero.web.SuperheroAttributeType;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1",
        produces = "application/vnd.api.v1+json")
public class SuperheroRestController {

    private final SuperheroService superheroService;
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Superhero.class)
//                            , examples = {@ExampleObject(value = BASE_SUCCESS_RESPONSE_OK)}
                    )})
//            ,
//            @ApiResponse(responseCode = "400", description = "Invalid SessionID", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = FlexLoginBaseResponse.class), examples = {
//                            @ExampleObject(value = INVALID_SESSION_ID_400_BAD_REQUEST)})}),
//            @ApiResponse(responseCode = "403", description = "Not correct request", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = FlexLoginBaseResponse.class), examples = {
//                            @ExampleObject(name = "Not correct state", description = "Not correct state",
//                                    value = SESSION_CREDENTIALS_NOT_MATCH_403),
//                            @ExampleObject(name = "You cannot call Api in this state", description = "You cannot call Api in this state",
//                                    value = INVALID_STATE_403_FORBIDDEN)})}),
//            @ApiResponse(responseCode = "500", description = "Internal System Error.", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = FlexLoginBaseResponse.class), examples = {
//                            @ExampleObject(value = INTERNAL_ERROR_500)})}),
//            @ApiResponse(responseCode = "503", description = "External System Error.", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = FlexLoginBaseResponse.class), examples = {
//                            @ExampleObject(value = FLEX_ERROR_503_SERVICE_UNAVAILABLE)})})
    })
    @Operation(summary = "creates Superhero Object ")


    @PostMapping (value="/superhero")
    public ResponseEntity<Superhero> createSuperhero(@RequestBody SuperheroDTO
                                                             superheroRequestDTO) {
        log.info ( "Creating superhero with name: {}", superheroRequestDTO.getAlias ( ) );
        Superhero superhero = superheroService.createSuperhero ( superheroRequestDTO );
        return ResponseEntity.status ( HttpStatus.CREATED ).body ( superhero );
    }


    @GetMapping (value="superhero/{id}")
    public ResponseEntity<Superhero> getSuperheroById(@PathVariable Long id) {
        log.info ( "Get superhero by id {}", id );
        Superhero superhero = superheroService.getSuperheroById ( id );
        return ResponseEntity.status ( HttpStatus.OK ).body ( superhero );
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = Superhero.class)
//                            , examples = {@ExampleObject(value = BASE_SUCCESS_RESPONSE_OK)}
                    )})
    })

    @PostMapping (value="/superhero/{id}/{type}/{name}")
    public ResponseEntity< SuperheroDTO> addAttributeSuperhero( @NotNull @PathVariable Long id,
                                                                         @PathVariable  @Valid @Type String type,
                                                                        @NotNull @PathVariable String name) {
        log.info ( " To Superhero with id: {} , Adding \"{}\"  to {}s list is requested ",id,name,type);
        SuperheroDTO superheroDTO = superheroService.addAttributeSuperhero ( id,type, name);
        return ResponseEntity.status ( HttpStatus.OK ).body ( superheroDTO );
    }


}
