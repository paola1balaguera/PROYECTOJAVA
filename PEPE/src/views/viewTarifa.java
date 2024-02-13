package views;

import repository.models.Periodo;
import repository.models.Programa;
import repository.models.Tarifa;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class viewTarifa extends viewMain {

    private static final List<Tarifa> listaTarifas = new ArrayList<>();
    private static final List<Periodo> listaPeriodos = new ArrayList<>();
    private static final List<Programa> listaProgramas = new ArrayList<>();

    public static void startMenu() {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    listarTarifas();
                    break;
                case 2:
                    crearTarifa();
                    break;
                case 3:
                    editarTarifa();
                    break;
                case 4:
                    eliminarTarifa();
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

    public static void crearTarifa() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el valor del crédito:");
        double valorCredito = scanner.nextDouble();

        System.out.println("Introduce el ID del periodo:");
        int idPeriodo = scanner.nextInt();
        Periodo periodo = obtenerPeriodoPorId(idPeriodo);

        System.out.println("Introduce el ID del programa:");
        int idPrograma = scanner.nextInt();
        Programa programa = obtenerProgramaPorId(idPrograma);

        int id = generarIdTarifa();
        Tarifa tarifa = new Tarifa(id, valorCredito, periodo, programa);
        listaTarifas.add(tarifa);

        System.out.println("Tarifa creada con éxito.");
    }

    public static void editarTarifa() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID de la tarifa a editar: ");
        int idTarifa = leer.nextInt();
        Tarifa tarifa = obtenerTarifaPorId(idTarifa);
        if (tarifa != null) {
            // Lógica para editar la tarifa
        } else {
            System.out.println("No se encontró ninguna tarifa con el ID proporcionado.");
        }
    }

    public static void eliminarTarifa() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID de la tarifa a eliminar: ");
        int idTarifa = leer.nextInt();
        Tarifa tarifa = obtenerTarifaPorId(idTarifa);
        if (tarifa != null) {
            listaTarifas.remove(tarifa);
            System.out.println("Tarifa eliminada exitosamente.");
        } else {
            System.out.println("No se encontró ninguna tarifa con el ID proporcionado.");
        }
    }

    public static void listarTarifas() {
        if (listaTarifas.isEmpty()) {
            System.out.println("No hay tarifas registradas.");
        } else {
            System.out.println("Lista de Tarifas:");
            for (Tarifa tarifa : listaTarifas) {
                System.out.println(tarifa);
            }
        }
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Tarifas----");
        System.out.println("1. Listar tarifas.");
        System.out.println("2. Crear tarifa.");
        System.out.println("3. Editar tarifa.");
        System.out.println("4. Eliminar tarifa.");
        System.out.println("5. Salir ");
        Scanner leer = new Scanner(System.in);
        return leer.nextInt();
    }

    private static int generarIdTarifa() {
        int maxId = 0;
        for (Tarifa tarifa : listaTarifas) {
            if (tarifa.getId() > maxId) {
                maxId = tarifa.getId();
            }
        }
        return maxId + 1;
    }

    private static Tarifa obtenerTarifaPorId(int idTarifa) {
        for (Tarifa tarifa : listaTarifas) {
            if (tarifa.getId() == idTarifa) {
                return tarifa;
            }
        }
        return null;
    }

    private static Periodo obtenerPeriodoPorId(int idPeriodo) {
        for (Periodo periodo : listaPeriodos) {
            if (periodo.getId() == idPeriodo) {
                return periodo;
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

    
