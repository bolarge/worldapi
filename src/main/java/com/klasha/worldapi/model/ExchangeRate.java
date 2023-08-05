package com.klasha.worldapi.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@Table(name = "exch_rate")
@Entity
public class ExchangeRate extends BaseEntity{
    private String sourceCurrency;
    private String targetCurrency;
    private BigDecimal rate;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    public ExchangeRate(String sourceCurrency, String targetCurrency, BigDecimal rate) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.rate = rate;
    }
}
