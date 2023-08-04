package com.klasha.worldapi.dataccess;

import com.klasha.worldapi.model.ExchangeRate;
import org.springframework.data.repository.CrudRepository;

public interface ExchangeRateRepository extends CrudRepository<ExchangeRate, Integer> {
}
