package com.example.countires.controllers;

import com.example.countires.HelloApplication;
import com.example.countires.domain.Answer;
import com.example.countires.domain.Country;
import com.example.countires.domain.Question;
import com.example.countires.domain.Quiz;
import com.example.countires.h2database.CountryDao;
import com.example.countires.h2database.H2DatabaseConnection;
import com.example.countires.h2database.JdbcCountryDao;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Класс реализующий окно с тестом
 * answer
 * @autor Феоктистова
 * @version 1.1
 */

public class TestWindowController {
    @FXML
    public Label checkedLabel;
    @FXML
    private Label questionLabel;
    @FXML
    private TextField answerTextField;
    @FXML
    private Button nextButton;
    @FXML
    private Button checkButton;

    private final Quiz quiz;
    private final List<Question> questions;
    private int currentQuestionIndex;

    private Connection connection;

    private CountryDao countryDao;

    public TestWindowController() throws SQLException {
        connection = H2DatabaseConnection.getConnection();
        countryDao = new JdbcCountryDao(connection);

        List<Country> countries = countryDao.getAllCountries();

        quiz = new Quiz(countries);

        questions = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            questions.add(quiz.generateQuestion());
        }
        currentQuestionIndex = 0;
    }

    @FXML
    private void initialize() {
        showCurrentQuestion();
    }

    /** Метод onNextButtonClicked() содержит кнопки для прохождения теста заново, завершения теста,
     * сохранение результата, просмотра таблицы результатов. Также содержит результат тестирования.*/

    @FXML
    private void onNextButtonClicked() throws IOException {
        currentQuestionIndex++;
        if (currentQuestionIndex >= questions.size()) {
            ButtonType restartTest = new ButtonType("Пройти заново", ButtonBar.ButtonData.OK_DONE);
            ButtonType exitTest = new ButtonType("Завершить", ButtonBar.ButtonData.CANCEL_CLOSE);
            ButtonType saveResult = new ButtonType("Сохранить результат", ButtonBar.ButtonData.OTHER);
            ButtonType showResults = new ButtonType("Показать таблицу результатов", ButtonBar.ButtonData.OTHER);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Result", restartTest, exitTest, saveResult, showResults);
            alert.setTitle("Результат тестирования");
            alert.setHeaderText("Ваш результат тестирования:\t" + quiz.getScore() + "/10");
            alert.setContentText("Вы можете перепройти тест, сохранить результат, посмотреть все результаты и завершить работу.");
            Optional<ButtonType> result = alert.showAndWait();
            ButtonType buttonType = result.orElse(ButtonType.CANCEL);
            if (buttonType == restartTest) {
                Stage currStage = (Stage) nextButton.getScene().getWindow();
                currStage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("test-window.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Тестирование");
                stage.setScene(scene);
                stage.show();
            }
            if (buttonType == exitTest) {
                alert.close();
                Stage currStage = (Stage) nextButton.getScene().getWindow();
                currStage.close();
            }
            if (buttonType == saveResult) {
                TextInputDialog textInputDialog = new TextInputDialog();
                textInputDialog.setTitle("Сохранить результат");
                textInputDialog.setHeaderText("Введите ваше имя:");
                textInputDialog.showAndWait();
                String name = textInputDialog.getEditor().getText();
                countryDao.saveResult(name, quiz.getScore() + "/10");
            }
            if (buttonType == showResults) {
                Stage currStage = (Stage) nextButton.getScene().getWindow();
                currStage.close();

                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("results-window.fxml"));
                Scene scene = new Scene(fxmlLoader.load());
                Stage stage = new Stage();
                stage.setTitle("Результаты");
                stage.setScene(scene);
                stage.show();
            }
        } else {
            showCurrentQuestion();
        }
    }
    /** Метод onCheckButtonClicked() используется для проверки ответа на вопрос ("Верно" или "Неверно") */
    @FXML
    private void onCheckButtonClicked() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        Answer answer = new Answer(answerTextField.getText());
        boolean isCorrect = quiz.checkAnswer(currentQuestion, answer);

        if (isCorrect) {
            checkedLabel.setText("Верно");
            checkedLabel.setStyle("-fx-text-fill: green; -fx-font-size: 16px;");
            quiz.increaseScore();
        } else {
            checkedLabel.setText("Неверно");
            checkedLabel.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        }
        checkButton.setDisable(true);
        nextButton.setDisable(false);
    }
    /** Метод showCurrentQuestion() используется для показа текущего вопроса.
     * Поле currentQuestionIndex используется для того, чтобы отследить на каком вопросе находится тест,
     * сохраняет индекс вопроса из списка*/
    private void showCurrentQuestion() {
        Question currentQuestion = questions.get(currentQuestionIndex);
        questionLabel.setText(currentQuestion.getQuestionText());
        answerTextField.setText("");
        checkedLabel.setText("");
        checkButton.setDisable(false);
        nextButton.setDisable(true);
    }


}
