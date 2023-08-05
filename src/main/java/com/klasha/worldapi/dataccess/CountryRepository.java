package com.klasha.worldapi.dataccess;

import com.klasha.worldapi.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findCountryByName(String countryName);
    Country findCountryByCurrencyCode(String currencyCode);
    @Query(value = "SELECT c.name as countryName, c.location as location, c.population as popuation, c.code2 as isoCode2," +
            "c.code3 as isoCode3 FROM Country c ORDER BY c.population DESC LIMIT :numberOfCities", nativeQuery = true)
    List<Country> findTopCitiesByPopulation(@Param("numberOfCities") int numberOfCities);

}
