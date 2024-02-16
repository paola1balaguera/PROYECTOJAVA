package repository.implementations.implAlumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import repository.models.*;
import repository.interfaces.*;

import utils.connectionBDMySql.connectionBDMySql;
public class RepositoryAlumnoMysqlImpl implements ModelCrudGenerico<Alumno> {

    private Connection getConnection() throws SQLException {
        return connectionBDMySql.getInstance();
    }

    @Override
    public List<Alumno> listar() {
        List<Alumno> listaAlumnos = new ArrayList<>();

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM persona INNER JOIN alumno ON alumno.id_persona = persona.id_persona INNER JOIN direccion ON direccion.id_direccion = persona.id_direccion");
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                listaAlumnos.add(crearAlumno(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaAlumnos;
    }

    @Override
    public Alumno busqueda(int documento) {
        Alumno alumno = null;

        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("SELECT * FROM alumno WHERE documento=?")) {
            stmt.setInt(1, documento);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    alumno = crearAlumno(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }

    @Override
    public void crear(Alumno alumno) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO alumno (id_alumno, id_persona, id_programa) VALUES (?, ?, ?)")) {
            stmt.setInt(1, alumno.getId());
            stmt.setInt(2, alumno.getPersona().getId());
            stmt.setInt(3, alumno.getPrograma().getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editar(Alumno alumno) {
        try (Connection conn = getConnection();
                PreparedStatement stmt = conn.prepareStatement("UPDATE alumno SET id_persona=?, id_programa=? WHERE id_alumno=?")) {
            stmt.setInt(1, alumno.getPersona().getId());
            stmt.setInt(2, alumno.getPrograma().getId());
            stmt.setInt(3, alumno.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // @Override
    // public void eliminar(Alumno alumno) {
    //     try (Connection conn = getConnection();
    //             PreparedStatement stmt = conn.prepareStatement("DELETE FROM alumno WHERE id_alumno=?")) {
    //         stmt.setInt(1, alumno.getId());
    //         stmt.executeUpdate();
    //     } catch (SQLException e) {
    //         e.printStackTrace();
    //     }
    // }

    @Override
    public void eliminar(int id) {

    }

    private Alumno crearAlumno(ResultSet rs) throws SQLException {
        Alumno alumno = new Alumno();
        alumno.setId(rs.getInt("id_alumno"));

        Direccion direccion = new Direccion(rs.getInt("id_direccion"), rs.getString("barrio"), rs.getString("calle"), rs.getString("numero_casa"));
        Persona persona = new Persona(rs.getInt("id_persona"), rs.getString("nombre"), rs.getString("apellido"), rs.getInt("id_ciudad"), rs.getInt("id_direccion"), rs.getString("telefono"), rs.getDate("fecha_nacimiento"));
        persona.setId(rs.getInt("id_persona"));
        persona.setNombres(rs.getString("nombres"));
        persona.setApellidos(rs.getString("apellidos"));
        persona.setDireccion(direccion);
        persona.setDocumento(rs.getLong("documento"));
        persona.setSexo(Persona.enumSexo.valueOf(rs.getString("sexo")));
        persona.setFechaNacimiento(rs.getDate("fecha_nacimiento"));
        persona.setTipoDocumento(Persona.TipoDocumento.valueOf(rs.getString("tipo_documento")));
        alumno.setPersona(persona);

        Programa programa = new Programa();
        programa.setId(rs.getInt("id_programa")); // Suponiendo que id_programa es el campo en la tabla alumno que referencia a la tabla programa
        alumno.setPrograma(programa);

        return alumno;
    }
}
