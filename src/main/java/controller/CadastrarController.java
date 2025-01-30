package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;

public class CadastrarController {

    @FXML
    private TextField nomeField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField matriculaField;

    @FXML
    private Button botaologin;

    @FXML
    private void handleLoginButton() {
        loadLoginScreen();
    }

    @FXML
    private void handleForgotPassword() {
        showAlert("Recuperação de senha", "Por favor, entre em contato com o suporte para recuperar sua senha.");
    }

    private void loadLoginScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage)botaologin.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar a tela de login.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleRegisterButton() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String matricula = matriculaField.getText();

        if (nome.isEmpty() || email.isEmpty() || matricula.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios.");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/poo"; // Substitua pelo URL do seu banco de dados
        String user = "root"; // Substitua pelo seu usuário do banco de dados
        String password = "Redst@ne87"; // Substitua pela sua senha do banco de dados

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // Testa a conexão com o banco de dados
            if (connection == null) {
                throw new SQLException("Conexão com o banco de dados falhou.");
            }

            // Verifica se a matrícula já existe
            String checkQuery = "SELECT COUNT(*) FROM usuarios WHERE matricula = ?";
            try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
                checkStatement.setString(1, matricula);
                ResultSet resultSet = checkStatement.executeQuery();
                if (resultSet.next() && resultSet.getInt(1) > 0) {
                    showAlert("Erro", "Matrícula já cadastrada. Por favor, use uma matrícula diferente.");
                    return;
                }
            }

            // Insere o novo usuário
            String insertQuery = "INSERT INTO usuarios(nome, email, matricula) VALUES (?,?,?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setString(1, nome);
                insertStatement.setString(2, email);
                insertStatement.setString(3, matricula);
                insertStatement.executeUpdate();
            }

            showAlert("Sucesso", "Usuário registrado com sucesso!");

            // Carregar a tela de login
            handleLoginButton();
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível registrar o usuário. Por favor, tente novamente.");
        }
    }
}
