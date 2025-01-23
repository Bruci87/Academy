package visao;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLoginButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Erro", "Usuário e senha são obrigatórios.");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/poo"; // Substitua pelo URL do seu banco de dados
        String user = "root"; // Substitua pelo seu usuário do banco de dados
        String dbPassword = "Redst@ne87"; // Substitua pela sua senha do banco de dados

        String query = "SELECT * FROM usuarios WHERE nome = ? AND senha = ?";

        try (Connection connection = DriverManager.getConnection(url, user, dbPassword);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                showAlert("Sucesso", "Login realizado com sucesso!");
                // Redirecionar para a página principal, se necessário
            } else {
                showAlert("Erro", "Usuário ou senha inválidos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível realizar o login. Por favor, tente novamente.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

