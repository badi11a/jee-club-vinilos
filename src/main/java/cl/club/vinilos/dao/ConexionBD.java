package cl.club.vinilos.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    
    private static final String URL = "jdbc:mariadb://localhost:3306/club_vinilos";
    private static final String USER = ""; 
    private static final String PASSWORD = ""; 
    
 
    public static Connection obtenerConexion() {
        Connection conexion = null;
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MariaDB. ¿Está en el pom.xml?");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos. Verifique credenciales o si el servicio está iniciado.");
            e.printStackTrace();
        }
        return conexion;
    }
}