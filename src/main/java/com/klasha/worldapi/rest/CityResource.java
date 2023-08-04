package com.klasha.worldapi.rest;

import com.klasha.worldapi.datatransfer.CityRecord;
import com.klasha.worldapi.service.WorldApiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Tag(name = "Cities", description = "City Resource")
@RequiredArgsConstructor
@RequestMapping(value = "api/v1")
@RestController
public class CityResource {

    private final WorldApiService worldApiService;
    @PostMapping("/cities")
    public ResponseEntity<?> addCityRecord(@Valid @RequestBody CityRecord cityRecord){
        var requestResponse = worldApiService.createCity(cityRecord);
        return ResponseEntity.ok(requestResponse);

    }

}
