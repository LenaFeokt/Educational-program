package com.example.countires.domain;

import java.util.List;
import java.util.Random;

/**
 * Класс реализующий генерацию вопросов для теста
 * answer
 * @autor Феоктистова
 * @version 1.1
 */

public class Quiz {
    private final List<Country> countries;

    private int score;

    public Quiz(List<Country> countries) {
        this.countries = countries;
        this.score = 0;
    }
    /** Метод generateQuestion() использует рандомайзер, который получает значение
     * от 0 до countries.size() не включая последнее, и таким образом из списка стран выбирается случайная
     * и на основе неё создается вопрос*/
    public Question generateQuestion() {
        Random random = new Random();
        int index = random.nextInt(countries.size());
        Country country = countries.get(index);
        return new Question(country);
    }

    public boolean checkAnswer(Question question, Answer answer) {
        return question.checkAnswer(answer);
    }

    public void increaseScore() {
        this.score++;
    }

    public int getScore() {
        return score;
    }
}
