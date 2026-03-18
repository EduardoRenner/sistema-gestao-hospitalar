package app;

import database.DatabaseConnection;
import database.DatabaseInitializer;
import service.HospitalService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main{
    static void main(String[] args) {

        DatabaseInitializer.inicializarBanco();

        HospitalService servicoHospitalar = new HospitalService();

        servicoHospitalar.menu();

    }
}
