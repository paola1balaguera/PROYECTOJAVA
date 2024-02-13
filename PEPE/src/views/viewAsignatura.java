package views;


import repository.models.Asignatura;
import repository.models.Curso;
import repository.models.Programa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class viewAsignatura extends viewMain {

    private static final List<Asignatura> listaAsignaturas = new ArrayList<>();
    private static final List<Curso> listaCursos = new ArrayList<>();
    private static final List<Programa> listaProgramas = new ArrayList<>();

    public static void startMenu() {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    listarAsignaturas();
                    break;
                case 2:
                    crearAsignatura();
                    break;
                case 3:
                    editarAsignatura();
                    break;
                case 4:
                    eliminarAsignatura();
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

    public static void crearAsignatura() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el nombre de la asignatura:");
        String nombre = scanner.nextLine();

        System.out.println("Introduce el número de créditos:");
        int creditos = scanner.nextInt();

        System.out.println("Introduce el cupo disponible:");
        int cupoDisponible = scanner.nextInt();

        System.out.println("Introduce el ID del curso:");
        int idCurso = scanner.nextInt();
        Curso curso = obtenerCursoPorId(idCurso);

        System.out.println("Introduce el ID del programa:");
        int idPrograma = scanner.nextInt();
        Programa programa = obtenerProgramaPorId(idPrograma);

        int id = generarIdAsignatura();
        Asignatura asignatura = new Asignatura(id, nombre, creditos, cupoDisponible, curso, programa);
        listaAsignaturas.add(asignatura);

        System.out.println("Asignatura creada con éxito.");
    }

    public static void editarAsignatura() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID de la asignatura a editar: ");
        int idAsignatura = leer.nextInt();
        Asignatura asignatura = obtenerAsignaturaPorId(idAsignatura);
        if (asignatura != null) {
            // Lógica para editar la asignatura
        } else {
            System.out.println("No se encontró ninguna asignatura con el ID proporcionado.");
        }
    }

    public static void eliminarAsignatura() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID de la asignatura a eliminar: ");
        int idAsignatura = leer.nextInt();
        Asignatura asignatura = obtenerAsignaturaPorId(idAsignatura);
        if (asignatura != null) {
            listaAsignaturas.remove(asignatura);
            System.out.println("Asignatura eliminada exitosamente.");
        } else {
            System.out.println("No se encontró ninguna asignatura con el ID proporcionado.");
        }
    }

    public static void listarAsignaturas() {
        if (listaAsignaturas.isEmpty()) {
            System.out.println("No hay asignaturas registradas.");
        } else {
            System.out.println("Lista de Asignaturas:");
            for (Asignatura asignatura : listaAsignaturas) {
                System.out.println(asignatura);
            }
        }
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Asignaturas----");
        System.out.println("1. Listar asignaturas.");
        System.out.println("2. Crear asignatura.");
        System.out.println("3. Editar asignatura.");
        System.out.println("4. Eliminar asignatura.");
        System.out.println("5. Salir ");
        Scanner leer = new Scanner(System.in);
        return leer.nextInt();
    }

    private static int generarIdAsignatura() {
        int maxId = 0;
        for (Asignatura asignatura : listaAsignaturas) {
            if (asignatura.getId() > maxId) {
                maxId = asignatura.getId();
            }
        }
        return maxId + 1;
    }

    private static Asignatura obtenerAsignaturaPorId(int idAsignatura) {
        for (Asignatura asignatura : listaAsignaturas) {
            if (asignatura.getId() == idAsignatura) {
                return asignatura;
            }
        }
        return null;
    }

    private static Curso obtenerCursoPorId(int idCurso) {
        for (Curso curso : listaCursos) {
            if (curso.getId() == idCurso) {
                return curso;
            }
        }
        return null;
    }

    private static Programa obtenerProgramaPorId(int idPrograma) {
        for (Programa programa : listaProgramas) {
            if (programa.getId() == idPrograma) {
                return programa;
            }
        }
        return null;
    }
}

