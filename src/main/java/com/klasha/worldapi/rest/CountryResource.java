package com.klasha.worldapi.rest;

import com.klasha.worldapi.datatransfer.CountryResourceResponse;
import com.klasha.worldapi.datatransfer.GenericResponse;
import com.klasha.worldapi.service.WorldAppService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@Tag(name = "Countries", description = "Country Resource")
@RequestMapping(value = "api/v1")
@RestController
public class CountryResource {
    private final WorldAppService worldAppService;

    public CountryResource(WorldAppService worldAppService) {
        this.worldAppService = worldAppService;
    }
    @GetMapping("/countries/{countryName}")
    public ResponseEntity<?> getCountry(@PathVariable("countryName") String countryName){
        GenericResponse<CountryResourceResponse> queryResponse = worldAppService.findCountry(countryName);
        return ResponseEntity.ok(queryResponse);
    }

    @GetMapping("/countries/{countryName}/all")
    public ResponseEntity<?> getCountryWithStatesAndCities(@PathVariable("countryName") String countryName){
        GenericResponse<?> queryResponse = new GenericResponse<>();
        return ResponseEntity.ok(queryResponse);
    }
}
