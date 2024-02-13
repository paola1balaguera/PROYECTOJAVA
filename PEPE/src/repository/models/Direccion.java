package repository.models;

public class Direccion {
    private int id;
    private String barrio;
    private String calle;
    private String numero_casa;

    public Direccion(String barrio,String calle,String numero_casa){
        this.barrio = barrio;
        this.calle = calle;
        this.numero_casa = numero_casa;
    }

    public Direccion(int int1, String string, String string2, String string3) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumeroCasa(String numero_casa) {
        this.numero_casa = numero_casa;
    }
}
