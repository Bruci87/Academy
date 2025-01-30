package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private void handleLoginButton() {
        String email = emailField.getText();
        String senha = passwordField.getText();

        if (email.isEmpty() || senha.isEmpty()) {
            showAlert("Erro", "Por favor, preencha todos os campos.");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/poo"; // Substitua pelo URL do seu banco
        String user = "root"; // Substitua pelo seu usuário do banco
        String password = "Redst@ne87"; // Substitua pela sua senha do banco

        String query = "SELECT * FROM usuarios WHERE email = ? AND matricula = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, senha);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Login bem-sucedido
                showAlert("Sucesso", "Login realizado com sucesso!");
                loadDashboardScreen();
            } else {
                // Credenciais inválidas
                showAlert("Erro", "E-mail ou senha inválidos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível conectar ao banco de dados.");
        }
    }

    private void loadDashboardScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/dashboard.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar a tela do dashboard.");
        }
    }
    @FXML
    private void handleCadastroHyperlink2() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/cadastrar.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) emailField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Cadastrar Usuário");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar a tela de cadastro.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
