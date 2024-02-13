package views;

import repository.models.Programa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewPrograma extends viewMain {

    private static final List<Programa> listaProgramas = new ArrayList<>();

    public static void startMenu() {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    listarProgramas();
                    break;
                case 2:
                    crearPrograma();
                    break;
                case 3:
                    editarPrograma();
                    break;
                case 4:
                    eliminarPrograma();
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

    public static void crearPrograma() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el nombre del programa:");
        String nombre = scanner.nextLine();

        System.out.println("¿Es de nivel avanzado? (true/false):");
        boolean nivel = scanner.nextBoolean();

        int id = generarIdPrograma();
        Programa programa = new Programa(id, nombre, nivel);
        listaProgramas.add(programa);

        System.out.println("Programa creado con éxito.");
    }

    public static void editarPrograma() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del programa a editar: ");
        int idPrograma = leer.nextInt();
        Programa programa = obtenerProgramaPorId(idPrograma);
        if (programa != null) {
            // Lógica para editar el programa
        } else {
            System.out.println("No se encontró ningún programa con el ID proporcionado.");
        }
    }

    public static void eliminarPrograma() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del programa a eliminar: ");
        int idPrograma = leer.nextInt();
        Programa programa = obtenerProgramaPorId(idPrograma);
        if (programa != null) {
            listaProgramas.remove(programa);
            System.out.println("Programa eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún programa con el ID proporcionado.");
        }
    }

    public static void listarProgramas() {
        if (listaProgramas.isEmpty()) {
            System.out.println("No hay programas registrados.");
        } else {
            System.out.println("Lista de Programas:");
            for (Programa programa : listaProgramas) {
                System.out.println(programa);
            }
        }
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Programas----");
        System.out.println("1. Listar programas.");
        System.out.println("2. Crear programa.");
        System.out.println("3. Editar programa.");
        System.out.println("4. Eliminar programa.");
        System.out.println("5. Salir ");
        Scanner leer = new Scanner(System.in);
        return leer.nextInt();
    }

    private static int generarIdPrograma() {
        int maxId = 0;
        for (Programa programa : listaProgramas) {
            if (programa.getId() > maxId) {
                maxId = programa.getId();
            }
        }
        return maxId + 1;
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


