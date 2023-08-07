package com.klasha.worldapi.rest;

import com.klasha.worldapi.datatransfer.CountryRecord;
import com.klasha.worldapi.datatransfer.ExchangeRateRecord;
import com.klasha.worldapi.datatransfer.GenericResponse;
import com.klasha.worldapi.datatransfer.StateRecord;
import com.klasha.worldapi.service.WorldApiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "Countries", description = "Country Resource")
@RequestMapping(value = "v1")
@RestController
public class CountryResource {
    private final WorldApiService worldApiService;
    public CountryResource(WorldApiService worldApiService) {
        this.worldApiService = worldApiService;
    }

    @GetMapping("/countries/{countryId}")
    public ResponseEntity<?> getCountryById(@PathVariable("countryId") Integer countryId){
        var queryResponse = worldApiService.findById(countryId);
        return ResponseEntity.ok(queryResponse);
    }

    @GetMapping("/countries/cities/top")
    public ResponseEntity<?> getTopCitiesByCountry(@RequestParam("numberOfCities") String numberOfCities){
        var queryResponse = worldApiService.getTopCitiesByPopulation(numberOfCities);
        return  ResponseEntity.ok(queryResponse);
    }

    @GetMapping("/countries/{countryName}/get-full-details")
    public ResponseEntity<?> getCountryFullDetailsRecord(@PathVariable("countryName") String countryName){
        var queryResponse = worldApiService.getCountryFullDetails(countryName);
        return ResponseEntity.ok(queryResponse);
    }

    @GetMapping("/countries/{countryName}/get-details")
    public ResponseEntity<?> getCountryDetailsRecord(@PathVariable("countryName") String countryName){
        var queryResponse = worldApiService.getCountryDetails(countryName);
        return ResponseEntity.ok(queryResponse);
    }

    @PostMapping("/countries")
    public ResponseEntity<?> createCountryRecord(@Valid @RequestBody CountryRecord countryRecord){
        var requestResponse = worldApiService.createCountry(countryRecord);
        return ResponseEntity.ok(requestResponse);
    }

    @PutMapping("/countries/{countryId}/state")
    public ResponseEntity<?> addStateToCountryRecord(@PathVariable("countryId") Integer countryId, @Valid @RequestBody StateRecord stateRecord){
        var requestResponse = worldApiService.addStateToCountry(countryId, stateRecord);
        return ResponseEntity.ok(requestResponse);
    }

    @PutMapping("/countries/{countryId}/exchange-rate")
    public ResponseEntity<?> addCurrencyToCountry(@PathVariable("countryId") String countryId, ExchangeRateRecord exchangeRateRecord){
        var requestResponse = worldApiService.addCurrencyToCountry(Integer.valueOf(countryId), exchangeRateRecord);
        return  ResponseEntity.ok(requestResponse);
    }

    @GetMapping("/countries/{countryName}/exchange-rate/{targetCurrency}/amount/{amount}")
    public ResponseEntity<?> convertSourceToTargetCurrency(@PathVariable("countryName") String countryName, @PathVariable("amount") String amount,
                                                           @PathVariable("targetCurrency") String targetCurrency){
        var requestResponse = worldApiService.convertSourceToTargetCurrency(countryName, amount, targetCurrency);
        return ResponseEntity.ok(requestResponse);
    }

}
