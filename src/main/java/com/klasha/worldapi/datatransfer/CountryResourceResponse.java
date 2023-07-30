package com.klasha.worldapi.datatransfer;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CountryResourceResponse {
    private String name;
    private String location;
    private String population;
    private String size;
    private String countryCapital;
    private String isoCode2;
    private String isoCode3;
}
