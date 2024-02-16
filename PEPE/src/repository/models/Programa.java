package repository.models;

import views.Nivel;

public class Programa {
    private int id;
    private String nombre;
    public boolean nivel;

    public Programa() {}

    public Programa(int id, String nombre, boolean nivel2){
        this.id = id;
        this.nombre = nombre;
        this.nivel = nivel2;
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

    public void setId(int id) {
        this.id = id;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setNivel(boolean nivel) {
        this.nivel = nivel;
    }








    @Override
    public String toString() {
        return "Programa{" +
                "id=" + id + '\'' +
                "nombre=" + nombre + '\'' +
                "nivel=" + nivel + '\'' +
            '}';
    }



}
