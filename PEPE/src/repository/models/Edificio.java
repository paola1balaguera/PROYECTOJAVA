package repository.models;

public class Edificio {
    private int id;
    private String nombre;


public Edificio(int id, String nombre){
    this.id = id;
    this.nombre = nombre;
    }


    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
