package com.klasha.worldapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class Locality extends BaseEntity{
    @Column(name = "name", unique = true)
    protected String name;
    @Column(name = "location")
    protected String location;
    @Column(name = "population")
    protected int population;

    public Locality(Integer id, String name, String location, int population) {
        super(id);
        this.name = name;
        this.location = location;
        this.population = population;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
