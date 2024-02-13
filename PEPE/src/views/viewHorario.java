package views;

import repository.models.Horario;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class viewHorario extends viewMain {

    private static final List<Horario> listaHorarios = new ArrayList<>();

    public static void startMenu() {
        int op;
        do {
            op = mostrarMenu();
            switch (op) {
                case 1:
                    listarHorarios();
                    break;
                case 2:
                    buscarHorarioPorDia();
                    break;
                case 3:
                    buscarHorarioPorSalon();
                    break;
                case 4:
                    buscarHorarioPorAsignatura();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        } while (op != 5);
    }

    public static void listarHorarios() {
        if (listaHorarios.isEmpty()) {
            System.out.println("No hay horarios registrados.");
        } else {
            System.out.println("Lista de Horarios:");
            for (Horario horario : listaHorarios) {
                System.out.println(horario);
            }
        }
    }

    public static void buscarHorarioPorDia() {
        Scanner leer = new Scanner(System.in);
        System.out.print("Introduce el día para buscar horarios (DD/MM/YYYY): ");
        String diaStr = leer.nextLine();
        Date dia = parseFecha(diaStr);

        List<Horario> horariosDia = obtenerHorariosPorDia(dia);
        if (!horariosDia.isEmpty()) {
            System.out.println("Horarios para el día " + diaStr + ":");
            for (Horario horario : horariosDia) {
                System.out.println(horario);
            }
        } else {
            System.out.println("No se encontraron horarios para el día " + diaStr);
        }
    }

    private static Date parseFecha(String diaStr) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'parseFecha'");
    }

    public static void buscarHorarioPorSalon() {
        Scanner leer = new Scanner(System.in);
        System.out.print("Introduce el ID del salón para buscar horarios: ");
        int idSalon = leer.nextInt();

        List<Horario> horariosSalon = obtenerHorariosPorSalon(idSalon);
        if (!horariosSalon.isEmpty()) {
            System.out.println("Horarios para el salón con ID " + idSalon + ":");
            for (Horario horario : horariosSalon) {
                System.out.println(horario);
            }
        } else {
            System.out.println("No se encontraron horarios para el salón con ID " + idSalon);
        }
    }

    public static void buscarHorarioPorAsignatura() {
        Scanner leer = new Scanner(System.in);
        System.out.print("Introduce el ID de la asignatura para buscar horarios: ");
        int idAsignatura = leer.nextInt();

        List<Horario> horariosAsignatura = obtenerHorariosPorAsignatura(idAsignatura);
        if (!horariosAsignatura.isEmpty()) {
            System.out.println("Horarios para la asignatura con ID " + idAsignatura + ":");
            for (Horario horario : horariosAsignatura) {
                System.out.println(horario);
            }
        } else {
            System.out.println("No se encontraron horarios para la asignatura con ID " + idAsignatura);
        }
    }

    public static int mostrarMenu() {
        System.out.println("----Menu--Horarios----");
        System.out.println("1. Listar horarios.");
        System.out.println("2. Buscar horarios por día.");
        System.out.println("3. Buscar horarios por ID de salón.");
        System.out.println("4. Buscar horarios por ID de asignatura.");
        System.out.println("5. Salir ");
        Scanner leer = new Scanner(System.in);
        return leer.nextInt();
    }

    private static List<Horario> obtenerHorariosPorDia(Date dia) {
        List<Horario> horariosDia = new ArrayList<>();
        for (Horario horario : listaHorarios) {
            if (horario.getDia().equals(dia)) {
                horariosDia.add(horario);
            }
        }
        return horariosDia;
    }

    private static List<Horario> obtenerHorariosPorSalon(int idSalon) {
        List<Horario> horariosSalon = new ArrayList<>();
        for (Horario horario : listaHorarios) {
            if (horario.getSalon().getId() == idSalon) {
                horariosSalon.add(horario);
            }
        }
        return horariosSalon;
    }

    private static List<Horario> obtenerHorariosPorAsignatura(int idAsignatura) {
        List<Horario> horariosAsignatura = new ArrayList<>();
        for (Horario horario : listaHorarios) {
            if (horario.getAsignatura().getId() == idAsignatura) {
                horariosAsignatura.add(horario);
            }
        }
        return horariosAsignatura;
    }
}


