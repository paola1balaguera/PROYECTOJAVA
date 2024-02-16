package views;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import java.text.ParseException;
import java.util.Scanner;

import repository.models.*;
import utils.connectionBDMySql.connectionBDMySql;

public class Universidad {
    private List<Programa> programas;
    private List<Alumno> alumnos;
    private static List<Matricula> matriculas;

    

    // 1. Listar los estudiantes matriculados por programa
    public static List<Alumno> getEstudiantesPorPrograma(Programa programa) {
        List<Alumno> estudiantesPorPrograma = new ArrayList<>();
        for (Matricula matricula : matriculas) {
            if (matricula.getPrograma().equals(programa)) {
                estudiantesPorPrograma.add(matricula.getEstudiante());
            }
        }
        return estudiantesPorPrograma;
    }

    // 2. Calcular el costo del semestre de un estudiante según las asignaturas matriculadas.
    public static double calcularCostoSemestre(int idEstudiante, int idPeriodo) {
        double costo = 0;
        String procedure = "{CALL CalcularCostoSemestre(?, ?)}";
    
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?serverTimezone=America/Santiago", "administrador", "pepe123");
                CallableStatement stmt = conn.prepareCall(procedure)) {
    
            stmt.setInt(1, idEstudiante);
            stmt.setInt(2, idPeriodo);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                costo = rs.getDouble(1);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    
        return costo;
    }

    //ingresos semtre 3
    public double calcularIngresosSemestre(int idPeriodo) {
        double ingresosSemestre = 0;
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?serverTimezone=America/Santiago", "administrador", "pepe123" );
             CallableStatement stmt = conn.prepareCall("{call calcular_ingresos_semestre(?)}")) {
    
            stmt.setInt(1, idPeriodo);
    
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                ingresosSemestre = rs.getDouble("Ingresos Semestre");
            }
    
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    
        return ingresosSemestre;
    }
    
    

    // 4. Imprimir el horario de un estudiante. Simplemente en forma de lista.
public static List<Horario> getHorarioEstudiante(Alumno alumno) {
List<Horario> horario = new ArrayList<>();
String procedure = "{CALL GetStudentSchedule(?)}";

try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/java?serverTimezone=America/Santiago", "administrador", "pepe123");
        CallableStatement stmt = conn.prepareCall(procedure)) {

    stmt.setInt(1, alumno.getId());
    ResultSet rs = stmt.executeQuery();

    while (rs.next()) {
        // Asume que tienes una clase Horario con un constructor que acepta los datos de cada columna
        Horario h = new Horario(rs.getString("dia"), rs.getString("hora_inicio"), rs.getString("hora_fin"));
        horario.add(h);
    }
} catch (SQLException e) {
    System.out.println(e.getMessage());
}

return horario;
}

    // 5. Imprimir en forma descendente el número de matriculados por programa
    public static List<Programa> getProgramasPorMatriculas() {
        Map<Programa, Integer> matriculasPorPrograma = new HashMap<>();
        for (Matricula matricula : matriculas) {
            matriculasPorPrograma.put(matricula.getPrograma(), matriculasPorPrograma.getOrDefault(matricula.getPrograma(), 0) + 1);
        }
        List<Programa> programasOrdenados = new ArrayList<>(matriculasPorPrograma.keySet());
        programasOrdenados.sort((p1, p2) -> matriculasPorPrograma.get(p2) - matriculasPorPrograma.get(p1));
        return programasOrdenados;
    }


    public static void startMenu() {
        cargarMatriculasDesdeBD();
        int op;
 }

    private static void cargarMatriculasDesdeBD() {
        connectionBDMySql.obtenerMatriculas();
    }

    public void Menu() {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
    
        while (opcion != 6) {
            System.out.println("1. Listar los estudiantes matriculados por programa");
            System.out.println("2. Calcular el costo del semestre de un estudiante");
            System.out.println("3. Calcular los ingresos de la universidad por semestre");
            System.out.println("4. Imprimir el horario de un estudiante");
            System.out.println("5. Imprimir en forma descendente el número de matriculados por programa");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
    
            switch (opcion) {
                case 1:
                    
                    Programa programa = programas.get(0);
                    List<Alumno> estudiantes = getEstudiantesPorPrograma(programa);
                    System.out.println("Estudiantes: " + estudiantes);
                    break;
                case 2:
                    
                    double costo = calcularCostoSemestre(1, 7);
                    System.out.println("Costo del semestre: " + costo);
                    break;
                case 3:
                    double ingresos = calcularIngresosSemestre(opcion);
                    System.out.println("Ingresos de la universidad: " + ingresos);
                    break;
                case 4:
                   
                    Alumno alumno = alumnos.get(0);
                    List<Horario> horario = getHorarioEstudiante(alumno);
                    System.out.println("Horario: " + horario);
                    break;
                case 5:
                    List<Programa> programas = getProgramasPorMatriculas();
                    System.out.println("Programas: " + programas);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    
        scanner.close();
    }
}


