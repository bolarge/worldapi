package com.klasha.worldapi.rest;

import com.klasha.worldapi.model.Country;
import com.klasha.worldapi.service.WorldApiService;
import com.google.common.collect.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.doReturn;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class WorldAppRestControllerTest {

    @MockBean
    private WorldApiService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("GET /countries success")
    void testGetCountriesSuccess() throws Exception {
        // Setup our mocked service
        Country country1 = new Country(1,"Nigeria","100-211",
                2000000,  "Abuja", "NG", "NGN");
        Country country2 = new Country(2,"Canada","180-229",
                8000000,  "Ottawa", "CN", "CND");
        doReturn(Lists.newArrayList(country1, country2)).when(service).fetchAllCountries();

        // Execute the GET request
        mockMvc.perform(get("/api/v1/countries"))
                // Validate the response code and content type
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))

                // Validate headers
                .andExpect(header().string(HttpHeaders.LOCATION, "/api/v1/countries"))

                // Validate the returned fields
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].name", is("Widget Name")))
                .andExpect(jsonPath("$[0].description", is("Description")))
                .andExpect(jsonPath("$[0].version", is(1)))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].name", is("Widget 2 Name")))
                .andExpect(jsonPath("$[1].description", is("Description 2")))
                .andExpect(jsonPath("$[1].version", is(4)));
    }

}
