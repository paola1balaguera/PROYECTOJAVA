package views;

import repository.models.Programa;
import utils.connectionBDMySql.connectionBDMySql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    listarProgramas();;
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
    
        System.out.println("¿Es de nivel avanzado? (1 = pregrado / 2 = posgrado):");
        int nivelInt = scanner.nextInt();
        Nivel nivel = (nivelInt == 1) ? Nivel.PREGRADO : Nivel.POSGRADO;
    
        //  se llama al método generarIdPrograma()
        int id = generarIdPrograma();
        Programa programa = new Programa();
        listaProgramas.add(programa);
    
        // Guardar el programa en la base de datos
        connectionBDMySql.guardarPrograma(programa);
    
        System.out.println("Programa creado con éxito.");
    }



    public static void editarPrograma() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del programa a editar: ");
        int idPrograma = leer.nextInt();
        Programa programa = obtenerProgramaPorIdBD(idPrograma);
        if (programa != null) {
            System.out.print("Introduce el nuevo nombre del programa: ");
            String nuevoNombre = leer.next();
            System.out.print("¿Es de nivel avanzado? (true = pregrado / false= posgrado):");
            boolean nuevoNivel = leer.nextBoolean();
    
            // Actualizar el programa en la lista
            programa.setNombre(nuevoNombre);
            programa.setNivel(nuevoNivel);
    
            // Actualizar el programa en la base de datos
            connectionBDMySql.actualizarPrograma(programa);
    
            System.out.println("Programa actualizado con éxito.");
        } else {
            System.out.println("No se encontró ningún programa con el ID proporcionado.");
        }
    }


    public static void eliminarPrograma() {
        Scanner leer = new Scanner(System.in);
        System.out.print("ID del programa a eliminar: ");
        int idPrograma = leer.nextInt();
        Programa programa = obtenerProgramaPorIdBD(idPrograma);
        if (programa != null) {
            listaProgramas.remove(programa);
    
            // Eliminar el programa de la base de datos
            connectionBDMySql.eliminarProgramaBD(idPrograma);
    
            System.out.println("Programa eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún programa con el ID proporcionado.");
        }
    }

    public static void listarProgramas() {

        List<Programa> listaProgramas = connectionBDMySql.obtenerProgramas();

        if (listaProgramas.isEmpty()) {
            System.out.println("No hay programas registrados.");
        } else {
            System.out.println("Lista de Programas:");
            for (Programa programa : listaProgramas) {
                System.out.println(programa);
            }
        }
    }


    public static int generarIdPrograma() {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        int maxId = 0;
    
        try {
            conexion = connectionBDMySql.getInstance();
            String query = "SELECT MAX(id_programa) AS max_id FROM programa";
            statement = conexion.prepareStatement(query);
    
            resultado = statement.executeQuery();
            if (resultado.next()) {
                maxId = resultado.getInt("max_id");
            }
        } catch (SQLException e) {
            System.out.println("Fallo al obtener el ID máximo de la BD");
            e.printStackTrace();
        } finally {
            connectionBDMySql.cerrarRecursos(conexion, statement, resultado);
        }
    
        return maxId + 1;
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

    

    private static Programa obtenerProgramaPorIdBD(int idPrograma) {
        Connection conexion = null;
        PreparedStatement statement = null;
        ResultSet resultado = null;
        Programa programa = null;
    
        try {
            conexion = connectionBDMySql.getInstance();
            String query = "SELECT * FROM programa WHERE id_programa = ?";
            statement = conexion.prepareStatement(query);
            statement.setInt(1, idPrograma);
    
            resultado = statement.executeQuery();
            if (resultado.next()) {
                String nombre = resultado.getString("nombre");
                String nivelStr = resultado.getString("nivel");
                boolean nivel = "pregrado".equals(nivelStr);
                programa = new Programa(idPrograma, nombre, nivel);
            }
        } catch (SQLException e) {
            System.out.println("Fallo al obtener el programa de la BD");
            e.printStackTrace();
        } finally {
            connectionBDMySql.cerrarRecursos(conexion, statement, resultado);
        }
    
        return programa;
    }



}


