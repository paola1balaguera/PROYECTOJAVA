package views;


import repository.models.Facultad;
import repository.models.Persona;
import repository.models.Profesor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class viewProfesor extends viewMain {

    private static final List<Profesor> listaProfesores = new ArrayList<>();
    private static final List<Persona> listaPersonas = new ArrayList<>();
    private static final List<Facultad> listaDepartamentos = new ArrayList<>();

    public static void startMenu() {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    listarProfesores();
                    break;
                case 2:
                    crearProfesor();
                    break;
                case 3:
                    editarProfesor();
                    break;
                case 4:
                    eliminarProfesor();
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

    public static void crearProfesor() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el ID de la persona del profesor:");
        int idPersona = scanner.nextInt();
        Persona persona = obtenerPersonaPorId(idPersona);

        System.out.println("Introduce el ID del departamento del profesor:");
        int idDepartamento = scanner.nextInt();
        Facultad departamento = obtenerDepartamentoPorId(idDepartamento);

        int id = generarIdProfesor();
        Profesor profesor = new Profesor(id, persona, departamento);
        listaProfesores.add(profesor);

        System.out.println("Profesor creado con éxito.");
    }

    public static void editarProfesor() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del profesor a editar: ");
        int idProfesor = leer.nextInt();
        Profesor profesor = obtenerProfesorPorId(idProfesor);
        if (profesor != null) {

            System.out.println("Introduce el ID del departamento del profesor:");
            int idDepartamento = leer.nextInt();
            Facultad departamento = obtenerDepartamentoPorId(idDepartamento);

            profesor.setDepartamento(departamento);

            System.out.println("Profesor actualizado correctamente.");

            } else {
                System.out.println("No se encontró ningún profesor con el ID proporcionado.");
            }
    }

    public static void eliminarProfesor() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del profesor a eliminar: ");
        int idProfesor = leer.nextInt();
        Profesor profesor = obtenerProfesorPorId(idProfesor);
        if (profesor != null) {
            listaProfesores.remove(profesor);
            System.out.println("Profesor eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún profesor con el ID proporcionado.");
        }
    }

    public static void listarProfesores() {
        if (listaProfesores.isEmpty()) {
            System.out.println("No hay profesores registrados.");
        } else {
            System.out.println("Lista de Profesores:");
            for (Profesor profesor : listaProfesores) {
                System.out.println(profesor);
            }
        }
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Profesores----");
        System.out.println("1. Listar profesores.");
        System.out.println("2. Crear profesor.");
        System.out.println("3. Editar profesor.");
        System.out.println("4. Eliminar profesor.");
        System.out.println("5. Salir ");
        Scanner leer = new Scanner(System.in);
        return leer.nextInt();
    }

    private static int generarIdProfesor() {
        int maxId = 0;
        for (Profesor profesor : listaProfesores) {
            if (profesor.getId() > maxId) {
                maxId = profesor.getId();
            }
        }
        return maxId + 1;
    }

    private static Profesor obtenerProfesorPorId(int idProfesor) {
        for (Profesor profesor : listaProfesores) {
            if (profesor.getId() == idProfesor) {
                return profesor;
            }
        }
        return null;
    }

    private static Persona obtenerPersonaPorId(int idPersona) {
        for (Persona persona : listaPersonas) {
            if (persona.getId() == idPersona) {
                return persona;
            }
        }
        return null;
    }

    private static Facultad obtenerDepartamentoPorId(int idDepartamento) {
        for (Facultad departamento : listaDepartamentos) {
            if (departamento.getId() == idDepartamento) {
                return departamento;
            }
        }
        return null;
    }
}


