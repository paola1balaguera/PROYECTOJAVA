package repository.models;

public class Programa {
    private int id;
    private String nombre;
    private boolean nivel;

    public Programa() {}

    public Programa(int id, String nombre, boolean nivel){
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isNivel() {
        return nivel;
    }

    @Override
    public String toString() {
        return "Programa{" +
                "nombre='" + nombre + '\'' +
                
                '}';
    }
}
