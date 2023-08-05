package com.klasha.worldapi.datatransfer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ExchangeRateRecord(@NotBlank @NotNull @Size(min = 3, max = 3, message = "Source currency is required!") String sourceCurrency,
                                 @NotBlank @NotNull @Size(min = 3, max = 3, message = "Target currency is required!") String targetCurrency,
                                 @NotBlank @NotNull @Size(message = "Rate is required!") String rate,
                                 String countryName) {}
