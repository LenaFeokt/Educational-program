package com.example.countires.domain;
/**
 * Класс реализующий получение ответов на вопросы
 * answer
 * @autor Феоктистова
 * @version 1.1
 */
public class Answer {
    private String answer;
/** Контроллер класса */
    public Answer(String answer) {
        this.answer = answer;
    }
    /** Метод возвращает ответ */
    public String getAnswer() {
        return answer;
    }

    public boolean isCorrect(Question question) {
        return question.checkAnswer(this);
    }
}