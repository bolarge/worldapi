package com.klasha.worldapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@MappedSuperclass
public class Locality extends BaseEntity{
    @Column(name = "name")
    protected String name;
    @Column(name = "location")
    protected String location;
    @Column(name = "population")
    protected String population;
    @Column(name = "size")
    protected String size;
    public Locality(Integer id, LocalDate dateCreated, String name, String location, String population, String size) {
        super(id, dateCreated);
        this.name = name;
        this.location = location;
        this.population = population;
        this.size = size;
    }
}
