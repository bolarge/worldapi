package com.klasha.worldapi.dataccess;

import com.klasha.worldapi.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CityRepository extends JpaRepository<City, Integer> {
}
