package dao;

import database.DatabaseConnection;
import model.Paciente;
import model.PacienteConvenio;

import java.sql.*;

public class PacienteDAO {
    public void inserirPaciente(Paciente paciente){
        String sql = "INSERT INTO paciente(nome, cpf, convenio) VALUES(?, ?, ?)";

        try(Connection conn = DatabaseConnection.connect()) {

            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, paciente.getNome());
            pstmt.setString(2, paciente.getCpf());
            if (paciente instanceof PacienteConvenio){
                pstmt.setString(3, ((PacienteConvenio) paciente).getConvenio().name());
            }else {
                pstmt.setString(3, "PARTICULAR");
            }

            pstmt.executeUpdate();

            System.out.println("Paciente salvo no banco.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void listarPacientes(){
        boolean founded = false;

        String sql = "SELECT * FROM paciente";
        try(Connection conn = DatabaseConnection.connect()){
            PreparedStatement pstmt = conn.prepareStatement(sql);
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()){
                founded = true;
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String convenio = resultSet.getString("convenio");

                System.out.println("Nome: "+nome+" | CPF: "+cpf+" | convenio: "+convenio);
            }

            if (!founded){
                System.out.println("Nenhum paciente encontrado.");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void deletePaciente(String cpf){
        String sql = "DELETE FROM paciente WHERE cpf = ?";

        try(Connection conn = DatabaseConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cpf);

            int linhas = pstmt.executeUpdate();

            if (linhas > 0){
                System.out.println("Paciente deletado do banco de dados.");
            }else {
                System.out.println("Paciente não encontrado no banco de dados.");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void atualizarPaciente(String cpf, String novoNome){
        String sql = "UPDATE paciente SET nome = ? WHERE cpf = ?";

        try(Connection conn = DatabaseConnection.connect()) {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, novoNome);
            pstmt.setString(2, cpf);

            int linhas = pstmt.executeUpdate();

            if (linhas > 0){
                System.out.println("Nome atualizado com sucesso.");
            }else {
                System.out.println("Paciente não encontrado.");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
