package com.klasha.worldapi.rest;

import com.klasha.worldapi.datatransfer.CityRecord;
import com.klasha.worldapi.datatransfer.StateRecord;
import com.klasha.worldapi.service.WorldApiService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Tag(name = "States", description = "State Resource")
@RequiredArgsConstructor
@RequestMapping("v1")
@RestController
public class StateResource {

    private final WorldApiService worldApiService;

    @PostMapping(value = "/states", produces = "application/json")
    public ResponseEntity<?> createStateRecord(@Valid @RequestBody StateRecord stateRecord){
        var requestResponse = worldApiService.createState(stateRecord);
        return ResponseEntity.ok(requestResponse);
    }

    @GetMapping("/states/name/{stateName}")
    public ResponseEntity<?> getAStateDetails(@PathVariable("stateName") String stateName){
        var requestResponse = worldApiService.getStateByName(stateName);
        return ResponseEntity.ok(requestResponse);
    }

    @PutMapping("/state/{stateId}")
    public ResponseEntity<?> addCityToState(@PathVariable("stateId") Integer stateId, CityRecord cityRecord){
        var requestResponse = worldApiService.addCityToState(stateId, cityRecord);
        return ResponseEntity.ok(requestResponse);
    }
}
