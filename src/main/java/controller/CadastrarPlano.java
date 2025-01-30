package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CadastrarPlano {

    @FXML
    private TextField matriculaField;

    @FXML
    private TextField planoField;

    @FXML
    private Button adicionarButton;

    @FXML
    private Button verPlanoButton;

    @FXML
    public void initialize() {
        adicionarButton.setOnAction(event -> adicionarPlano());
        verPlanoButton.setOnAction(event -> verPlano());
    }

    private void adicionarPlano() {
        String matricula = matriculaField.getText().trim();
        String plano = planoField.getText().trim();

        if (matricula.isEmpty() || plano.isEmpty()) {
            showAlert("Erro", "Todos os campos são obrigatórios.");
            return;
        }

        int planoId;
        try {
            planoId = Integer.parseInt(plano);
            if (planoId < 1 || planoId > 5) {
                showAlert("Erro", "ID do plano inválido. Deve ser um número entre 1 e 5.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert("Erro", "ID do plano inválido. Deve ser um número.");
            return;
        }

        String url = "jdbc:mysql://localhost:3306/poo";
        String user = "root";
        String password = "Redst@ne87";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {

            // Verifique se a matrícula existe e obtenha o ID do usuário
            String checkUserQuery = "SELECT id FROM usuarios WHERE matricula = ?";
            try (PreparedStatement checkUserStmt = connection.prepareStatement(checkUserQuery)) {
                checkUserStmt.setString(1, matricula);
                ResultSet userResultSet = checkUserStmt.executeQuery();

                if (!userResultSet.next()) {
                    showAlert("Erro", "Matrícula não encontrada.");
                    return;
                }

                int userId = userResultSet.getInt("id");

                // Associa o plano ao ID do usuário
                String query = "INSERT INTO usuario_planos(usuario_id, plano_id) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setInt(1, userId);
                    preparedStatement.setInt(2, planoId);
                    preparedStatement.executeUpdate();

                    showAlert("Sucesso", "Plano associado à matrícula com sucesso!");

                    // Limpa os campos de entrada
                    matriculaField.clear();
                    planoField.clear();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível associar o plano à matrícula. Por favor, tente novamente.");
        }
    }

    private void verPlano() {
        String matricula = matriculaField.getText().trim();
        if (matricula.isEmpty()) {
            showAlert("Erro", "Digite a matrícula para ver os planos.");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/example/academy/exibirPlanos.fxml"));
            AnchorPane root = loader.load();

            // Passa a matrícula para o controlador ExibirPlano
            ExibirPlano controller = loader.getController();
            controller.setMatricula(matricula);

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Erro", "Não foi possível carregar a tela de exibição de planos.");
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
