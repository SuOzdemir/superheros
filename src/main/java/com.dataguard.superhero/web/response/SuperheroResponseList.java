package com.dataguard.superhero.web.response;

import com.dataguard.superhero.dao.entity.Superhero;
import com.dataguard.superhero.web.request.SuperheroDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class SuperheroResponseList {


    @Schema(description = "The number of pages returned for the request made.")
    private int pageCount;

    @Schema(description = "page_number is used to paginate through large result sets.", defaultValue = "0")
    private int pageNumber;

    @Schema(description = "The number of records returned with a single API call.", defaultValue = "10")
    private int pageSize;

    @Schema(description = "The total number of all the records available across pages.\n" +
            "In other words, its the participant count of the meeting")
    private long totalRecords;

    @Schema(description = "The meeting's participants list")
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<SuperheroDTO> superheroList;

    public static SuperheroResponseList of(Page<Superhero> pagedResult) {
        return SuperheroResponseList.builder()
                .superheroList(pagedResult.getContent().stream()
                        .map( SuperheroDTO::of )
                        .collect( Collectors.toList()))
                .pageCount(pagedResult.getTotalPages())
                .pageNumber(pagedResult.getNumber())
                .pageSize(pagedResult.getSize())
                .totalRecords(pagedResult.getTotalElements())
                .build();
    }
}
