package com.klasha.worldapi.datatransfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
public record StateRecord(@NotBlank @NotNull @Size(min = 3, max = 3, message = "State code is required!") String stateCode,
                          @NotBlank @NotNull @Size(min = 3, max = 25, message = "State name is required!") String stateName,
                          @NotBlank @NotNull @Size(message = "Location is required!") String stateLocation,
                          @NotBlank @NotNull @Size(message = "Population is required!") String statePopulation,
                          @NotBlank @NotNull @Size(message = "State capital is required!") String stateCapital,
                          @NotBlank @NotNull @Size(min = 3, max = 3, message = "City code is required!") String cityCode,
                          @NotBlank @NotNull @Size(min = 3, max = 25, message = "City name is required!") String cityName,
                          @NotBlank @NotNull @Size(message = "Location is required!") String cityLocation,
                          @NotBlank @NotNull @Size(message = "Population is required!") String cityPopulation,
                          String stateId,
                          String countryId) {}
