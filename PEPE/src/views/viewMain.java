package views;


import java.text.ParseException;
import java.util.Scanner;

import repository.implementations.implAlumno.RepositoryAlumnoMysqlImpl;
import repository.implementations.implPersona.RepositoryPersonaMysqlImpl;
import repository.models.Alumno;
import repository.models.Persona;
import services.ServiceCrudGenerico;
import services.implementations.*;


public class viewMain {

    public static final ServiceCrudGenerico<Persona> servicePersona = new ServicePersonaImplement(new RepositoryPersonaMysqlImpl());
    public static final ServiceCrudGenerico<Alumno> serviceAlumno = new ServiceAlumnoImplement(new RepositoryAlumnoMysqlImpl());
    public static final Scanner leer = new Scanner(System.in);

    /**
     * @param args
     * @throws ParseException
     */
    public static void main(String[] args) throws ParseException {
        int op = 0;

        do {
            op = menuMain();
            switch (op) {
                case 1:
                ViewPrograma.startMenu();
        
                    break;
                case 2:
                viewMatricula.startMenu();
                case 3:
                viewSalon.startMenu();

                case 4:
                viewAlumno.startMenu();

                case 5:
                    viewTarifa.startMenu();
                case 6:
                    viewAsignatura.startMenu();
                case 7:
                    viewCurso.startMenu();
                case 8:
                    viewProfesor.startMenu();
                case 9:
                    Universidad.startMenu();
                break;
                    
                case 10:

                default:
                    System.out.println("Fin");
                    break;
            }
        } while(op >= 1 && op < 2);
    }

    public static int menuMain() {
        System.out.println("\t MENU PRINCIPAL");
        System.out.println("==============================");
        System.out.println("1. Modulo de programa");
        System.out.println("2. Modulo de matriculas");
        System.out.println("3. Modulo de salon");
        System.out.println("4. Modulo de Alumnos");
        System.out.println("5. Modulo de tarifa");
        System.out.println("6. Modulo de asignatura");
        System.out.println("7. Modulo de curso");
        System.out.println("8. Modulo de profesor");
        System.out.println("9. Modulo de reportes");
        System.out.println("10. Salir");
        
        return leer.nextInt();
    }
}