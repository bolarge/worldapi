package com.klasha.worldapi.dataccess;

import com.klasha.worldapi.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Integer> {
    State findStateByName(String stateName);
}
