package com.klasha.worldapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "country")
@Entity
public class Country extends Locality {
    private String countryCapital;
    private String isoCode2;
    private String isoCode3;
    private Set<State> theStates = new HashSet<>();
}
