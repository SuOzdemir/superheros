package com.dataguard.superhero;

import com.dataguard.superhero.web.controller.requestDTO.SuperheroRequestDTO;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class ApplicationTests {



    private Map<String, SuperheroRequestDTO> getTestData() {

        Map<String, SuperheroRequestDTO> data = new HashMap<> ();

        SuperheroRequestDTO superhero1 = SuperheroRequestDTO.builder ( ).name("Carol Danvers").alias ( "Captain Marvel" ).origin ( "Exposed to Space Stone reactor overload").build ( );
        List<String> powers1=  Arrays.asList (  "photon-blast", "flight", "super-strength", "healing" );
        List<String> associations1=  Arrays.asList ( "photon-blast", "flight", "super-strength", "healing" );
        List<String> weapons1= Collections.emptyList ( );

        superhero1.setWeaponList ( weapons1 );
        superhero1.setPowerList ( powers1 );
        superhero1.setAssociationList ( associations1 );

        SuperheroRequestDTO superhero2 = SuperheroRequestDTO.builder ( ).name("Tony Stark").alias ( "Iron Man" ).origin ( "Kidnapped in Afghanistan, created the first iron-man suit to escape.").build ( );
        List<String> powers2 = Arrays.asList (  "genius-intelligence", "wealth" );
        List<String> associations2=  Arrays.asList ( "war-machine", "avengers", "jarvis", "thanos", "pepper-potts" );
        List<String> weapons2=  Arrays.asList ("arc-reactor", "iron-man-suit", "iron-legion");

        superhero2.setWeaponList ( weapons2 );
        superhero2.setPowerList ( powers2 );
        superhero2.setAssociationList ( associations2 );

        data.put("Captain Marvel", superhero1);
        data.put("Iron Man", superhero2);

        return data;
    }
}
