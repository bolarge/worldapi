package com.klasha.worldapi.dataccess;

import com.klasha.worldapi.model.Country;
import com.klasha.worldapi.model.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StateRepository extends JpaRepository<State, Integer> {
    State findStateByName(String stateName);

   /* @Query(value = "SELECT c.name as countryName, c.location as location, c.population as popuation, c.code2 as isoCode2," +
            "c.code3 as isoCode3 FROM Country c WHERE c.name = :countryName", nativeQuery = true)
    Country getCountryByName(@Param("countryName") String countryName);*/
}
