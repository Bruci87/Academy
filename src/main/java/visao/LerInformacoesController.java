package visao;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LerInformacoesController {

    @FXML
    private TextField emailField;

    @FXML
    private TextField matriculaField;

    @FXML
    private Label nomeLabel;

    @FXML
    private Label emailLabel;

    @FXML
    private Label matriculaLabel;

    @FXML
    private Button lerInformacoesButton;

    @FXML
    private void handleLerInformacoesButton() {
        String email = emailField.getText();
        String matricula = matriculaField.getText();

        if (email.isEmpty() || matricula.isEmpty()) {
            showAlert("Erro", "E-mail e matrícula são obrigatórios.");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/poo"; // Substitua pelo URL do seu banco de dados
        String user = "root"; // Substitua pelo seu usuário do banco de dados
        String password = "Redst@ne87"; // Substitua pela sua senha do banco de dados

        String query = "SELECT nome, email, matricula FROM usuarios WHERE email = ? AND matricula = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, matricula);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String nome = resultSet.getString("nome");
                String emailResultado = resultSet.getString("email");
                String matriculaResultado = resultSet.getString("matricula");

                nomeLabel.setText("Nome: " + nome);
                emailLabel.setText("E-mail: " + emailResultado);
                matriculaLabel.setText("Matrícula: " + matriculaResultado);
            } else {
                showAlert("Erro", "E-mail ou matrícula inválidos.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível recuperar as informações. Por favor, tente novamente.");
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
