package com.klasha.worldapi.dataccess;

import com.klasha.worldapi.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Integer, Country> {
}
