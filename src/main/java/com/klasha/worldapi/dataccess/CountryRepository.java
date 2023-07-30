package com.klasha.worldapi.dataccess;

import com.klasha.worldapi.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface CountryRepository extends JpaRepository<Country, Integer> {
    Collection<Country> findCountryByLocation(String location);
    Country findCountryByName(String countryName);

    /*@Query(value = "SELECT c.account_number as accountNumber, c.account_type as accountType FROM Card c " +
            "WHERE c.card_production_status = :cardStatus ORDER BY c.account_number DESC LIMIT :numberOfCards", nativeQuery = true)
    Collection<Country> getListOfCardApplicantAccounts(@Param("cardStatus") String cardStatus, @Param("numberOfCards") int numberOfCards);
    @Query(value = "SELECT c.id as id, c.customer_name as customerName, c.account_number as accountNumber, c.phone_number as phoneNumber, " +
            "c.delivery_address as deliveryAddress, c.card_production_status as cardStatus FROM Card c WHERE c.card_production_status " +
            "= :cardStatus ORDER BY c.account_number DESC LIMIT :numberOfCards", nativeQuery = true)
    Collection<Country> getListOfCardApplicants(@Param("cardStatus") String cardStatus, @Param("numberOfCards") int numberOfCards);*/

}
