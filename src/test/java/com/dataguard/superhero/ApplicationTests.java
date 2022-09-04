package com.dataguard.superhero;

import com.dataguard.superhero.web.request.SuperheroBaseDTO;
import com.dataguard.superhero.web.request.SuperheroDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ApplicationTests {

    ObjectMapper om = new ObjectMapper ( );
    @Autowired
    MockMvc mockMvc;
    List<SuperheroDTO> testData;

    public static final String SUPERHEROS_PATH = "/superheros";
    public static final String SUPERHEROS_ID_PATH = "/superheros/{id}";
    public static final String SUPERHEROS_ID_PUT_ATTRIBUTE_PATH = "/superheros/{id}/attributes/{type}";
    public static final String SUPERHEROS_ID_ATTRIBUTE_PATH = "/superheros/{id}/attributes/{type}/{name}";
    public static final String BASE_PATH = "/api/v1";

    @Test
    @Order(1)
    public void test_CreateSuperheros() throws Exception {

        testData = getTestData ( );

        for (SuperheroDTO superhero : testData) {
            mockMvc.perform ( post ( BASE_PATH + SUPERHEROS_PATH )
                            .contentType ( "application/json" )
                            .content ( om.writeValueAsString ( superhero ) ) )
                    .andExpect ( status ( ).isCreated ( ) );

            System.out.println ( om.writeValueAsString ( superhero )  );
        }

    }

    @Test
    @Order(2)
    public void test_CreateSuperheros_WhenNameAndAliasIsEmpty_ShouldReturn_400() throws Exception {
        SuperheroDTO superhero = SuperheroDTO.builder ( )
                .name ( "" )
                .alias ( null )
                .origin ( "Kidnapped in Afghanistan, created the first iron-man suit to escape." )
                .build ( );

        mockMvc.perform ( post ( BASE_PATH + SUPERHEROS_PATH )
                        .contentType ( "application/json" )
                        .content ( om.writeValueAsString ( superhero ) ) )
                .andExpect ( status ( ).isBadRequest ( ) );
    }

    @Test
    @Order(3)
    public void test_GetAllSuperheros() throws Exception {
        Integer pageSize=10;
        Integer pageNumber=0;

        mockMvc.perform ( get ( BASE_PATH + SUPERHEROS_PATH, pageNumber, pageSize ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) )
                .andExpect ( jsonPath ( "$.total_records", Matchers.is ( 2 ) ) )
                .andReturn ( );
    }


    @Test
    @Order(4)
    public void testGetSuperhero_withID_ShouldReturn_200_OK() throws Exception {

        mockMvc.perform ( get ( BASE_PATH + SUPERHEROS_ID_PATH, 1L ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }

    @Test
    @Order(5)
    public void testGetSuperhero_withID_ShouldReturn_NOT_FOUND() throws Exception {
        Long dummy = 999L;
        mockMvc.perform ( get ( BASE_PATH + SUPERHEROS_ID_PATH, dummy ) )
                //.andDo ( print ( ) )
                .andExpect ( status ( ).isNotFound ( ) )
                .andReturn ( );
    }

    @Test
    @Order(6)
        public void test_AddAttribute_Weapon_ShouldReturn_OK() throws Exception {
        mockMvc.perform ( post ( BASE_PATH + SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, "weapon", "run-fast" )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }
    @Test
    @Order(7)
    public void test_AddAttribute_Power_ShouldReturn_OK() throws Exception {
        mockMvc.perform ( post ( BASE_PATH + SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, "power", "water" )
                        .contentType ( "application/json" ) )
//                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }
    @Test
    @Order(7)
    public void test_AddAttribute_Association_ShouldReturn_OK() throws Exception {
        mockMvc.perform ( post ( BASE_PATH + SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, "association", "greate" )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }
    @Test
    @Order(8)
    public void test_AddAttribute_Association_WhenSuperheroIsNotFound_ShouldReturn_404() throws Exception {
        Long dummy = 999L;
        mockMvc.perform ( post ( BASE_PATH + SUPERHEROS_ID_ATTRIBUTE_PATH, dummy, "association", "greate" )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) )
                .andExpect ( status ( ).isNotFound ( ) )
                .andReturn ( );
    }

    @Test
    @Order(10)
    public void test_AddAttribute_ShouldReturn_BAD_REQUEST() throws Exception {
        String dummyType = "weapon3";
        mockMvc.perform ( post ( BASE_PATH + SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, dummyType, "run-fast" )
                        .contentType ( "application/json" ) )
                //.andDo ( print ( ) )
                .andExpect ( status ( ).is4xxClientError ( ) )
                .andReturn ( );
    }

    @Test
    @Order(15)
    public void test_RemoveAttribute_ShouldReturn_OK() throws Exception {
        mockMvc.perform ( delete ( BASE_PATH + SUPERHEROS_ID_ATTRIBUTE_PATH, 1L, "weapon", "run-fast" )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }

    @Test
    @Order(20)
    public void test_PutAllAttributes_Weapon_ShouldReturn_OK() throws Exception {
        List<String> stringList = List.of ( "run-fast2", "run-fast3" );
        mockMvc.perform ( put ( BASE_PATH + SUPERHEROS_ID_PUT_ATTRIBUTE_PATH, 1L, "weapon" )
                        .contentType ( "application/json" )
                        .content ( om.writeValueAsString ( stringList ) ) )
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    @Order(21)
    public void test_PutAllAttributes_Power_ShouldReturn_OK() throws Exception {
        List<String> stringList = List.of ( "run-fast2", "run-fast3" );
        mockMvc.perform ( put ( BASE_PATH + SUPERHEROS_ID_PUT_ATTRIBUTE_PATH, 1L, "power" )
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

        mockMvc.perform ( patch ( BASE_PATH + SUPERHEROS_ID_PATH, 1L )
                        .contentType ( "application/json" )
                        .content ( om.writeValueAsString ( superheroBaseDTO) ))
                .andExpect ( status ( ).isOk ( ) );
    }

    @Test
    @Order(40)
    public void test_DeleteSuperhero_ShouldReturn_OK() throws Exception {

        mockMvc.perform ( delete ( BASE_PATH + SUPERHEROS_ID_PATH, 1L )
                        .contentType ( "application/json" ) )
                .andDo ( print ( ) ).andExpect ( status ( ).isOk ( ) )
                .andReturn ( );
    }

    private List<SuperheroDTO> getTestData() {

        List<SuperheroDTO> data = new ArrayList<> ( );

        SuperheroDTO superhero1 = SuperheroDTO.builder ( ).name ( "Carol Danvers" ).alias ( "Captain Marvel" ).origin ( "Exposed to Space Stone reactor overload" ).build ( );
        superhero1.setPowerList ( Arrays.asList ( "photon-blast", "flight", "super-strength", "healing" ));
        superhero1.setAssociationList ( Arrays.asList ("space-stone", "skrulls", "photon", "kree", "avengers"));
        superhero1.setWeaponList ( new ArrayList<> (  ));


        SuperheroDTO superhero2 = SuperheroDTO.builder ( )
                .name ( "Tony Stark" )
                .alias ( "Iron Man" )
                .origin ( "Kidnapped in Afghanistan, created the first iron-man suit to escape." )
                .build ( );
        superhero2.setPowerList ( Arrays.asList ("genius-intelligence", "wealth" ));
        superhero2.setAssociationList ( Arrays.asList ("war-machine", "avengers", "jarvis", "thanos", "pepper-potts" ));
        superhero2.setWeaponList ( Arrays.asList ("arc-reactor", "iron-man-suit", "iron-legion" ));


        data.add ( superhero1 );
        data.add ( superhero2 );

        return data;
    }

}
