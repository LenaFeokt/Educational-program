package com.example.countires.domain;

/**
 * Класс реализующий получение страны и её столицы
 * answer
 * @autor Феоктистова
 * @version 1.1
 */
public class Country {
    private String country;
    private String capital;
    /** Контроллер класса */
    public Country(String country, String capital) {
        this.country = country;
        this.capital = capital;
    }
    /** Метод возвращает ответ */
    public String getCountry() {
        return country;
    }

    public String getCapital() {
        return capital;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }
}
