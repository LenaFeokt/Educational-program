package com.example.countires.controllers;

import com.example.countires.HelloApplication;
import com.example.countires.domain.Result;
import com.example.countires.h2database.CountryDao;
import com.example.countires.h2database.H2DatabaseConnection;
import com.example.countires.h2database.JdbcCountryDao;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Класс реализующий окно с результатом тестирования
 * answer
 * @autor Феоктистова
 * @version 1.1
 */
public class ResultWindowController {

    @FXML
    public TableView<Result> resultsTable;
    @FXML
    public TableColumn<Result, String> nameColumn;
    @FXML
    public TableColumn<Result, String> resultColumn;
    @FXML
    public Button restartButton;
    public Button deleteButton;

    private Connection connection;

    private CountryDao countryDao;

    private final List<Result> results;

    public ResultWindowController() throws SQLException {
        connection = H2DatabaseConnection.getConnection();
        countryDao = new JdbcCountryDao(connection);
        results = countryDao.getResults();
    }
    /** Метод для вывода имени тестируемого и его результата */
    @FXML
    private void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        resultColumn.setCellValueFactory(new PropertyValueFactory<>("result"));

        resultsTable.setItems(FXCollections.observableArrayList(results));
    }
    /** Метод для повторного прохождения тестирования */
    public void restartTest(ActionEvent actionEvent) throws IOException {
        Stage currStage = (Stage) restartButton.getScene().getWindow();
        currStage.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("test-window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Тестирование");
        stage.setScene(scene);
        stage.show();
    }
    /** Метод для удаления результата из таблицы результатов тестируемых */
    public void deleteResult(ActionEvent actionEvent) {
        Result selectedResult = resultsTable.getSelectionModel().getSelectedItem();
        if (selectedResult != null) {
            countryDao.deleteResult(selectedResult.getId());
            results.remove(selectedResult);
        }
        initialize();
    }
}
