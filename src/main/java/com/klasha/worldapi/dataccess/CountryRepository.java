package com.klasha.worldapi.dataccess;

import com.klasha.worldapi.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Country findCountryByName(String countryName);
    Country findCountryByCurrencyCode(String currencyCode);
    @Query(value = "SELECT c.id as id, c.name as cityName, c.population as popuation, c.city_code as cityCode" +
            " FROM world_db.city c JOIN world_db.state s ON c.state_id = s.id JOIN world_db.country c2 ON c2.id = s.country_id  " +
            "ORDER BY c.population DESC LIMIT :numberOfCities", nativeQuery = true)
    List<Country> findTopCitiesByPopulation(@Param("numberOfCities") int numberOfCities);


}
