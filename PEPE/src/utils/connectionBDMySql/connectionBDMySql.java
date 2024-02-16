package utils.connectionBDMySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import repository.models.Alumno;
import repository.models.Asignatura;
import repository.models.AsignaturaPeriodo;
import repository.models.BloqueHorario;
import repository.models.Ciudad;
import repository.models.Curso;
import repository.models.Direccion;
import repository.models.Edificio;
import repository.models.Facultad;
import repository.models.Horario;
import repository.models.Matricula;
import repository.models.Periodo;
import repository.models.Persona;
import repository.models.Profesor;
import repository.models.Programa;
import repository.models.Salon;
import repository.models.Tarifa;

//import utils.Configuracion;

public class connectionBDMySql {

    //private static String url = Configuracion.obtenerValor("db.url");
    private static String url = "jdbc:mysql://localhost:3306/java";
    private static String username = "administrador";
    private static String password = "pepe123";
    private static Connection connection;



    public static Connection getInstance() throws SQLException {
        System.out.println(url);
        return  DriverManager.getConnection(url, username, password);

    }

    //OBTENCION DE LISTAS EN LA BASE DE DATOS
    // --------------------------------------------------------//


    // PROGRAMASSSSSSS

    public static List<Programa> obtenerProgramas() {
    List<Programa> listaProgramas = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM programa";
        statement = conexion.prepareStatement(query);
        resultado = statement.executeQuery();

        while (resultado.next()) {
            Programa programa = new Programa(resultado.getInt("id_programa"), resultado.getString("nombre"), false);
            listaProgramas.add(programa);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return listaProgramas;
}

// TARIFA

    public static List<Tarifa> obtenerTarifas() {
        List<Tarifa> listaTarifas = new ArrayList<>();
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;

        try {
            conexion = getInstance();
            String query = "SELECT * FROM tarifa";
            statement = conexion.prepareStatement(query);
            resultado = statement.executeQuery();

            while (resultado.next()) {
                Tarifa tarifa = new Tarifa(resultado.getInt("id_tarifa"), resultado.getString("nombre"), resultado.getDouble("monto"));
                listaTarifas.add(tarifa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, resultado);
        }

        return listaTarifas;
    }

    // SALON

    public static List<Salon> obtenerSalones() {
    List<Salon> listaSalones = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM salon";
        statement = conexion.prepareStatement(query);
        resultado = statement.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("id_salon");
            int capacidadAlumnos = resultado.getInt("capacidad_alumnos");
            int piso = resultado.getInt("piso");
            String identificador = resultado.getString("identificador");
            int idEdificio = resultado.getInt("id_edificio");

            Edificio edificio = obtenerEdificioPorId(idEdificio);

            Salon salon = new Salon(id, capacidadAlumnos, piso, identificador, edificio);
            listaSalones.add(salon);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return listaSalones;
}


    //PROFESOR

    public static List<Profesor> obtenerProfesores() {
    List<Profesor> listaProfesores = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM profesor";
        statement = conexion.prepareStatement(query);
        resultado = statement.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("id_profesor");
            int idPersona = resultado.getInt("id_persona");
            int idDepartamento = resultado.getInt("id_departamento");

            Persona persona = obtenerPersonaPorId(idPersona);
            Facultad departamento = obtenerFacultadPorId(idDepartamento);

            Profesor profesor = new Profesor(id, persona, departamento);
            listaProfesores.add(profesor);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return listaProfesores;
}


//MATRICULA

    public static List<Matricula> obtenerMatriculas() {
    List<Matricula> listaMatriculas = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM matricula";
        statement = conexion.prepareStatement(query);
        resultado = statement.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("id_matricula");
            int idAlumno = resultado.getInt("id_alumno");
            int idAsignaturaPeriodo = resultado.getInt("id_asignaturaPeriodo");

            Alumno alumno = obtenerAlumnoPorId(idAlumno);
            AsignaturaPeriodo asignaturaPeriodo = obtenerAsignaturaPeriodoPorId(idAsignaturaPeriodo);

            Matricula matricula = new Matricula(id, alumno, asignaturaPeriodo, null);
            listaMatriculas.add(matricula);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return listaMatriculas;
}


    //HORARIO

    public static List<Horario> obtenerHorarios() {
    List<Horario> listaHorarios = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM horario";
        statement = conexion.prepareStatement(query);
        resultado = statement.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("id_horario");
            Date dia = resultado.getDate("dia");
            int idSalon = resultado.getInt("id_salon");
            int idBloqueHorario = resultado.getInt("id_bloque_horario");
            int idAsignaturaPeriodo = resultado.getInt("id_asignatura_periodo");

            Salon salon = obtenerSalonPorId(idSalon);
            BloqueHorario bloqueHorario = obtenerBloqueHorarioPorId(idBloqueHorario);
            AsignaturaPeriodo asignaturaPeriodo = obtenerAsignaturaPeriodoPorId(idAsignaturaPeriodo);

            Horario horario = new Horario(id, dia, salon, bloqueHorario, asignaturaPeriodo);
            listaHorarios.add(horario);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return listaHorarios;
}

    //curso

    public static List<Curso> obtenerCursos() {
    List<Curso> listaCursos = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM curso";
        statement = conexion.prepareStatement(query);
        resultado = statement.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("id_curso");
            String nombre = resultado.getString("nombre");
            String temas = resultado.getString("temas");
            String competencias = resultado.getString("competencias");

            Curso curso = new Curso(id, nombre, temas, competencias);
            listaCursos.add(curso);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return listaCursos;
}

// Alumno
public static List<Alumno> obtenerAlumnos() {
    List<Alumno> listaAlumnos = new ArrayList<>();
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM alumno";
        statement = conexion.prepareStatement(query);
        resultado = statement.executeQuery();

        while (resultado.next()) {
            int id = resultado.getInt("id_alumno");
            int idPersona = resultado.getInt("id_persona");
            int idPrograma = resultado.getInt("id_programa");

            Persona persona = obtenerPersonaPorId(idPersona);
            Persona programa = obtenerPersonaPorId(idPrograma);

            Alumno alumno = new Alumno(idPrograma, query, query);
            listaAlumnos.add(alumno);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return listaAlumnos;
}










 

    






//GUARDADO DE NUEVOS REGISTROS EN LA BASE DE DATOS
// -----------------------------------------------------------------//

    public static void guardarPrograma(Programa programa) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = getInstance();
            String query = "INSERT INTO programa (id_programa, nombre, nivel) VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, programa.getId());
            statement.setString(2, programa.getNombre());
            statement.setBoolean(3, programa.isNivel());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al guardar el programa en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //tarifas

    public static void guardarTarifa(Tarifa tarifa) {
    Connection conexion = null;
    PreparedStatement statement = null;

    try {
        conexion = getInstance();
        String query = "INSERT INTO tarifa (id_tarifa, tipo, monto) VALUES (?, ?, ?)";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, tarifa.getId());
        statement.setString(2, tarifa.getTipo());
        statement.setDouble(3, tarifa.getMonto());
        
        statement.executeUpdate();
    } catch (SQLException e) {
        System.out.println("Fallo al guardar la tarifa en la BD");
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, null);
    }
}

    //salon

    public static void guardarSalon(Salon salon) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "INSERT INTO salon (id, capacidad_alumnos, piso, identificador, id_edificio) VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, salon.getId());
            statement.setInt(2, salon.getCapacidadAlumnos());
            statement.setInt(3, salon.getPiso());
            statement.setString(4, salon.getIdentificador());
            statement.setInt(5, salon.getEdificio().getId());
            
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al guardar el salon en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //PROFESOR
    public static void guardarProfesor(Profesor profesor) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "INSERT INTO profesor (id_profesor, id_persona, id_departamento) VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, profesor.getId());
            statement.setInt(2, profesor.getPersona().getId());
            statement.setInt(3, profesor.getDepartamento().getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al guardar el profesor en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }
    
    // MATRICULA 

    public static void guardarMatricula(Matricula matricula) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "INSERT INTO matricula (id, id_alumno, id_asignatura_periodo) VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, matricula.getId());
            statement.setInt(2, matricula.getAlumno().getId());
            statement.setInt(3, matricula.getAsignaturaPeriodo().getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al guardar la matrícula en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    // HORARIO
    public static void guardarHorario(Horario horario) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "INSERT INTO horario (id, dia, id_salon, id_bloque_horario, id_asignatura_periodo) VALUES (?, ?, ?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, horario.getId());
            statement.setDate(2, new java.sql.Date(horario.getDia().getTime()));
            statement.setInt(3, horario.getSalon().getId());
            statement.setInt(4, horario.getBloqueHorario().getId());
            statement.setInt(5, horario.getAsignaturaPeriodo().getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al guardar el horario en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //curso

    public static void guardarCurso(Curso curso) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "INSERT INTO curso (id_curso, nombre, temas, competencias) VALUES (?, ?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, curso.getId());
            statement.setString(2, curso.getNombre());
            statement.setString(3, curso.getTemas());
            statement.setString(4, curso.getCompetencias());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al guardar el curso en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    // ALUMNO

    public static void guardarAlumno(Alumno alumno) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "INSERT INTO alumno (id_alumno, id_persona, id_programa) VALUES (?, ?, ?)";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, alumno.getId());
            statement.setInt(2, alumno.getPersona().getId());
            statement.setInt(3, alumno.getPrograma().getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al guardar el alumno en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }
    
    
    
    
    
    





    // ACTUALIZADO EN LA BASE DE DATOSSS
    // ---------------------------------------------------------- //


    public static void actualizarPrograma(Programa programa) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "UPDATE programa SET nombre = ?, nivel = ? WHERE id_programa = ?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, programa.getNombre());
            statement.setBoolean(2, programa.isNivel());
            statement.setInt(3, programa.getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al actualizar el programa en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //tarifa

    public static void actualizarTarifa(Tarifa tarifa) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "UPDATE tarifa SET tipo = ?, monto = ? WHERE id_tarifa = ?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, tarifa.getTipo());
            statement.setDouble(2, tarifa.getMonto());
            statement.setInt(3, tarifa.getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al actualizar la tarifa en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //salon

    public static void actualizarSalon(Salon salon) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "UPDATE salon SET capacidad_alumnos = ?, piso = ?, identificador = ?, id_edificio = ? WHERE id = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, salon.getCapacidadAlumnos());
            statement.setInt(2, salon.getPiso());
            statement.setString(3, salon.getIdentificador());
            statement.setInt(4, salon.getEdificio().getId());
            statement.setInt(5, salon.getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al actualizar el salon en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //PROFESOR
    
    public static void actualizarProfesor(Profesor profesor) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "UPDATE profesor SET id_persona = ?, id_departamento = ? WHERE id_profesor = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, profesor.getPersona().getId());
            statement.setInt(2, profesor.getDepartamento().getId());
            statement.setInt(3, profesor.getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al actualizar el profesor en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    // MATRICULA
    public static void actualizarMatricula(Matricula matricula) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "UPDATE matricula SET id_alumno = ?, id_asignatura_periodo = ? WHERE id = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, matricula.getAlumno().getId());
            statement.setInt(2, matricula.getAsignaturaPeriodo().getId());
            statement.setInt(3, matricula.getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al actualizar la matrícula en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //HORARIO

    public static void actualizarHorario(Horario horario) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "UPDATE horario SET dia = ?, id_salon = ?, id_bloque_horario = ?, id_asignatura_periodo = ? WHERE id = ?";
            statement = conexion.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(horario.getDia().getTime()));
            statement.setInt(2, horario.getSalon().getId());
            statement.setInt(3, horario.getBloqueHorario().getId());
            statement.setInt(4, horario.getAsignaturaPeriodo().getId());
            statement.setInt(5, horario.getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al actualizar el horario en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //curso
    public static void actualizarCurso(Curso curso) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "UPDATE curso SET nombre = ?, temas = ?, competencias = ? WHERE id_curso = ?";
            statement = conexion.prepareStatement(query);
            statement.setString(1, curso.getNombre());
            statement.setString(2, curso.getTemas());
            statement.setString(3, curso.getCompetencias());
            statement.setInt(4, curso.getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al actualizar el curso en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }
    
    //ALUMNO    
    
    public static void actualizarAlumno(Alumno alumno) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "UPDATE alumno SET id_persona = ?, id_programa = ? WHERE id_alumno = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, alumno.getPersona().getId());
            statement.setInt(2, alumno.getPrograma().getId());
            statement.setInt(3, alumno.getId());
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al actualizar el alumno en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }
    
    
    
    



    //ELIMINAR REGISTROS EN LA BASE DE DATOS
// -----------------------------------------------------------------//
    public static void eliminarProgramaBD(int idPrograma) {
        Connection conexion = null;
        PreparedStatement statement = null;

        try {
            conexion = getInstance();
            String query = "DELETE FROM programa WHERE id_programa = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idPrograma);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al eliminar el programa en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //tarifa

    public static void eliminarTarifaBD(int idTarifa) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "DELETE FROM tarifa WHERE id_tarifa = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idTarifa);
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al eliminar la tarifa en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //salon
    
    public static void eliminarSalon(int idSalon) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "DELETE FROM salon WHERE id = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idSalon);
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al eliminar el salon en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //profesor

    public static void eliminarProfesor(int idProfesor) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "DELETE FROM profesor WHERE id_profesor = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idProfesor);
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al eliminar el profesor en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //matricula

    public static void eliminarMatricula(int idMatricula) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "DELETE FROM matricula WHERE id = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idMatricula);
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al eliminar la matrícula en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    //horario

    public static void eliminarHorario(int idHorario) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "DELETE FROM horario WHERE id = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idHorario);
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al eliminar el horario en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }
    
    //curso

    public static void eliminarCurso(int idCurso) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "DELETE FROM curso WHERE id_curso = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idCurso);
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al eliminar el curso en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }

    public static void eliminarAlumno(int idAlumno) {
        Connection conexion = null;
        PreparedStatement statement = null;
    
        try {
            conexion = getInstance();
            String query = "DELETE FROM alumno WHERE id_alumno = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idAlumno);
    
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Fallo al eliminar el alumno en la BD");
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, null);
        }
    }
    
    
    
    
    
    






// ----------------------------------------------------------------------//
// ----------------------------------------------------------------------//
   

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


    public static void cerrarRecursos(Connection conexion, PreparedStatement statement, ResultSet resultado) {
        if (resultado != null) {
            try {
                resultado.close();
            } catch (SQLException e) {
                System.out.println("Fallo al cerrar el ResultSet");
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Fallo al cerrar el PreparedStatement");
                e.printStackTrace();
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Fallo al cerrar la conexion");
                e.printStackTrace();
            }
        }
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

    public static Persona obtenerPersonaPorId(int idPersona) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        Persona persona = null;
    
        try {
            conexion = getInstance();
            String query = "SELECT * FROM persona WHERE id_persona = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idPersona);
            resultado = statement.executeQuery();
    
            if (resultado.next()) {
                int id = resultado.getInt("id_persona");
                String nombre = resultado.getString("nombre");
                String apellido = resultado.getString("apellido");
                String ciudad = resultado.getString("id_ciudad");
                String direccion = resultado.getString("direccion");
                String telefono = resultado.getString("telefono");
                Date fechaNacimiento = resultado.getDate("fecha_nacimiento");



                persona = new Persona(id, nombre, apellido, ciudad, direccion, telefono, fechaNacimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, resultado);
        }
    
        return persona;
    }
    
    public static Facultad obtenerFacultadPorId(int idFacultad) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        Facultad departamento = null;
    
        try {
            conexion = getInstance();
            String query = "SELECT * FROM facultad WHERE id_facultad = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idFacultad);
            resultado = statement.executeQuery();
    
            if (resultado.next()) {
                int id = resultado.getInt("id_departamento");
                String nombre = resultado.getString("nombre");
            
                departamento = new Facultad(id, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, resultado);
        }
    
        return departamento;
    }
    



    public static Edificio obtenerEdificioPorId(int idEdificio) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        Edificio edificio = null;
    
        try {
            conexion = getInstance();
            String query = "SELECT * FROM edificio WHERE id_edificio = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idEdificio);
            resultado = statement.executeQuery();
    
            if (resultado.next()) {
                int id = resultado.getInt("id_edificio");
                String nombre = resultado.getString("nombre");
                
    
                edificio = new Edificio(id, nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cerrarRecursos(conexion, statement, resultado);
        }
    
        return edificio;
    }

    public static Alumno obtenerAlumnoPorId(int idAlumno) {
    Alumno alumno = null;
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM alumno WHERE id_alumno = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, idAlumno);
        resultado = statement.executeQuery();

        if (resultado.next()) {
            int id = resultado.getInt("id_alumno");
            String nombre = resultado.getString("nombre");
            String apellido = resultado.getString("apellido");

            alumno = new Alumno(id, nombre, apellido);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return alumno;
}

public static AsignaturaPeriodo obtenerAsignaturaPeriodoPorId(int idAsignaturaPeriodo) {
    AsignaturaPeriodo asignaturaPeriodo = null;
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM asignatura_periodo WHERE id_asignatura_periodo = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, idAsignaturaPeriodo);
        resultado = statement.executeQuery();

        if (resultado.next()) {
            int id = resultado.getInt("id_asignatura_periodo");
            int idAsignatura = resultado.getInt("id_asignatura");
            int idPeriodo = resultado.getInt("id_periodo");

            Alumno asignatura = obtenerAlumnoPorId(idAsignatura);
            AsignaturaPeriodo periodo = obtenerAsignaturaPeriodoPorId(idPeriodo);

            asignaturaPeriodo = new AsignaturaPeriodo(id, asignatura, periodo);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return asignaturaPeriodo;
}


    public static Salon obtenerSalonPorId(int idSalon) {
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;
    Salon salon = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM salon WHERE id = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, idSalon);
        resultado = statement.executeQuery();

        if (resultado.next()) {
            int id = resultado.getInt("id");
            int capacidadAlumnos = resultado.getInt("capacidad_alumnos");
            int piso = resultado.getInt("piso");
            String identificador = resultado.getString("identificador");
            int idEdificio = resultado.getInt("id_edificio");

            Edificio edificio = obtenerEdificioPorId(idEdificio);

            salon = new Salon(id, capacidadAlumnos, piso, identificador, edificio);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return salon;
}

public static BloqueHorario obtenerBloqueHorarioPorId(int idBloqueHorario) {
    Connection conexion = null;
    PreparedStatement statement = null;
    ResultSet resultado = null;
    BloqueHorario bloqueHorario = null;

    try {
        conexion = getInstance();
        String query = "SELECT * FROM bloque_horario WHERE id = ?";
        statement = conexion.prepareStatement(query);
        statement.setInt(1, idBloqueHorario);
        resultado = statement.executeQuery();

        if (resultado.next()) {
            int id = resultado.getInt("id");
            Time horaInicio = resultado.getTime("hora_inicio");
            Time horaFin = resultado.getTime("hora_fin");

            bloqueHorario = new BloqueHorario(id, horaInicio, horaFin);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        cerrarRecursos(conexion, statement, resultado);
    }

    return bloqueHorario;
}

    

    public static void editarTarifa(Tarifa tarifa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'editarTarifa'");
    }


}
