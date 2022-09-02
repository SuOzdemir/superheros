package com.dataguard.superhero;

import com.dataguard.superhero.dao.entity.BaseAttribute;
import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ApplicationTests {

    ObjectMapper om = new ObjectMapper ( );
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testGetSuperheros() throws Exception {

        List<SuperheroDTO>  testData = getTestData ( );

        List<Superhero> expected = new ArrayList<> ( );

        for ( SuperheroDTO kv : testData) {
           // Superhero response = om.readValue (
                    mockMvc.perform ( post ( "/api/v1/superhero" )
                                    .contentType ( "application/json" )
                                    .content ( om.writeValueAsString ( kv ) ) )
                            //  .andDo ( print ( ) )
                            .andExpect ( status ( ).isCreated ( ) );
                            //.andReturn ( ).getResponse ( ).getContentAsString ( ), Superhero.class );
            // expected.add ( response );
        }
        System.out.println ("----------------------------------------------------------------------------" );
//        Collections.sort ( expected, Comparator.comparing ( Superhero::getId ) );
//
//        //without filter
//           List<Superhero> actualRecords = om.readValue( mockMvc.perform ( get ( "/api/v1/superheros-with-id" ) )
//                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
//                .andReturn().getResponse().getContentAsString() , new TypeReference<List<Superhero>> () {}
//          );


        //     AssertEqualsForExpectedLists ( expected, actualRecords );

//        mockMvc.perform ( get ( "/api/v1/superheros-with-id" ) )
//                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) );

    }

    private void AssertEqualsForExpectedLists(List<Superhero> expected, List<Superhero> actualRecords) {
        assertThat(expected.size ()).isEqualTo(actualRecords.size ());
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue(new ReflectionEquals ( expected.get(i), "id","associationList","powerList",  "weaponList").matches( actualRecords.get(i)));
            AssertEqualsForEachAttributes( expected.get(i).getAssociationList (),actualRecords.get ( i).getAssociationList ());
        }
    }

    private void AssertEqualsForEachAttributes(List< ? extends BaseAttribute> expected, List< ? extends BaseAttribute> actualRecords) {
        for (int i = 0; i < expected.size(); i++) {
            Assert.assertTrue(new ReflectionEquals ( expected.get(i), "id").matches( actualRecords.get(i)));
        }
    }


    private  List<SuperheroDTO>  getTestData() {

        List<SuperheroDTO> data =new ArrayList<> (  );

        SuperheroDTO superhero1 = SuperheroDTO.builder ( ).name ( "Carol Danvers" ).alias ( "Captain Marvel" ).origin ( "Exposed to Space Stone reactor overload" ).build ( );
        superhero1.setPowerList ( "photon-blast", "flight", "super-strength", "healing" );
        superhero1.setAssociationList ( "photon-blast", "flight", "super-strength", "healing" );
        superhero1.setWeaponList ( );


        SuperheroDTO superhero2 = SuperheroDTO.builder ( )
                .name ( "Tony Stark" )
                .alias ( "Iron Man" )
                .origin ( "Kidnapped in Afghanistan, created the first iron-man suit to escape." )
                .build ( );
        superhero2.setPowerList ( "genius-intelligence", "wealth" );
        superhero2.setAssociationList ( "war-machine", "avengers", "jarvis", "thanos", "pepper-potts" );
        superhero2.setWeaponList ( "arc-reactor", "iron-man-suit", "iron-legion" );


        data.add ( superhero1 );
        data.add ( superhero2 );

        return data;
    }

}
