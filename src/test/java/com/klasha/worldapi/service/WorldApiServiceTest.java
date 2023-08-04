package com.klasha.worldapi.service;

import com.klasha.worldapi.dataccess.CountryRepository;
import com.klasha.worldapi.model.Country;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Collection;
import java.util.Optional;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
@SpringBootTest
public class WorldApiServiceTest {

    @Autowired
    private WorldApiService worldApiService;

    @MockBean
    private CountryRepository countryRepository;
    @Test
    @DisplayName("Test findCountryById Success")
    void testFindCountryById(){
        Country country = new Country(1,"Nigeria","100-211",
                2000000,  "Abuja", "NG", "NGN");
        doReturn(Optional.of(country)).when(countryRepository).findById(1);

        Optional<Country> returnedCountry = worldApiService.findById(1);

        Assertions.assertTrue(returnedCountry.isPresent(), "Country was not found");
        Assertions.assertSame(returnedCountry.get(), country, "The country returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findCountryById Not Found")
    void testFindCountryByIdNotFound() {
        doReturn(Optional.empty()).when(countryRepository).findById(1);

        Optional<Country> returnedCountry = worldApiService.findById(1);
        Assertions.assertFalse(returnedCountry.isPresent(), "Widget should not be found");
    }

    @Test
    @DisplayName("Test findAllCountries")
    void testFindAll() {
        Country country1 = new Country(1,"Nigeria","100-211",
                2000000,  "Abuja", "NG", "NGN");
        Country country2 = new Country(2,"Canada","180-229",
                8000000,  "Ottawa", "CN", "CND");
        doReturn(Arrays.asList(country1, country2)).when(countryRepository).findAll();

        Collection<Country> countries = worldApiService.fetchAllCountries();
        Assertions.assertEquals(2, countries.size(), "findAll should return 2 widgets");
    }

    @Test
    @DisplayName("Test save country")
    void testSave() {
        Country country = new Country(1,"Nigeria","100211",
                2000000,  "Abuja", "NG", "NGN");
        doReturn(country).when(countryRepository).save(any());

        Country returnedCountry = worldApiService.create(country);

        Assertions.assertNotNull(returnedCountry, "The saved country should not be null");
        Assertions.assertEquals(1, returnedCountry.getId(), "Id should be same");
    }
}
