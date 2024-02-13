package repository.models;

public class Facultad {
    private int id;
    private String nombre;

    public Facultad(int id, String nombre){
        this.id = id;
        this.nombre = nombre;

    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean matches(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matches'");
    }
}
