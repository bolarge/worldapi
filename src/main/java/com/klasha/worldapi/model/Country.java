package com.klasha.worldapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "country")
@Entity
public class Country extends Locality {
    private String countryCapital;
    private Set<State> theStates = new HashSet<>();
}
