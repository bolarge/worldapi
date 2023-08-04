package com.klasha.worldapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "state")
@Entity
public class State extends Locality {
    private String stateCode;
    private String stateCapital;
    @JsonManagedReference
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "state", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<City> theCities = new HashSet<>();
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public State(String stateCode, String name, String location, int population, String stateCapital) {
        super(name, location, population);
        this.stateCode = stateCode;
        this.stateCapital = stateCapital;
    }

    public void addCity(City city) {
       city.setState(this);
       getTheCities().add(city);
    }

    public void removeCity(City city) {
        getTheCities().remove(city);
    }

    public void addCities(Set<City> cities) {
        ((City) cities).setState(this);
        getTheCities().addAll(cities);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        State state = (State) o;
        return Objects.equals(getStateCode(), state.getStateCode()) && Objects.equals(getStateCapital(), state.getStateCapital());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getStateCode(), getStateCapital());
    }
}
