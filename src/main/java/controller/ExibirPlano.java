package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExibirPlano {

    @FXML
    private TableView<Plano> planosTableView;

    @FXML
    private TableColumn<Plano, String> matriculaColumn;

    @FXML
    private TableColumn<Plano, String> nomeColumn;

    @FXML
    private TableColumn<Plano, String> emailColumn;

    @FXML
    private TableColumn<Plano, String> planoColumn;

    @FXML
    private Button voltarButton;

    private ObservableList<Plano> planosList;
    private String matricula;

    @FXML
    public void initialize() {
        matriculaColumn.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        nomeColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        planoColumn.setCellValueFactory(new PropertyValueFactory<>("plano"));

        planosList = FXCollections.observableArrayList();
        planosTableView.setItems(planosList);

        voltarButton.setOnAction(event -> handleVoltar());
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
        carregarPlanos();
    }

    private void carregarPlanos() {
        planosList.clear();
        String url = "jdbc:mysql://localhost:3306/poo";
        String user = "root";
        String password = "Redst@ne87";

        String query = "SELECT u.matricula, u.nome, u.email, p.Nome_Plano AS plano FROM usuario_planos up " +
                "JOIN usuarios u ON up.usuario_id = u.id " +
                "JOIN plano p ON up.plano_id = p.ID_Plano " +
                "WHERE u.matricula = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, this.matricula);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String matricula = resultSet.getString("matricula");
                String nome = resultSet.getString("nome");
                String email = resultSet.getString("email");
                String plano = resultSet.getString("plano");

                System.out.println("Matrícula: " + matricula + ", Nome: " + nome + ", E-mail: " + email + ", Plano: " + plano); // Mensagem de depuração

                Plano planoObj = new Plano(matricula, nome, email, plano);
                planosList.add(planoObj);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleVoltar() {
        Stage stage = (Stage) voltarButton.getScene().getWindow();
        stage.close(); // Fecha a tela atual
    }

    public static class Plano {
        private final String matricula;
        private final String nome;
        private final String email;
        private final String plano;

        public Plano(String matricula, String nome, String email, String plano) {
            this.matricula = matricula;
            this.nome = nome;
            this.email = email;
            this.plano = plano;
        }

        public String getMatricula() {
            return matricula;
        }

        public String getNome() {
            return nome;
        }

        public String getEmail() {
            return email;
        }

        public String getPlano() {
            return plano;
        }
    }
}
