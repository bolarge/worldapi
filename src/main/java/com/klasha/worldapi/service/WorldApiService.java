package com.klasha.worldapi.service;

import com.klasha.worldapi.datatransfer.*;
import com.klasha.worldapi.model.Country;
import com.klasha.worldapi.model.City;
import com.klasha.worldapi.model.State;

import java.util.Collection;
import java.util.Optional;

public interface WorldApiService {

    //City Operations
    GenericResponse<CityRecord> createCity(CityRecord cityRecord);
    Collection<City> fetchAllCities();

    //State Operations

    GenericResponse<LocalityResourceResponse> getStateByName(String stateName);
    GenericResponse<StateRecord> createState(StateRecord stateRecord);
    GenericResponse<StateRecord> addCityToState(Integer stateId, CityRecord cityRecord);
    Collection<State> fetchAllStates();

    //Country Operations
    Optional<Country> findById(Integer id);
    Country create(Country country);
    GenericResponse<CountryRecord> createCountry(CountryRecord countryRecord);
    GenericResponse<CountryRecord> addStateToCountry(Integer countryId, StateRecord stateRecord);
    Collection<Country> fetchAllCountries();
    GenericResponse<LocalityResourceResponse> getCountryDetails(String countryName);
    GenericResponse<LocalityResourceResponse> getCountryFullDetails(String countryName);
    GenericResponse<Iterable<Country>> getTopCitiesByPopulation(String limit);
    GenericResponse<ExchangeRateRecord> addCurrencyToCountry(Integer countryId, ExchangeRateRecord exchangeRateRecord);
    GenericResponse<ConvertedAmountRecord> convertSourceToTargetCurrency(String sourceCountry, String amount, String targetCurrency);
}
