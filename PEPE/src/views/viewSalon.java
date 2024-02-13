package views;


import repository.models.Edificio;
import repository.models.Salon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class viewSalon extends viewMain {

    private static final List<Salon> listaSalones = new ArrayList<>();
    private static final List<Edificio> listaEdificios = new ArrayList<>();

    public static void startMenu() {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    listarSalones();
                    break;
                case 2:
                    crearSalon();
                    break;
                case 3:
                    editarSalon();
                    break;
                case 4:
                    eliminarSalon();
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

    public static void crearSalon() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce la capacidad de alumnos:");
        int capacidadAlumnos = scanner.nextInt();

        System.out.println("Introduce el piso:");
        int piso = scanner.nextInt();

        System.out.println("Introduce el identificador:");
        String identificador = scanner.next();

        System.out.println("Introduce el ID del edificio al que pertenece:");
        int idEdificio = scanner.nextInt();
        Edificio edificio = obtenerEdificioPorNombre(idEdificio);

        int id = generarIdSalon();
        Salon salon = new Salon(id, capacidadAlumnos, piso, identificador, edificio);
        listaSalones.add(salon);

        System.out.println("Salón creado con éxito.");
    }

    public static void editarSalon() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del salón a editar: ");
        int idSalon = leer.nextInt();
        Salon salon = obtenerSalonPorId(idSalon);
        if (salon != null) {
            // Lógica para editar el salón
        } else {
            System.out.println("No se encontró ningún salón con el ID proporcionado.");
        }
    }

    public static void eliminarSalon() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del salón a eliminar: ");
        int idSalon = leer.nextInt();
        Salon salon = obtenerSalonPorId(idSalon);
        if (salon != null) {
            listaSalones.remove(salon);
            System.out.println("Salón eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún salón con el ID proporcionado.");
        }
    }

    public static void listarSalones() {
        if (listaSalones.isEmpty()) {
            System.out.println("No hay salones registrados.");
        } else {
            System.out.println("Lista de Salones:");
            for (Salon salon : listaSalones) {
                System.out.println(salon);
            }
        }
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Salones----");
        System.out.println("1. Listar salones.");
        System.out.println("2. Crear salón.");
        System.out.println("3. Editar salón.");
        System.out.println("4. Eliminar salón.");
        System.out.println("5. Salir ");
        Scanner leer = new Scanner(System.in);
        return leer.nextInt();
    }

    private static int generarIdSalon() {
        int maxId = 0;
        for (Salon salon : listaSalones) {
            if (salon.getId() > maxId) {
                maxId = salon.getId();
            }
        }
        return maxId + 1;
    }

    private static Salon obtenerSalonPorId(int idSalon) {
        for (Salon salon : listaSalones) {
            if (salon.getId() == idSalon) {
                return salon;
            }
        }
        return null;
    }

    private static Edificio obtenerEdificioPorNombre(int idEdificio) {
        for (Edificio edificio : listaEdificios) {
            if (edificio.getNombre().equals(idEdificio)) {
                return edificio;
            }
        }
        return null;
    }
}


