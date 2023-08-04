package com.klasha.worldapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "exch_rate")
@Entity
public class ExchangeRate extends BaseEntity{
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal rate;
}
