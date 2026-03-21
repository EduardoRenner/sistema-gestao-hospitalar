package database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void inicializarBanco(){
        inicializarBancoPacientes();
        inicializarBancoAtendimentos();
    }

    public static void inicializarBancoPacientes(){
        try(Connection conn = DatabaseConnection.connect()){
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS paciente(" +
                    "id INTEGER PRIMARY KEY," +
                    "nome TEXT," +
                    "cpf  TEXT," +
                    "convenio TEXT" +
                    ");";
            stmt.executeUpdate(sql);

            System.out.println("Banco de pacientes inicializado.");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void inicializarBancoAtendimentos(){
        try(Connection conn = DatabaseConnection.connect()) {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS atendimento(" +
                    "id INTEGER PRIMARY KEY," +
                    "descricao TEXT," +
                    "valorBase INTEGER," +
                    "data TEXT," +
                    "paciente_id INTEGER, FOREIGN KEY(paciente_id) REFERENCES paciente(id)" +
                    ");";
            stmt.executeUpdate(sql);

            System.out.println("Banco de atendimentos inicializado.");
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
