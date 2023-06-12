package com.example.countires.domain;

/**
 * Класс реализующий вывод вопроса
 * answer
 * @autor Феоктистова
 * @version 1.1
 */

public class Question {
    private Country country;

    public Question(Country country) {
        this.country = country;
    }
    /** Метод указывает на правильность соответствия страны и столицы */
    public boolean checkAnswer(Answer answer) {
        return answer.getAnswer().equalsIgnoreCase(country.getCapital());
    }
    /** Метод для составление вопроса */
    public String getQuestionText() {
        return "Столица страны " + country.getCountry() + ":";
    }

    public Country getCountry() {
        return country;
    }
}