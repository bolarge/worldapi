package com.klasha.worldapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "city")
@Entity
public class City extends Locality{
    private String type;
    private String density;
}
