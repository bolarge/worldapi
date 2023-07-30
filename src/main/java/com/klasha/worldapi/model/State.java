package com.klasha.worldapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashSet;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "state")
@Entity
public class State extends Locality{
    private String capitalCity;
    Set<City> theCities = new HashSet<>();
}
