package com.example.countires.h2database;

import com.example.countires.domain.Country;
import com.example.countires.domain.Result;

import java.sql.SQLException;
import java.util.List;

/**
 * Класс CountryDao содержит интерфейс
 * answer
 * @autor Феоктистова
 * @version 1.1
 */

public interface CountryDao {
    List<Country> getAllCountries();

    Country getCountryById(int id) throws SQLException;

    void addCountry(Country country);

    void saveResult(String name, String result);

    List<Result> getResults();

    void deleteResult(int id);
}