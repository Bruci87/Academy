package visao;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class BotaoController {

    @FXML
    private Button meuBotao;

    @FXML
    private Button botaoCadastrar;

    @FXML
    private Button botaoExcluir;

    @FXML
    private Button botaoEditar;

    @FXML
    private Button botaoLerInformacoes;

    @FXML
    private void handleBotaoClique() {
        System.out.println("Botão foi clicado!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/avaliacao.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) meuBotao.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Avaliação");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBotaoCadastrar() {
        System.out.println("Botão Cadastrar foi clicado!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/cadastrar.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) botaoCadastrar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Cadastrar");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBotaoExcluir() {
        System.out.println("Botão Excluir foi clicado!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/deletar.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) botaoExcluir.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Deletar");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBotaoEditar() {
        System.out.println("Botão Editar foi clicado!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/editar.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) botaoEditar.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Editar");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBotaoLerInformacoes() {
        System.out.println("Botão Ler Informações foi clicado!");
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org/example/academy/lerinformacoes.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) botaoLerInformacoes.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setTitle("Ler Informações");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
