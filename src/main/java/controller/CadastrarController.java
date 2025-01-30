package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

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
    private void handleRegisterButton() {
        String nome = nomeField.getText();
        String email = emailField.getText();
        String matricula = matriculaField.getText();

        if (nome.isEmpty() || email.isEmpty() || matricula.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios.");
            return;
        }

        // Lógica para salvar o usuário no banco ou exibir mensagem
        showAlert("Sucesso", "Usuário cadastrado com sucesso!");

        // Após o cadastro, redirecionar para a tela de login
        handleLoginButton();
    }

    @FXML
    private void handleLoginButton() {
        loadLoginScreen();
    }

    private void loadLoginScreen() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/login.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) botaologin.getScene().getWindow(); // Obtém a janela atual a partir do botão
            stage.setScene(new Scene(root));
            stage.setTitle("Login");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar a tela de login.");
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
