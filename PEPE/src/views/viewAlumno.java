package views;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import repository.models.Alumno;
import repository.models.Ciudad;
import repository.models.Direccion;
import repository.models.Persona;
import utils.connectionBDMySql.connectionBDMySql;


public class viewAlumno extends viewMain {

    public static void startMenu() throws ParseException {

        int op = 0;

        do {

            op = mostrarMenu();
            switch (op) {
                case 1:
                    listarAlumnos();
//                    crearAlumno();
                    break;
//                case 2:
//                    editarAlumno();
//                    break;
//                case 3:
//                    eliminarAlumno();
//                    break;
//                case 4:
//                    listarAlumnos();
//                    break;
//                case 5:
//                    obtenerAlumnoPorId(op);
//                    break;
                case 2:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }

        } while (op >= 1 && op < 2);

    }


//    public static void crearAlumno() throws ParseException {
//        leer.nextLine();
//        System.out.print("Tipo de Documento: ");
//        Persona.TipoDocumento tipo_documento = Persona.TipoDocumento.valueOf(leer.nextLine());
//
//        System.out.print("Documento: ");
//        int documento = Integer.parseInt(leer.nextLine());
//
//        System.out.print("Nombres: ");
//        String nombres = leer.nextLine();
//
//        System.out.print("Apellidos: ");
//        String apellidos = leer.nextLine();
//
//        System.out.print("Dirección: ");
//        String direccionNombre = leer.nextLine();
//        Direccion direccion = connectionBDMySql.buscarDireccionPorNombre(direccionNombre);
//
//        System.out.print("Telefono: ");
//        String telefono = leer.nextLine();
//
//        System.out.print("Fecha de Nacimiento: ");
//        SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
//        Date fecha = formatter.parse(leer.nextLine());
//
//        System.out.print("Ciudad: ");
//        String ciudadNombre = leer.nextLine();
//        Ciudad ciudad = connectionBDMySql.buscarCiudadPorNombre(ciudadNombre);
//
//        Persona persona = new Persona(tipo_documento, documento, nombres, apellidos, direccion, telefono, fecha, Persona.enumSexo.masculino, ciudad);
//        serviceAlumno.crear(persona);
//    }
    

    


    
    
    public static void editarAlumno() throws ParseException {
        leer.nextLine();
        System.out.print("ID del alumno a editar: ");
        int idAlumno = leer.nextInt();
        
        // Aquí deberías obtener el objeto Alumno basado en su ID
        Alumno alumno = obtenerAlumnoPorId(idAlumno);
        
        if (alumno != null) {
            System.out.println("Datos actuales del alumno a editar:");
            System.out.println(alumno);
            
            leer.nextLine(); 
            System.out.print("Nuevos nombres: ");
            String nuevosNombres = leer.nextLine();
            
            System.out.print("Nuevos apellidos: ");
            String nuevosApellidos = leer.nextLine();
            
            System.out.print("Nueva dirección: ");
            String nuevaDireccionNombre = leer.nextLine();
            Direccion nuevaDireccion = connectionBDMySql.buscarDireccionPorNombre(nuevaDireccionNombre);
            
            System.out.print("Nuevo teléfono: ");
            String nuevoTelefono = leer.nextLine();
            
            System.out.print("Nueva fecha de nacimiento (dd-MMM-yyyy): ");
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
            Date nuevaFechaNacimiento = formatter.parse(leer.nextLine());
            
            System.out.print("Nueva ciudad: ");
            String nuevaCiudadNombre = leer.nextLine();
            Ciudad nuevaCiudad = connectionBDMySql.buscarCiudadPorNombre(nuevaCiudadNombre);
            
            
            alumno.getPersona().setNombres(nuevosNombres);
            alumno.getPersona().setApellidos(nuevosApellidos);
            alumno.getPersona().setDireccion(nuevaDireccion);
            alumno.getPersona().setTelefono(nuevoTelefono);
            alumno.getPersona().setFechaNacimiento(nuevaFechaNacimiento);
            alumno.getPersona().setCiudad(nuevaCiudad);

            //agregar para guardar cambios en la bd
            // guardarCambiosAlumno(alumno);
            
            System.out.println("Alumno editado exitosamente:");
            System.out.println(alumno);
        } else {
            System.out.println("No se encontró ningún alumno con el ID proporcionado.");
        }
    }

    public static void eliminarAlumno() {
        leer.nextLine();
        System.out.print("ID del alumno a eliminar: ");
        int idAlumno = leer.nextInt();
    
        
        Alumno alumno = obtenerAlumnoPorId(idAlumno);
    
        if (alumno != null) {
            System.out.println("¿Estás seguro de que deseas eliminar este alumno? (S/N)");
            String respuesta = leer.next();
    
            if (respuesta.equalsIgnoreCase("S")) {
                // çlógica para eliminar al alumno de la base de datos 
                // eliminarAlumnoDeBaseDeDatos(alumno);
    
                System.out.println("Alumno eliminado exitosamente.");
            } else {
                System.out.println("Operación cancelada. El alumno no ha sido eliminado.");
            }
        } else {
            System.out.println("No se encontró ningún alumno con el ID proporcionado.");
        }
    }

    /* public static void listarAlumnos() {
        if (listaAlumnos.isEmpty()) {
            System.out.println("No hay alumnos registrados.");
        } else {
            System.out.println("Lista de Alumnos:");
            for (Alumno alumno : listaAlumnos) {
                System.out.println(alumno);

                aqui el error esta en que me faltan crear las listas apra almacenar
                todos mis modulos

                Igual creo que puedes crear el arrayList aqui mismo
            }
        }
    } */

    public static void listarAlumnos() {
        System.out.println("Lista de Alumnos");
        for (Alumno alumno : serviceAlumno.listar()) {
            alumno.imprimir();
            System.out.println();
        }
    }


    public static Alumno obtenerAlumnoPorId(int idAlumno) {
        for (Alumno alumno : serviceAlumno.listar()) {
            if (alumno.getId() == idAlumno) {
                return alumno;
            }
        }
        return null; 
    }
    

    public static int mostrarMenu() {
        System.out.println("----Menu--Alumnos----");
        System.out.println("1. Listar alumnos.");
        System.out.println("2. Salir ");
        return leer.nextInt();
    }

}
