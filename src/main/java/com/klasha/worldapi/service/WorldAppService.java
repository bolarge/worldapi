package com.klasha.worldapi.service;

import com.klasha.worldapi.datatransfer.CountryResourceResponse;
import com.klasha.worldapi.datatransfer.GenericResponse;
import com.klasha.worldapi.model.Country;

import java.util.List;
import java.util.Optional;

public interface WorldAppService {

    Country create(Country country);
    List<Country> fetchAll();
    GenericResponse<CountryResourceResponse> findCountryByName(String countryName);
    Optional<Country> findById(Integer id);
}
