package com.klasha.worldapi.dataaccess;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.connection.ConnectionHolder;
import com.github.database.rider.junit5.DBUnitExtension;
import com.google.common.collect.Lists;
import com.klasha.worldapi.dataccess.CountryRepository;
import com.klasha.worldapi.model.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;

@ExtendWith(DBUnitExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class WorldAppRepositoryTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private CountryRepository countryRepository;

    public ConnectionHolder getConnectionHolder() {
        return () -> dataSource.getConnection();
    }
    @Test
    @DataSet("countries.yml")
    void testFindAll() {
        List<Country> countries = Lists.newArrayList(countryRepository.findAll());
        Assertions.assertEquals(2, countries.size(), "Expected 2 countries in the database");
    }

    @Test
    @DataSet("countries.yml")
    void testFindByIdSuccess() {
        Optional<Country> country = countryRepository.findById(1);
        Assertions.assertTrue(country.isPresent(), "We should find a country with ID 1");

        Country country1 = country.get();
        Assertions.assertEquals(1, country1.getId(), "The country ID should be 1");
        Assertions.assertEquals("Country 1", country1.getName(), "Incorrect country name");
        Assertions.assertEquals("This is country 1", country1.getLocation(), "Incorrect country location");
        Assertions.assertEquals("ABS", country1.getIsoCode2(), "Incorrect country iso code 2");
    }

    @Test
    @DataSet("countries.yml")
    void testFindByIdNotFound() {
        Optional<Country> country = countryRepository.findById(3);
        Assertions.assertFalse(country.isPresent(), "A country with ID 3 should not be found");
    }

   /* @Test
    @DataSet("countries.yml")
    void testFindCountriesWithNameLike() {
        List<Country> widgets = countryRepository.findWidgetsWithNameLike("Widget%");
        Assertions.assertEquals(2, widgets.size());
    }*/
}
