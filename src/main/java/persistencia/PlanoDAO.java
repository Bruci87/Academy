package persistencia;

import dominio.Plano;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {
    private Connection conexao;

    public PlanoDAO() {
        conexao = Conexao.getConexao();
    }

    public void salvar(Plano plano) {
        String sql = "INSERT INTO plano (codigo, nome, descricao, valor, duracaoMeses) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, plano.getCodigo());
            stmt.setString(2, plano.getNome());
            stmt.setString(3, plano.getDescricao());
            stmt.setDouble(4, plano.getValor());
            stmt.setInt(5, plano.getDuracaoMeses());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Plano plano) {
        String sql = "UPDATE plano SET nome = ?, descricao = ?, valor = ?, duracaoMeses = ? WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, plano.getNome());
            stmt.setString(2, plano.getDescricao());
            stmt.setDouble(3, plano.getValor());
            stmt.setInt(4, plano.getDuracaoMeses());
            stmt.setInt(5, plano.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int codigo) {
        String sql = "DELETE FROM plano WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Plano buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM plano WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Plano(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getInt("duracaoMeses")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Plano> buscarTodos() {
        List<Plano> planos = new ArrayList<>();
        String sql = "SELECT * FROM plano";
        try (Statement stmt = conexao.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Plano plano = new Plano(
                        rs.getInt("codigo"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getDouble("valor"),
                        rs.getInt("duracaoMeses")
                );
                planos.add(plano);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return planos;
    }
}
