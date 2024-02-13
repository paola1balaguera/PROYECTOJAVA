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

    public static void main(String[] args) throws ParseException {
        int op = 0;

        do {
            op = menuMain();
            switch (op) {
                case 1:
                    viewAlumno.startMenu();
                    break;
                case 2:
                    viewMatricula.startMenu();
                case 3:
                    ViewPrograma.startMenu();
            
                default:
                    System.out.println("Fin");
                    break;
            }
        } while(op >= 1 && op < 2);
    }

    public static int menuMain() {
        System.out.println("---Gestor de Alumnos-----");
        System.out.println("1. Modulo de Alumnos");
        System.out.println("2. Modulo de matriculas");
        System.out.println("3. Modulo de programa");
        System.out.println("4. Salir:");
        return leer.nextInt();
    }
}