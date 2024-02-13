package utils.connectionBDMySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import repository.models.Alumno;
import repository.models.Ciudad;
import repository.models.Direccion;

//import utils.Configuracion;

public class connectionBDMySql {

    //private static String url = Configuracion.obtenerValor("db.url");
    private static String url = "jdbc:mysql://localhost:3306/java";
    private static String username = "administrador";
    private static String password = "pepe123";
    private static Connection connection;

    public static Connection getInstance() throws SQLException {
        System.out.println(url);
        return connection = DriverManager.getConnection(url, username, password);

    }

    public static Direccion buscarDireccionPorNombre(String nombre) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        Direccion direccionEncontrada = null;

        try {
            conexion = getInstance();
            String query = "SELECT * FROM direcciones WHERE nombre = ?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, nombre);
            resultado = statement.executeQuery();

            if (resultado.next()) {
                direccionEncontrada = new Direccion(resultado.getInt("id"), resultado.getString("nombre"), resultado.getString("calle"), resultado.getString("ciudad"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, resultado);
        }

        return direccionEncontrada;
    }

    private static void cerrarRecursos(Connection conexion2, PreparedStatement statement, ResultSet resultado) {
        throw new UnsupportedOperationException("Unimplemented method 'cerrarRecursos'");
    }

    public static Ciudad buscarCiudadPorNombre(String nombre) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        Ciudad ciudadEncontrada = null;

        try {
            conexion = getInstance();
            String query = "SELECT * FROM ciudades WHERE nombre = ?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, nombre);
            resultado = statement.executeQuery();

            if (resultado.next()) {
                ciudadEncontrada = new Ciudad(resultado.getInt("id"), resultado.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, resultado);
        }

        return ciudadEncontrada;
    }

    public static Connection getConnection() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getConnection'");
    }

}
