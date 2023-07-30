package com.klasha.worldapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
}
