package com.klasha.worldapi.service.impl;

import com.klasha.worldapi.dataccess.CityRepository;
import com.klasha.worldapi.dataccess.CountryRepository;
import com.klasha.worldapi.dataccess.StateRepository;
import com.klasha.worldapi.datatransfer.*;
import com.klasha.worldapi.model.City;
import com.klasha.worldapi.model.Country;
import com.klasha.worldapi.model.ExchangeRate;
import com.klasha.worldapi.model.State;
import com.klasha.worldapi.service.WorldApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Service
public class WorldApiServiceImpl implements WorldApiService {

    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    @Override
    public GenericResponse<CityRecord> createCity(CityRecord cityRecord) {
        Optional<State> queriedState = stateRepository.findById(Integer.valueOf(cityRecord.stateCode()));
        State foundState = queriedState.orElseGet(queriedState::orElseThrow);

        City city = new City();
        city.setName(cityRecord.cityName()); city.setLocation(cityRecord.cityLocation());
        city.setPopulation(Integer.parseInt(cityRecord.cityPopulation())); city.setCityCode(cityRecord.cityCode());
        City addedCity = cityRepository.save(city);
        GenericResponse<CityRecord> requestResponse = new GenericResponse<>();
        CityRecord cRec = new CityRecord(addedCity.getCityCode(), addedCity.getName(), addedCity.getLocation(),
                String.valueOf(addedCity.getPopulation()), addedCity.getState().getStateCode());
        requestResponse.setData(cRec);
        requestResponse.setMessage("City successfully created");
        return requestResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<City> fetchAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public GenericResponse<StateRecord> createState(StateRecord stateRecord) {
        var newState = new State();
        newState.setStateCapital(stateRecord.stateCapital());
        newState.setStateCode(stateRecord.stateCode());
        newState.setName(stateRecord.stateName());
        newState.setLocation(stateRecord.stateLocation());
        newState.setPopulation(Integer.parseInt(stateRecord.statePopulation()));

        var city = new City();
        city.setName(stateRecord.cityName()); city.setLocation(stateRecord.cityLocation());
        city.setPopulation(Integer.parseInt(stateRecord.cityPopulation())); city.setCityCode(stateRecord.cityCode());
        city.setState(newState);

        newState.addCity(city);
        newState = stateRepository.save(newState);

        GenericResponse<StateRecord> requestResponse = new GenericResponse<>();
        StateRecord sRec = new StateRecord(newState.getStateCode(),newState.getName(),newState.getLocation(),
                String.valueOf(newState.getPopulation()), newState.getStateCapital(), stateRecord.cityName(),stateRecord.cityLocation(),
                stateRecord.cityPopulation(),stateRecord.cityCode(),"","");
        requestResponse.setData(sRec);
        requestResponse.setMessage("State created successfully");
        return requestResponse;
    }

    @Override
    public GenericResponse<StateRecord> addCityToState(Integer stateId, CityRecord cityRecord) {
        Optional<State> queriedState = stateRepository.findById(stateId);
        State foundState = queriedState.orElseGet(queriedState::orElseThrow);

        City newCity = new City();
        newCity.setCityCode(cityRecord.cityCode());
        newCity.setName(cityRecord.cityName());
        newCity.setPopulation(Integer.parseInt(cityRecord.cityPopulation()));
        newCity.setLocation(cityRecord.cityLocation());

        foundState.addCity(newCity);
        foundState = stateRepository.save(foundState);

        GenericResponse<StateRecord> requestResponse = new GenericResponse<>();
        StateRecord sRec = new StateRecord(foundState.getStateCode(), foundState.getName(), foundState.getLocation(),
                String.valueOf(foundState.getPopulation()), foundState.getStateCapital(), cityRecord.cityCode(), newCity.getName(), newCity.getLocation(),
                String.valueOf(newCity.getPopulation()), "", "");
        requestResponse.setData(sRec);
        requestResponse.setMessage(newCity.getName() +" city was successfully added to state " + foundState.getName());

        return requestResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<State> fetchAllStates() {
        return stateRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public GenericResponse<LocalityResourceResponse> getStateByName(String stateName) {
        var state = stateRepository.findStateByName(stateName);
        var resourceResponse = LocalityResourceResponse.builder()
                .name(state.getName())
                .countryCapital(state.getStateCapital())
                .population(String.valueOf(state.getPopulation()))
                .location(state.getLocation())
                .theCities(state.getTheCities())
                .build();
        GenericResponse<LocalityResourceResponse> response = new GenericResponse<>();
        response.setMessage("Successfully retrieved details for " + stateName + " state");
        response.setData(resourceResponse);
        return response;
    }


    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Override
    @Transactional(readOnly = true)
    public Collection<Country> fetchAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Country> findById(Integer id) {
        return getById(id);
    }

    @Override
    public GenericResponse<CountryRecord> createCountry(CountryRecord countryRecord) {
        var newCountry = new Country();
        newCountry.setIsoCode2(countryRecord.isoCode2());
        newCountry.setIsoCode3(countryRecord.isoCode3());
        newCountry.setCapitalCity(countryRecord.countryCapital());
        newCountry.setName(countryRecord.countryName());
        newCountry.setLocation(countryRecord.countryLocation());
        newCountry.setPopulation(Integer.parseInt(countryRecord.countryPopulation()));
        newCountry.setCurrencyCode(countryRecord.currencyCode());

        var newState = new State();
        newState.setName(countryRecord.stateName());
        newState.setStateCapital(countryRecord.stateName());
        newState.setStateCode(countryRecord.stateCode());
        newState.setLocation(countryRecord.stateLocation());
        newState.setPopulation(Integer.parseInt(countryRecord.statePopulation()));

        newCountry.addState(newState);
        newCountry = countryRepository.save(newCountry);

        GenericResponse<CountryRecord> requestResponse = new GenericResponse<>();
        CountryRecord cRec = new CountryRecord(newCountry.getIsoCode2(), newCountry.getIsoCode3(),newCountry.getName(),
                newCountry.getLocation(), String.valueOf(newCountry.getPopulation()), newCountry.getCapitalCity(), newState.getStateCode(),
                newState.getStateCapital(), newState.getLocation(), String.valueOf(newState.getPopulation()), newCountry.getCurrencyCode(), "");
        requestResponse.setData(cRec);
        requestResponse.setMessage("Country " +countryRecord.countryName() +" was created successfully");
        return requestResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public GenericResponse<LocalityResourceResponse> getCountryDetails(String countryName) {
        var country = getCountryByName(countryName);
        LocalityResourceResponse resourceResponse = LocalityResourceResponse.builder().name(country.getName())
                .countryCapital(country.getCapitalCity())
                .location(country.getLocation())
                .population(String.valueOf(country.getPopulation()))
                .isoCode2(country.getIsoCode2())
                .isoCode3((country.getIsoCode3()))
                .build();
        GenericResponse<LocalityResourceResponse> localityResourceResponseGenericResponse = new GenericResponse<>();
        localityResourceResponseGenericResponse.setMessage("Successfully retrieved details for country "+ countryName);
        localityResourceResponseGenericResponse.setData(resourceResponse);
        return localityResourceResponseGenericResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public GenericResponse<LocalityResourceResponse> getCountryFullDetails(String countryName) {
        var country = getCountryByName(countryName);
        LocalityResourceResponse resourceResponse = LocalityResourceResponse.builder().name(country.getName())
                .countryCapital(country.getCapitalCity())
                .location(country.getLocation())
                .population(String.valueOf(country.getPopulation()))
                .isoCode2(country.getIsoCode2())
                .isoCode3((country.getIsoCode3()))
                .theStates(country.getTheStates())
                .build();
        var localityResourceResponseGenericResponse = new GenericResponse<LocalityResourceResponse>();
        localityResourceResponseGenericResponse.setMessage("Successfully retrieved full details for country "+ countryName);
        localityResourceResponseGenericResponse.setData(resourceResponse);
        return localityResourceResponseGenericResponse;
    }

    @Override
    public GenericResponse<CountryRecord> addStateToCountry(Integer countryId, StateRecord stateRecord) {
        var queriedCountry = getById(countryId);
        var foundCountry = queriedCountry.orElseGet(queriedCountry::orElseThrow);

        var newState = new State();
        newState.setName(stateRecord.stateName());
        newState.setStateCapital(stateRecord.stateName());
        newState.setStateCode(stateRecord.stateCode());
        newState.setLocation(stateRecord.stateLocation());
        newState.setPopulation(Integer.parseInt(stateRecord.statePopulation()));

        foundCountry.addState(newState);
        foundCountry = countryRepository.save(foundCountry);

        var requestResponse = new GenericResponse<CountryRecord>();
        CountryRecord cRec = new CountryRecord(foundCountry.getIsoCode2(), foundCountry.getIsoCode3(),foundCountry.getName(),
                foundCountry.getLocation(), String.valueOf(foundCountry.getPopulation()), foundCountry.getCapitalCity(), newState.getStateCode(),
                newState.getStateCapital(), newState.getLocation(), String.valueOf(newState.getPopulation()), foundCountry.getCurrencyCode(), "");
        requestResponse.setData(cRec);
        requestResponse.setMessage("State " +stateRecord.stateName() +" was successfully added to Country " + foundCountry.getName());
        return requestResponse;
    }

    public GenericResponse<ExchangeRateRecord> addCurrencyToCountry(Integer countryId, ExchangeRateRecord exchangeRateRecord){
        var queriedCountry = getById(countryId);
        var foundCountry = queriedCountry.orElseGet(queriedCountry::orElseThrow);

        var exchangeRate = new ExchangeRate(exchangeRateRecord.sourceCurrency(),exchangeRateRecord.targetCurrency(),
                new BigDecimal(exchangeRateRecord.rate()));

        foundCountry.addExchangeRate(exchangeRate);
        foundCountry = countryRepository.save(foundCountry);

        var requestResponse = new GenericResponse<ExchangeRateRecord>();
        var rateRecorde = new ExchangeRateRecord(exchangeRateRecord.sourceCurrency(), exchangeRateRecord.targetCurrency(),
                exchangeRateRecord.rate(), foundCountry.getName());
        requestResponse.setData(rateRecorde);
        requestResponse.setMessage("Exchange rate successfully for " + foundCountry.getName());
        return  requestResponse;
    }

    private Optional<Country> getById(Integer countryId) {
        return countryRepository.findById(countryId);
    }

    private Country getCountryByName(String countryName) {
        return countryRepository.findCountryByName(countryName);
    }

    @Override
    public GenericResponse<Iterable<Country>> getTopCitiesByPopulation(String limit) {
        var countryList = countryRepository.findTopCitiesByPopulation(Integer.parseInt(limit));
        GenericResponse<Iterable<Country>> queryResponse = new GenericResponse<>();
        queryResponse.setData(countryList);
        queryResponse.setMessage("List of top countries");
        return queryResponse;
    }

    @Override
    public GenericResponse<ConvertedAmountRecord> convertSourceToTargetCurrency(String sourceCountry, String amount, String targetCurrency) {
        var senderCountry = getCountryByName(sourceCountry);
        var receiverCountry = countryRepository.findCountryByCurrencyCode(targetCurrency);
        log.info("Size is "+receiverCountry.getExchangeRates().size());
        final Optional<ExchangeRate> exchangeRate = receiverCountry.getExchangeRates().stream().filter(
                exchangeRate1 -> senderCountry.getCurrencyCode()
                .equals(exchangeRate1.getSourceCurrency())).findFirst();
        log.info(String.valueOf(exchangeRate.isPresent()));

        var selectedRate = exchangeRate.orElseGet(exchangeRate::orElseThrow);
        var rateConvertedAmount = new BigDecimal(amount).multiply(selectedRate.getRate());
        var locale = new Locale("en", receiverCountry.getIsoCode2());
        var numberFormat = NumberFormat.getCurrencyInstance(locale);
        var formattedAmount = numberFormat.format(rateConvertedAmount);

        var requestResponse = new GenericResponse<ConvertedAmountRecord>();
        var convertedAmountRecord = new ConvertedAmountRecord(selectedRate.getTargetCurrency(), formattedAmount);
        requestResponse.setData(convertedAmountRecord);
        requestResponse.setMessage(String.format("Conversion from %s to %s was successful for %s ", targetCurrency,
                selectedRate.getSourceCurrency(), amount));

        return requestResponse;
    }

}

