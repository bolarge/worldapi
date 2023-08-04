package com.klasha.worldapi.datatransfer;

import com.klasha.worldapi.model.City;
import com.klasha.worldapi.model.State;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LocalityResourceResponse {
    private String name;
    private String location;
    private String population;
    private String countryCapital;
    private String isoCode2;
    private String isoCode3;
    private String currency;
    private Iterable<City> theCities;
    private Iterable<State> theStates;
}
