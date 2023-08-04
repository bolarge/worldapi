package com.klasha.worldapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "country")
@Entity
public class Country extends Locality {
    @Column(name = "capital_city")
    private String countryCapital;
    @Column(name = "code2")
    private String isoCode2;
    @Column(name = "code3")
    private String isoCode3;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<State> theStates = new HashSet<>();

    public Country(Integer id, String name, String location, int population, String countryCapital, String isoCode2, String isoCode3) {
        super(name, location, population);
        this.countryCapital = countryCapital;
        this.isoCode2 = isoCode2;
        this.isoCode3 = isoCode3;
    }

    public void addState(State state) {
        state.setCountry(this);
        getTheStates().add(state);
    }

    public void removeState(State state) {
        getTheStates().remove(state);
    }

    public void addStates(Set<State> states) {
        ((State) states).setCountry(this);
        getTheStates().addAll(states);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Country country = (Country) o;
        return Objects.equals(getCountryCapital(), country.getCountryCapital()) && Objects.equals(getIsoCode3(), country.getIsoCode3());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCountryCapital(), getIsoCode3());
    }
}
