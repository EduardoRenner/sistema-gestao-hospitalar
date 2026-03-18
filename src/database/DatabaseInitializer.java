package database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    public static void inicializarBanco(){
        try(Connection conn = DatabaseConnection.connect()){


            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS paciente(" +
                    "id INTEGER PRIMARY KEY," +
                    "nome TEXT," +
                    "cpf  TEXT," +
                    "convenio TEXT" +
                    ");";
            stmt.executeUpdate(sql);

            System.out.println("Banco inicializado.");

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
