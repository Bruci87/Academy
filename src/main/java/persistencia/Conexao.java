package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static Connection getConexao() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/poo"; // Altere 'localhost', '3306', 'seubanco', 'username' e 'password'
        String user = "root";
        String password = "Redst@ne87";
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        try {
            Connection con = getConexao();
            System.out.println("Conexão bem-sucedida!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
