package com.dataguard.superhero;

import com.dataguard.superhero.web.controller.requestDTO.SuperheroBaseDTO;
import com.dataguard.superhero.web.controller.requestDTO.SuperheroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationTests {

    ObjectMapper om = new ObjectMapper ( );
    @Autowired
    MockMvc mockMvc;
    List<SuperheroDTO> testData;


    public static final String ABSOLUTE_PATH = "/api/v1";
    public static final String SUPERHEROS_PATH = ABSOLUTE_PATH + "/superheros";
    public static final String SUPERHEROS_DETAILS_PATH = ABSOLUTE_PATH + "/superheros/details";
    public static final String SUPERHEROS_ID_PATH = ABSOLUTE_PATH + "/superheros/{id}";

    public static final String SUPERHEROS_ID_PUT_ATTRIBUTE_PATH = ABSOLUTE_PATH + "/superheros/{id}/attributes/{type}";

    public static final String SUPERHEROS_ID_ATTRIBUTE_PATH = ABSOLUTE_PATH + "/superheros/{id}/attributes/{type}/{name}";


    @Test
    @Order(1)
    public void testCreateSuperheros() throws Exception {

        testData = getTestData ( );

        for (SuperheroDTO kv : testData) {
            mockMvc.perform ( post ( SUPERHEROS_PATH )
                            .contentType ( "application/json" )
                            .content ( om.writeValueAsString ( kv ) ) )
                    .andExpect ( status ( ).isCreated ( ) );
        }

    }

    @Test
    @Order(2)
    public void testGetAllSuperheros() throws Exception {

        mockMvc.perform ( get ( SUPERHEROS_PATH ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }

    @Test
    @Order(3)
    public void testGetSuperheros_withIDDetails() throws Exception {

        mockMvc.perform ( get ( SUPERHEROS_DETAILS_PATH ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }

    @Test
    @Order(4)
    public void testGetSuperhero_withID_ShouldReturn_200_OK() throws Exception {

        mockMvc.perform ( get ( SUPERHEROS_ID_PATH, 1L ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }

    @Test
    @Order(5)
    public void testGetSuperhero_withID_ShouldReturn_NOT_FOUND() throws Exception {
        Long dummy = 999L;
        mockMvc.perform ( get ( SUPERHEROS_ID_PATH, dummy ) )
                //.andDo ( print ( ) )
                .andExpect ( status ( ).isNotFound ( ) )
                .andReturn ( );
    }

    @Test
    @Order(6)
        public void test_AddAttribute_Weapon_ShouldReturn_OK() throws Exception {
        mockMvc.perform ( post ( SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, "weapon", "run-fast" )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }
    @Test
    @Order(7)
    public void test_AddAttribute_Power_ShouldReturn_OK() throws Exception {
        mockMvc.perform ( post ( SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, "power", "water" )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }
    @Test
    @Order(7)
    public void test_AddAttribute_Association_ShouldReturn_OK() throws Exception {
        mockMvc.perform ( post ( SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, "association", "greate" )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }

    @Test
    @Order(10)
    public void test_addAttribute_ShouldReturn_BAD_REQUEST() throws Exception {
        String dummyType = "weapon3";
        mockMvc.perform ( post ( SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, dummyType, "run-fast" )
                        .contentType ( "application/json" ) )
                //.andDo ( print ( ) )
                .andExpect ( status ( ).is4xxClientError ( ) )
                .andReturn ( );
    }

    @Test
    @Order(15)
    public void test_removeAttribute_ShouldReturn_OK() throws Exception {
        mockMvc.perform ( delete ( SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, "weapon", "run-fast" )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }

    @Test
    @Order(20)
    public void test_putAllAttributes_ShouldReturn_OK() throws Exception {
        List<String> stringList = List.of ( "run-fast2", "run-fast3" );
        mockMvc.perform ( put ( SUPERHEROS_ID_PUT_ATTRIBUTE_PATH, 1L, "weapon" )
                        .contentType ( "application/json" )
                        .content ( om.writeValueAsString ( stringList ) ) )
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    @Order(30)
    public void test_UpdateSuperhero_ShouldReturn_OK() throws Exception {
        SuperheroBaseDTO superheroBaseDTO = SuperheroBaseDTO.builder ( )
                .name ( "Alice James" )
                .alias ( "Iron Woman" )
                .origin ( "Kidnapped in Afghanistan, created the first iron-man suit to escape." )
                .build ( );

        mockMvc.perform ( patch ( SUPERHEROS_ID_PATH, 1L )
                        .contentType ( "application/json" )
                        .content ( om.writeValueAsString ( superheroBaseDTO) ))
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    @Order(40)
    public void test_DeleteSuperhero_ShouldReturn_OK() throws Exception {


        mockMvc.perform ( delete ( SUPERHEROS_ID_PATH, 1L )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }

    private List<SuperheroDTO> getTestData() {

        List<SuperheroDTO> data = new ArrayList<> ( );

        SuperheroDTO superhero1 = SuperheroDTO.builder ( ).name ( "Carol Danvers" ).alias ( "Captain Marvel" ).origin ( "Exposed to Space Stone reactor overload" ).build ( );
        superhero1.setPowerList ( "photon-blast", "flight", "super-strength", "healing" );
        superhero1.setAssociationList ( "space-stone", "skrulls", "photon", "kree", "avengers");
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
