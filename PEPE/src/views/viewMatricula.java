package views;


import repository.models.AsignaturaPeriodo;
import repository.models.Alumno;
import repository.models.Matricula;
import utils.connectionBDMySql.connectionBDMySql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class viewMatricula extends viewMain {

    private static final List<Matricula> listaMatriculas = new ArrayList<>();
    private static final List<Alumno> listaAlumnos = new ArrayList<>();
    private static final List<AsignaturaPeriodo> listaAsignaturas = new ArrayList<>();

    public class connectionBDMySql {
    private static final String URL = "jdbc:mysql://localhost:3306/java";
    private final static String USER = "administrador";
    private static final String PASSWORD = "pepe123";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}




    public static void startMenu() {
        cargarMatriculasDesdeBD();
        int op;

        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    listarMatriculas();
                    break;
                case 2:
                    crearMatricula();
                    break;
                case 3:
                    editarMatricula();
                    break;
                case 4:
                    eliminarMatricula();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (op >= 1 && op < 5);
    }


    public static void crearMatricula() {
        Scanner scanner = new Scanner(System.in);
    
        System.out.println("Introduce el ID del alumno:");
        int idAlumno = scanner.nextInt();
        Alumno alumno = obtenerAlumnoPorId(idAlumno);
    
        System.out.println("Introduce el ID de la asignatura:");
        int idAsignatura = scanner.nextInt();
        AsignaturaPeriodo asignatura = obtenerAsignaturaPorId(idAsignatura);
    
        System.out.println("Introduce el estado de la matrícula (APROBADO, REPROBADO, CURSANDO):");
        String estadoString = scanner.next();
        Matricula.EstadoEnum estado = Matricula.EstadoEnum.valueOf(estadoString.toUpperCase());
    
        int id = generarIdMatricula();
        Matricula matricula = new Matricula(id, alumno, asignatura, estado);
        listaMatriculas.add(matricula);
    
        System.out.println("Matrícula creada con éxito.");
    }


    public static void cargarMatriculasDesdeBD() {
    Connection conn = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
        conn = connectionBDMySql.getConnection();
        String query = "SELECT * FROM matricula"; 
        stmt = conn.prepareStatement(query);
        rs = stmt.executeQuery();

        while (rs.next()) {
            int id = rs.getInt("id_matricula");
            int idAlumno = rs.getInt("id_alumno");
            int idAsignatura = rs.getInt("id_asignaturaPeriodo");
            String estadoString = rs.getString("estado");

            Alumno alumno = obtenerAlumnoPorId(idAlumno);
            AsignaturaPeriodo asignatura = obtenerAsignaturaPorId(idAsignatura);
            
            Matricula.EstadoEnum estadoEnum = Matricula.EstadoEnum.valueOf(estadoString.toUpperCase());

            Matricula matricula = new Matricula(id, alumno, asignatura, estadoEnum);
            listaMatriculas.add(matricula);
        }
    } catch (SQLException e) {
        System.out.println("Error loading matriculas from database: " + e.getMessage());
        e.printStackTrace();
    } finally {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("Error closing ResultSet: " + e.getMessage());
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.out.println("Error closing PreparedStatement: " + e.getMessage());
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                System.out.println("Error closing Connection: " + e.getMessage());
            }
        }
    }
}

    public static void editarMatricula() {
        Scanner leer = new Scanner(System.in);

        System.out.print("ID de la matrícula a editar: ");
        int idMatricula = leer.nextInt();
        Matricula matricula = obtenerMatriculaPorId(idMatricula);

        if (matricula != null) {
            System.out.println("Introduce el ID de la asignatura:");
            int idAsignatura = leer.nextInt();
            AsignaturaPeriodo asignatura = obtenerAsignaturaPorId(idAsignatura);
        
            System.out.println("Introduce el estado de la matrícula (APROBADO, REPROBADO, CURSANDO):");
            String estadoString = leer.next();
            Matricula.EstadoEnum estado = Matricula.EstadoEnum.valueOf(estadoString.toUpperCase());
        

            matricula.setAsignaturaPeriodo(asignatura);
            matricula.setEstadoEnum(estado);

            System.out.println("Matricula actualizada correctamente.");
        
        } else 
            {
            System.out.println("No se encontró ninguna matrícula con el ID proporcionado.");
        }
    }

    public static void eliminarMatricula() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID de la matrícula a eliminar: ");
        int idMatricula = leer.nextInt();
        Matricula matricula = obtenerMatriculaPorId(idMatricula);
        if (matricula != null) {
            listaMatriculas.remove(matricula);
            System.out.println("Matrícula eliminada exitosamente.");
        } else {
            System.out.println("No se encontró ninguna matrícula con el ID proporcionado.");
        }
    }

    public static void listarMatriculas() {
        if (listaMatriculas.isEmpty()) {
            System.out.println("No hay matrículas registradas.");
        } else {
            System.out.println("Lista de Matrículas:");
            for (Matricula matricula : listaMatriculas) {
                System.out.println(matricula);
            }
        }
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Matriculas----");
        System.out.println("1. Listar matriculas.");
        System.out.println("2. Crear matricula.");
        System.out.println("3. Editar matricula.");
        System.out.println("4. Eliminar matricula.");
        System.out.println("5. Salir ");
        Scanner leer = new Scanner(System.in);
        return leer.nextInt();
    }

    private static int generarIdMatricula() {
        int maxId = 0;
        for (Matricula matricula : listaMatriculas) {
            if (matricula.getId() > maxId) {
                maxId = matricula.getId();
            }
        }
        return maxId + 1;
    }

    private static AsignaturaPeriodo obtenerAsignaturaPorId(int idAsignatura) {
        for (AsignaturaPeriodo asignatura : listaAsignaturas) {
            if (asignatura.getId() == idAsignatura) {
                return asignatura;
            }
        }
        return null;
    }

    private static Alumno obtenerAlumnoPorId(int idAlumno) {
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getId() == idAlumno) {
                return alumno;
            }
        }
        return null;
    }

    private static Matricula obtenerMatriculaPorId(int idMatricula) {
        for (Matricula matricula : listaMatriculas) {
            if (matricula.getId() == idMatricula) {
                return matricula;
            }
        }
        return null;
    }
}


