package dao;

import database.DatabaseConnection;
import model.Atendimento;
import model.Paciente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AtendimentoDAO {
    public void inserirAtendimento(Atendimento atendimento) {
        String sql = "INSERT INTO atendimento(descricao, valorBase, data, paciente_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, atendimento.getDescricao());
            pstmt.setDouble(2, atendimento.getValorBase());
            pstmt.setString(3, atendimento.getData());
            pstmt.setInt(4, atendimento.getPacienteId());
            pstmt.executeUpdate();

            System.out.println("Atendimento adicionado ao banco.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarAtendimentos() {
        boolean founded = false;

        String sql = "SELECT * FROM atendimento";
        try (Connection conn = DatabaseConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                founded = true;
                int id = resultSet.getInt("id");
                String descricao = resultSet.getString("descricao");
                double valorBase = resultSet.getDouble("valorBase");
                String data = resultSet.getString("data");
                int pacienteId = resultSet.getInt("paciente_id");

                System.out.println("ID: " + id + " | Descrição: " + descricao + " | Valor Base: " + valorBase + " | Data: " + data + " | ID do paciente: " + pacienteId);
            }
            if (!founded) {
                System.out.println("Nenhum atendimento encontrado");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void listarAtendimentosPaciente(int idPaciente) {
        boolean founded = false;

        String sql = "SELECT * FROM atendimento WHERE paciente_id = ?";

        try (Connection conn = DatabaseConnection.connect()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPaciente);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                founded = true;
                int id = resultSet.getInt("id");
                String descricao = resultSet.getString("descricao");
                double valorBase = resultSet.getDouble("valorBase");
                String data = resultSet.getString("data");
                int pacienteId = resultSet.getInt("paciente_id");

                System.out.println("ID: " + id + " | Descrição: " + descricao + " | Valor Base: " + valorBase + " | Data: " + data + " | ID do paciente: " + pacienteId);
            }
            if (!founded) {
                System.out.println("Nenhum atendimento encontrado");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void atualizarAtendimento(int id, String novaDescricao) {
        String sql = "UPDATE atendimento SET descricao = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, novaDescricao);
            pstmt.setInt(2, id);

            int linhas = pstmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Atendimento atualizado com sucesso.");
            } else {
                System.out.println("Atendimento não encontrado.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deletarAtendimento(int id) {
        String sql = "DELETE FROM atendimento WHERE id = ?";

        try (Connection conn = DatabaseConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            int linhas = pstmt.executeUpdate();

            if (linhas > 0) {
                System.out.println("Atendimento deletado.");
            } else {
                System.out.println("Atendimento não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public double valorTotal(int idPaciente) {
        double total = 0;
        String sql = "SELECT valorBase FROM atendimento WHERE paciente_id = ?";

        try (Connection conn = DatabaseConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, idPaciente);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                total += resultSet.getDouble("valorBase");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return total;
    }
}
