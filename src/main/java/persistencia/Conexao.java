package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/poo";
    private static final String USUARIO = "root"; // usuário encontrado
    private static final String SENHA = "Redst@ne87"; // insira a senha correta
    private static Connection conexao = null;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                // Registrar o driver MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");
                conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return conexao;
    }

    public static void fecharConexao() {
        if (conexao != null) {
            try {
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connection con = getConexao();
        if (con != null) {
            System.out.println("Conexão estabelecida com sucesso!");
            fecharConexao();
        } else {
            System.out.println("Falha ao estabelecer a conexão.");
        }
    }
}
