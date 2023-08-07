package com.klasha.worldapi.datatransfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CityRecord(@NotBlank @NotNull @Size(min = 3, max = 3, message = "City code is required!") String cityCode,
                        @NotBlank @NotNull @Size(min = 3, max = 25, message = "City name is required!") String cityName,
                         @NotBlank @NotNull @Size(message = "Location is required!") String cityLocation,
                         @NotBlank @NotNull @Size(message = "Population is required!") String cityPopulation,
                         @NotBlank @NotNull @Size(message = "State code is required!") String stateCode) {}
