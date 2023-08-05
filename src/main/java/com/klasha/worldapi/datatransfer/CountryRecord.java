package com.klasha.worldapi.datatransfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CountryRecord(@NotBlank @NotNull @Size(min = 2, max = 2, message = "iso code 2 is required!") String isoCode2,
                            @NotBlank @NotNull @Size(min = 3, max = 3, message = "iso code 3 is required!") String isoCode3,
                            @NotBlank @NotNull @Size(min = 5, max = 25, message = "Country name is required!") String countryName,
                            @NotBlank @NotNull @Size(message = "Location is required!") String countryLocation,
                            @NotBlank @NotNull @Size(message = "Population is required!") String countryPopulation,
                            @NotBlank @NotNull @Size(message = "Country capital is required!") String countryCapital,
                            @NotBlank @NotNull @Size(min = 3, max = 3, message = "State code is required!") String stateCode,
                            @NotBlank @NotNull @Size(min = 5, max = 25, message = "State name is required!") String stateName,
                            @NotBlank @NotNull @Size(message = "Location is required!") String stateLocation,
                            @NotBlank @NotNull @Size(message = "Population is required!") String statePopulation,
                            @NotBlank @NotNull @Size(message = "Currency code is required!")String currencyCode,
                            String id) {
}
