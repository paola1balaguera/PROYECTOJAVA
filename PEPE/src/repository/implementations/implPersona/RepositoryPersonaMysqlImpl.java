package repository.implementations.implPersona;

import repository.interfaces.ModelCrudGenerico;
import repository.models.Alumno;
import repository.models.Persona;
import utils.connectionBDMySql.connectionBDMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RepositoryPersonaMysqlImpl implements ModelCrudGenerico<Persona> {
    private Connection getConnection() throws SQLException {
        return connectionBDMySql.getInstance();
    }

    @Override
    public List<Persona> listar() {
        List<Persona> listaPersonas = new ArrayList<>();

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT persona.* FROM persona INNER JOIN alumno ON alumno.id_persona = persona.id_persona");
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listaPersonas.add(crearPersona(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaPersonas;
    }

    @Override
    public Persona busqueda(int documento) {
        Persona persona = null;

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM persona WHERE documento=?")) {
            stmt.setInt(1, documento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    persona = crearPersona(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    @Override
    public void crear(Persona persona) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO persona (tipo_documento, documento, nombres, apellidos, id_ciudad, id_direccion, telefono, fecha_nacimiento, sexo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, persona.getTipo_documento().name());
            stmt.setLong(2, persona.getDocumento());
            stmt.setString(3, persona.getNombres());
            stmt.setString(4, persona.getApellidos());
            stmt.setInt(5, persona.getCiudadId());
            stmt.setInt(6, persona.getDireccionId());
            stmt.setString(7, persona.getTelefono());
            stmt.setDate(8, java.sql.Date.valueOf(persona.getFecha_nacimiento()));
            stmt.setString(9, persona.getSexo().name());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Persona persona) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("UPDATE persona SET nombre=?")) {
            stmt.setString(1, persona.getNombres());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(int id) {}

    private Persona crearPersona(ResultSet rs) throws SQLException {
        Persona persona = new Persona();
        persona.setId(rs.getInt("id"));
        persona.setDocumento(rs.getInt("documento"));
        persona.setNombres(rs.getString("nombre"));
        persona.setApellidos(rs.getString("apellido"));
        return persona;

    }
}
