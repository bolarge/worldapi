package com.klasha.worldapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "city")
@Entity
public class City extends Locality{
    private String cityCode;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private State state;

    public City(Integer id, String name, String location, int population, String cityCode) {
        super(id, name, location, population);
        this.cityCode = cityCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        City city = (City) o;
        return Objects.equals(getCityCode(), city.getCityCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getCityCode());
    }
}
