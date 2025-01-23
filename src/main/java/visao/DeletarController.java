package visao;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletarController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField matriculaField;

    @FXML
    private Button deleteButton;

    @FXML
    private void handleDeleteButton() {
        String email = emailField.getText();
        String matricula = matriculaField.getText();

        if (email.isEmpty() && matricula.isEmpty()) {
            showAlert("Erro", "E-mail ou matrícula são obrigatórios.");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/poo"; // Substitua pelo URL do seu banco de dados
        String user = "root"; // Substitua pelo seu usuário do banco de dados
        String password = "Redst@ne87"; // Substitua pela sua senha do banco de dados

        String query = "DELETE FROM usuarios WHERE email = ? OR matricula = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, matricula);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                showAlert("Sucesso", "Usuário deletado com sucesso!");
            } else {
                showAlert("Erro", "Nenhum usuário encontrado com os dados fornecidos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível deletar o usuário. Por favor, tente novamente.");
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
