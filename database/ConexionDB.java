package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    // Definimos constantes para los parámetros de conexión: URL, usuario y contraseña
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_productos_cuidado_personal"; // Dirección de la base de datos MySQL
    private static final String USER = "root"; // Nombre de usuario para la conexión
    private static final String PASSWORD = "1234"; // Contraseña del usuario

    // Metodo estático que se encargará de realizar la conexión con la base de datos
    public static Connection getConnection() {
        try {
            // Cargar el driver de MySQL. Este es el componente que permite a Java conectar con MySQL.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establecer la conexión con la base de datos:
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // Verificar si la conexión fue exitosa
            if (conn != null) {
                System.out.println("Conexión exitosa a la base de datos");
            } else {
                System.out.println("Error: La conexión es nula.");
            }

            return conn;
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se pudo encontrar el driver JDBC de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: No se pudo conectar a la base de datos.");
            e.printStackTrace();
        }

        // Retorna null si hubo un error en la conexión
        return null;
    }
}
