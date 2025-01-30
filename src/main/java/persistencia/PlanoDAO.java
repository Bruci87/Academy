package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlanoDAO {

    private Connection conexao;

    public PlanoDAO() throws SQLException {
        // Configuração da conexão com o banco de dados
        String url = "jdbc:mysql://localhost:3306/poo";
        String user = "root";
        String password = "Redst@ne87";
        this.conexao = DriverManager.getConnection(url, user, password);
    }

    public boolean deletar(int idPlano) {
        String deleteUsuarioPlanos = "DELETE FROM usuario_planos WHERE plano_id = ?";
        String deletePlano = "DELETE FROM plano WHERE ID_plano = ?";

        try {
            conexao.setAutoCommit(false);

            // Excluir registros dependentes primeiro
            try (PreparedStatement stmt1 = conexao.prepareStatement(deleteUsuarioPlanos)) {
                stmt1.setInt(1, idPlano);
                stmt1.executeUpdate();
            }

            // Excluir o plano
            try (PreparedStatement stmt2 = conexao.prepareStatement(deletePlano)) {
                stmt2.setInt(1, idPlano);
                int rowsAffected = stmt2.executeUpdate();
                conexao.commit();
                return rowsAffected > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                conexao.rollback();
            } catch (SQLException rollbackException) {
                rollbackException.printStackTrace();
            }
            return false;
        } finally {
            try {
                conexao.setAutoCommit(true);
            } catch (SQLException autoCommitException) {
                autoCommitException.printStackTrace();
            }
        }
    }
}
