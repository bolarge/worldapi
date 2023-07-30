package com.klasha.worldapi.service.impl;

import com.klasha.worldapi.dataccess.CountryRepository;
import com.klasha.worldapi.datatransfer.CountryResourceResponse;
import com.klasha.worldapi.datatransfer.GenericResponse;
import com.klasha.worldapi.model.Country;
import com.klasha.worldapi.service.WorldAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class WorldAppServiceImpl implements WorldAppService {

    private final CountryRepository countryRepository;

    @Override
    public Country create(Country country) {
        return countryRepository.save(country);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Country> fetchAll() {
        return countryRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public GenericResponse<CountryResourceResponse> findCountryByName(String countryName) {
        Country country = countryRepository.findCountryByName(countryName);
        CountryResourceResponse resourceResponse = CountryResourceResponse.builder().name(country.getName())
                .countryCapital(country.getCountryCapital())
                .location(country.getLocation())
                .population(country.getPopulation())
                .isoCode2(country.getIsoCode2())
                .isoCode3((country.getIsoCode3()))
                .build();
        GenericResponse<CountryResourceResponse> response = new GenericResponse<>();
        response.setMessage("Successfully retrieved details for %s");
        response.setData(resourceResponse);
        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Country> findById(Integer id) {
        return countryRepository.findById(id);
    }
}
