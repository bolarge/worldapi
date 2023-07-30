package com.klasha.worldapi.service;

import com.klasha.worldapi.model.Country;

import java.util.List;

public interface WorldApiService {

    Country create(Country country);
    List<Country> getAll();
}
