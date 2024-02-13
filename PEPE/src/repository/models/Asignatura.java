package repository.models;

public class Asignatura {
    private int id;
    private String nombre;
    private int creditos;
    private int cupoDisponible;
    private Curso curso;
    private Programa programa;

    public Asignatura(int id, String nombre,int creditos,int cupoDisponible, Curso curso, Programa programa){
        this.id = id;
        this.nombre = nombre;
        this.creditos = creditos;
        this.cupoDisponible = cupoDisponible;
        this.curso = curso;
        this.programa = programa;
    }

    public Asignatura(String nombre2, String codigo, int creditos2) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCreditos() {
        return creditos;
    }

    public int getCupoDisponible() {
        return cupoDisponible;
    }

    public Curso getCurso() {
        return curso;
    }

    public Programa getPrograma() {
        return programa;
    }
}
