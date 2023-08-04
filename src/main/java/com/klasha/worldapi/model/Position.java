package com.klasha.worldapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "position")
@Entity
public class Position extends BaseEntity {
    private String iso2;
    private String name;
    private String longitude;
    private String latitude;
}
