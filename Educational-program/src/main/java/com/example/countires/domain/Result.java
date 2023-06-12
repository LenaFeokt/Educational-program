package com.example.countires.domain;

/**
 * Класс реализующий результат ответа на вопрос
 * answer
 * @autor Феоктистова
 * @version 1.1
 */

public class Result {
    private int id;
    private String name;
    private String result;

    public Result(int id, String name, String result) {
        this.id = id;
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public String getResult() {
        return result;
    }

    public int getId() {
        return id;
    }
}
