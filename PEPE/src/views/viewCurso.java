package views;


import repository.models.Curso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class viewCurso extends viewMain {

    private static final List<Curso> listaCursos = new ArrayList<>();

    public static void startMenu() {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    listarCursos();
                    break;
                case 2:
                    crearCurso();
                    break;
                case 3:
                    editarCurso();
                    break;
                case 4:
                    eliminarCurso();
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

    public static void crearCurso() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el nombre del curso:");
        String nombre = scanner.nextLine();

        System.out.println("Introduce los temas del curso:");
        String temas = scanner.nextLine();

        System.out.println("Introduce las competencias del curso:");
        String competencias = scanner.nextLine();

        int id = generarIdCurso();
        Curso curso = new Curso(id, nombre, temas, competencias);
        listaCursos.add(curso);

        System.out.println("Curso creado con éxito.");
    }

    public static void editarCurso() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del curso a editar: ");
        int idCurso = leer.nextInt();
        Curso curso = obtenerCursoPorId(idCurso);
        if (curso != null) {
           
            
            System.out.println("Introduce el nombre del curso:");
            String nombre = leer.nextLine();
    
            System.out.println("Introduce los temas del curso:");
            String temas = leer.nextLine();
    
            System.out.println("Introduce las competencias del curso:");
            String competencias = leer.nextLine();

            curso.setNombre(nombre);
            curso.setTemas(temas);
            curso.setCompetencias(competencias);

            System.out.println("Curso actualizado coreectamente.");

        } else {
            System.out.println("No se encontró ningún curso con el ID proporcionado.");
        }
    }

    public static void eliminarCurso() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del curso a eliminar: ");
        int idCurso = leer.nextInt();
        Curso curso = obtenerCursoPorId(idCurso);
        if (curso != null) {
            listaCursos.remove(curso);
            System.out.println("Curso eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún curso con el ID proporcionado.");
        }
    }

    public static void listarCursos() {
        if (listaCursos.isEmpty()) {
            System.out.println("No hay cursos registrados.");
        } else {
            System.out.println("Lista de Cursos:");
            for (Curso curso : listaCursos) {
                System.out.println(curso);
            }
        }
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Cursos----");
        System.out.println("1. Listar cursos.");
        System.out.println("2. Crear curso.");
        System.out.println("3. Editar curso.");
        System.out.println("4. Eliminar curso.");
        System.out.println("5. Salir ");
        Scanner leer = new Scanner(System.in);
        return leer.nextInt();
    }

    private static int generarIdCurso() {
        int maxId = 0;
        for (Curso curso : listaCursos) {
            if (curso.getId() > maxId) {
                maxId = curso.getId();
            }
        }
        return maxId + 1;
    }

    private static Curso obtenerCursoPorId(int idCurso) {
        for (Curso curso : listaCursos) {
            if (curso.getId() == idCurso) {
                return curso;
            }
        }
        return null;
    }
}


