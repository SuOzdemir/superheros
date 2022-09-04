package com.dataguard.superhero.service.impl;

import com.dataguard.superhero.dao.SuperheroDao;
import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.exception.SuperheroNotFoundException;
import com.dataguard.superhero.service.SuperheroService;
import com.dataguard.superhero.web.SuperheroAttributeType;
import com.dataguard.superhero.web.request.SuperheroBaseDTO;
import com.dataguard.superhero.web.request.SuperheroDTO;
import com.dataguard.superhero.web.response.SuperheroResponseList;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class SuperheroServiceImpl implements SuperheroService {

    public final SuperheroDao superheroDao;

    @Override
    public Superhero createSuperhero(SuperheroDTO superheroRequestDTO) {

        Superhero superhero = Superhero.of ( superheroRequestDTO );

        return superheroDao.saveSuperhero ( superhero );
    }

    @Override
    public SuperheroResponseList getAllSuperheroes(int pageNumber, int pageSize) {

        Pageable paging = PageRequest.of ( pageNumber, pageSize );

        Page<Superhero> pagedResult = superheroDao.getAllSuperheroes ( paging );


        validatePageHasParticipants ( pagedResult );

        return SuperheroResponseList.of ( pagedResult );

    }

    public static void validatePageHasParticipants(Page<Superhero> pagedResult) {
        if (pagedResult.getTotalElements ( ) == 0) {
            throw new SuperheroNotFoundException ( "There is no superhero in the system" );
        }
    }

//    @Override
//    public List<Superhero> getAllSuperheroesWithIDS() {
//
//      //  List<Superhero> superheroList = superheroDao.getAllSuperheroes ( );
//
//
//        return superheroList;
//    }

    @Override
    public Superhero getSuperHeroById(Long id) {

        if ((id == null || id == 0L))
            throw new InvalidParameterException ( " Id parameter is required" );

        return superheroDao.getSuperheroById ( id );
    }

    @Override
    public SuperheroDTO addAttributeSuperhero(Long id, String typeStr, String name) {

        SuperheroAttributeType type = getTypeWithValidating ( typeStr );

        Superhero superhero = superheroDao.getSuperheroById ( id );

        superhero.addAttributeInDifferentType ( type, name );

        return SuperheroDTO.of ( superheroDao.saveSuperhero ( superhero ) );
    }


   @Override
    public SuperheroDTO removeAttributeSuperhero(Long id, String typeStr, String name) {

        SuperheroAttributeType type = getTypeWithValidating ( typeStr );

        Superhero superhero = superheroDao.getSuperheroById ( id );

        superhero.removeAttributeInDifferentType ( type, name );

       return SuperheroDTO.of ( superheroDao.saveSuperhero ( superhero ) );
    }


    @Override
    public SuperheroDTO putAttributeListToSuperhero(Long id, String typeStr, List<String> attributeList) {

        SuperheroAttributeType type = getTypeWithValidating ( typeStr );

        Superhero superhero = superheroDao.getSuperheroById ( id );

        superhero.addAttributeListInDifferentType ( type, attributeList );

        superheroDao.saveSuperhero ( superhero );

        return SuperheroDTO.of ( superheroDao.saveSuperhero ( superhero ) );
    }

    @Override
    public SuperheroDTO updateSuperhero(Long id , SuperheroBaseDTO superheroBaseDTO) {

        Superhero superhero = superheroDao.getSuperheroById ( id );

        if ( !Strings.isEmpty ( superheroBaseDTO.getName() ))
            superhero.setName ( superheroBaseDTO.getName()  );

        if ( !Strings.isEmpty ( superheroBaseDTO.getAlias ()  ))
            superhero.setName ( superheroBaseDTO.getAlias ()  );

        if ( !Strings.isEmpty (superheroBaseDTO.getOrigin ()  ))
            superhero.setName ( superhero.getOrigin () );

         superheroDao.saveSuperhero ( superhero );

        return SuperheroDTO.of ( superhero );
    }

    @Override
    public void deleteSuperhero(Long id) {
        superheroDao.deleteSuperhero ( id );
    }

    private SuperheroAttributeType getTypeWithValidating(String typeStr) {
        return SuperheroAttributeType.findByName ( typeStr )
                .orElseThrow ( () -> new InvalidParameterException ( "type parameter is wrong " + typeStr ) );
    }

}
