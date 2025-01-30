package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class DashBoardController {

    @FXML
    private Button meuPlanoButton;

    @FXML
    private Button editarPlanoButton;

    @FXML
    private Button excluirPlanoButton;

    @FXML
    public void initialize() {
        meuPlanoButton.setOnAction(event -> handleMeuPlano());
        editarPlanoButton.setOnAction(event -> handleEditarPlano());
        excluirPlanoButton.setOnAction(event -> handleExcluirPlano());
    }

    @FXML
    private void handleMeuPlano() {
        carregarTela("/org/example/academy/cadastrarplano.fxml");
    }

    @FXML
    private void handleEditarPlano() {
        carregarTela("/org/example/academy/dashboard.fxml");
    }

    @FXML
    private void handleExcluirPlano() {
        carregarTela("/org/example/academy/excluirPlano.fxml"); // Caminho atualizado para a tela de exclusão
    }

    private void carregarTela(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            AnchorPane root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = (Stage) meuPlanoButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            System.out.println("Tela " + fxmlFile + " carregada com sucesso.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao carregar a tela " + fxmlFile + ": " + e.getMessage());
        }
    }
}
