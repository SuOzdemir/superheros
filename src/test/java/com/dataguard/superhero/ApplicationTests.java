package com.dataguard.superhero;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        Map<String, SuperheroDTO> testData = getTestData ( );

        Map<String, Superhero> expectedMap = new HashMap<> ( );

        List<Superhero> expected = new ArrayList<> ( );

        for (Map.Entry<String, SuperheroDTO> kv : testData.entrySet ( )) {
            //  Superhero response = om.readValue(
            mockMvc.perform ( post ( "/superheros" )
                            .contentType ( "application/json" )
                            .content ( om.writeValueAsString ( kv.getValue ( ) ) ) )
                    .andDo ( print ( ) )
            //  .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString(), Superhero.class)
            ;
            //   expectedMap.put(kv.getKey(), response);
        }

        //Arrays.sort ( expectedMap.values ( ).toArray ( new Superhero[testData.size ( )] ), Comparator.comparing ( Superhero::getId ) );

        //without filter
        //   List<Superhero> actualRecords = om.readValue(
        mockMvc.perform ( get ( "/superheros" ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
        //.andReturn().getResponse().getContentAsString();
        //, new TypeReference<List<Superhero>> () {}
        //  )
        ;

//        for (int i = 0; i < expected.size(); i++) {
//            Assert.assertTrue(new ReflectionEquals (expected.get(i), "id").matches(actualRecords.get(i)));
//        }


    }

    private Map<String, SuperheroDTO> getTestData() {

        Map<String, SuperheroDTO> data = new HashMap<> ( );

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


        data.put ( "Captain Marvel", superhero1 );
        data.put ( "Iron Man", superhero2 );

        return data;
    }
}
