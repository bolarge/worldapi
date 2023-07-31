package com.klasha.worldapi.service;

import com.klasha.worldapi.dataccess.CountryRepository;
import com.klasha.worldapi.model.Country;
import com.klasha.worldapi.service.WorldAppService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
@SpringBootTest
public class WorldAppServiceTest {

    @Autowired
    private WorldAppService worldAppService;

    @MockBean
    private CountryRepository countryRepository;
    @Test
    @DisplayName("Test findCountryById Success")
    void testFindCountryById(){
        Country country = new Country(1,LocalDate.now(),"Nigeria","100-211",
                "2000000","400 sqm",  "Abuja", "NG", "NGN");
        doReturn(Optional.of(country)).when(countryRepository).findById(1);

        Optional<Country> returnedCountry = worldAppService.findById(1);

        Assertions.assertTrue(returnedCountry.isPresent(), "Country was not found");
        Assertions.assertSame(returnedCountry.get(), country, "The country returned was not the same as the mock");
    }

    @Test
    @DisplayName("Test findCountryById Not Found")
    void testFindCountryByIdNotFound() {
        doReturn(Optional.empty()).when(countryRepository).findById(1);

        Optional<Country> returnedCountry = worldAppService.findById(1);

        Assertions.assertFalse(returnedCountry.isPresent(), "Widget should not be found");
    }

    @Test
    @DisplayName("Test findAllCountries")
    void testFindAll() {
        Country country1 = new Country(1,LocalDate.now(),"Nigeria","100-211",
                "2000000","400 sqm",  "Abuja", "NG", "NGN");
        Country country2 = new Country(2,LocalDate.now(),"Canada","180-229",
                "8000000","700 sqm",  "Ottawa", "CN", "CND");
        doReturn(Arrays.asList(country1, country2)).when(countryRepository).findAll();

        List<Country> countries = worldAppService.fetchAll();

        Assertions.assertEquals(2, countries.size(), "findAll should return 2 widgets");
    }

    @Test
    @DisplayName("Test save country")
    void testSave() {
        Country country = new Country(1,LocalDate.now(),"Nigeria","100211",
                "2000000","400 sqm",  "Abuja", "NG", "NGN");
        doReturn(country).when(countryRepository).save(any());

        Country returnedCountry = worldAppService.create(country);

        Assertions.assertNotNull(returnedCountry, "The saved country should not be null");
        Assertions.assertEquals(1, returnedCountry.getId(), "Id should be same");
    }
}
