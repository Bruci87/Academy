package controller;

import persistencia.PlanoDAO;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ExcluirPlanoController {

    @FXML
    private TextField matriculaField;

    @FXML
    private TextField idPlanoField;

    @FXML
    private Button confirmarExclusaoButton;

    @FXML
    private Label resultadoLabel;

    private PlanoDAO planoDAO;

    @FXML
    public void initialize() {
        try {
            planoDAO = new PlanoDAO();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }

        confirmarExclusaoButton.setOnAction(event -> handleConfirmarExclusao());
    }

    private void handleConfirmarExclusao() {
        try {
            int matricula = Integer.parseInt(matriculaField.getText());
            int idPlano = Integer.parseInt(idPlanoField.getText());

            if (planoDAO.deletar(idPlano)) {
                resultadoLabel.setText("Plano excluído com sucesso.");
                System.out.println("Plano excluído com sucesso.");
            } else {
                resultadoLabel.setText("Erro ao excluir o plano.");
                System.out.println("Erro ao excluir o plano.");
            }
        } catch (NumberFormatException e) {
            resultadoLabel.setText("Erro: Matrícula ou ID do Plano inválidos.");
            System.out.println("Erro: Matrícula ou ID do Plano inválidos.");
        } catch (Exception e) {
            e.printStackTrace();
            resultadoLabel.setText("Erro ao excluir o plano: " + e.getMessage());
            System.out.println("Erro ao excluir o plano: " + e.getMessage());
        }
    }
}
