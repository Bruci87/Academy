package persistencia;

import dominio.Matricula;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAO {
    private Connection conexao;

    public MatriculaDAO() throws SQLException {
        conexao = Conexao.getConexao();
    }

    public void salvar(Matricula matricula) {
        String sql = "INSERT INTO matricula (codigo, matriculaAluno, codigoPlano, dataInicio, dataFim) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, matricula.getCodigo());
            stmt.setInt(2, matricula.getMatriculaAluno());
            stmt.setInt(3, matricula.getCodigoPlano());
            stmt.setDate(4, Date.valueOf(matricula.getDataInicio()));
            stmt.setDate(5, Date.valueOf(matricula.getDataFim()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizar(Matricula matricula) {
        String sql = "UPDATE matricula SET matriculaAluno = ?, codigoPlano = ?, dataInicio = ?, dataFim = ? WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, matricula.getMatriculaAluno());
            stmt.setInt(2, matricula.getCodigoPlano());
            stmt.setDate(3, Date.valueOf(matricula.getDataInicio()));
            stmt.setDate(4, Date.valueOf(matricula.getDataFim()));
            stmt.setInt(5, matricula.getCodigo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletar(int codigo) {
        String sql = "DELETE FROM matricula WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Matricula buscarPorCodigo(int codigo) {
        String sql = "SELECT * FROM matricula WHERE codigo = ?";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Matricula(
                        rs.getInt("codigo"),
                        rs.getInt("matriculaAluno"),
                        rs.getInt("codigoPlano"),
                        rs.getDate("dataInicio").toLocalDate(),
                        rs.getDate("dataFim").toLocalDate()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Matricula> buscarTodos() {
        List<Matricula> matriculas = new ArrayList<>();
        String sql = "SELECT * FROM matricula";
        try (Statement stmt = conexao.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Matricula matricula = new Matricula(
                        rs.getInt("codigo"),
                        rs.getInt("matriculaAluno"),
                        rs.getInt("codigoPlano"),
                        rs.getDate("dataInicio").toLocalDate(),
                        rs.getDate("dataFim").toLocalDate()
                );
                matriculas.add(matricula);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matriculas;
    }
}
