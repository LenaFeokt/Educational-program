package com.example.countires.h2database;

import com.example.countires.domain.Country;
import com.example.countires.domain.Result;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс реализующий интерфейс
 * answer
 * @autor Феоктистова
 * @version 1.1
 */

public class JdbcCountryDao implements CountryDao {
    //private Connection connection;
    private final Connection connection;

    public JdbcCountryDao(Connection connection) {
        this.connection = connection;
    }
    /** Методы getAllCountries(), getCountryById(int id), addCountry(Country country),
     * saveResult(String name, String result), getResults(), deleteResult(int id)
     *  содержат запрос к базе данных при помощи PreparedStatement */
    @Override
    public List<Country> getAllCountries() {
        List<Country> countries = new ArrayList<>();
        String sql = "SELECT * FROM countries";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String name = resultSet.getString("country");
                String capital = resultSet.getString("capital");
                Country country = new Country(name, capital);
                countries.add(country);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return countries;
    }

    @Override
    public Country getCountryById(int id) throws SQLException {
        String sql = "SELECT * FROM countries WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("country");
                    String capital = resultSet.getString("capital");
                    return new Country(name, capital);
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public void addCountry(Country country) {
        String sql = "INSERT INTO countries (country, capital) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, country.getCountry());
            statement.setString(2, country.getCapital());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveResult(String name, String result) {
        String sql = "INSERT INTO results (name, result) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setString(2, result);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Result> getResults() {
        List<Result> results = new ArrayList<>();
        String sql = "SELECT * FROM RESULTS";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String result = resultSet.getString("result");
                Result country = new Result(Integer.parseInt(id), name, result);
                results.add(country);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @Override
    public void deleteResult(int id) {
        String sql = "DELETE FROM RESULTS WHERE ID = (?);";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}