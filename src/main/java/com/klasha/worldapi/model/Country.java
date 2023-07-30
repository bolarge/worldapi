package com.klasha.worldapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
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

    public Country(Integer id, LocalDate dateCreated, String name, String location, String population, String size, String countryCapital, String isoCode2, String isoCode3) {
        super(id, dateCreated, name, location, population, size);
        this.countryCapital = countryCapital;
        this.isoCode2 = isoCode2;
        this.isoCode3 = isoCode3;
    }
}
