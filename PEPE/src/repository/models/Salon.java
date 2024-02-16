package repository.models;

public class Salon {
    private int id;
    private int capacidadAlumnos;
    private int piso;
    private String identificador;
    private Edificio edificio;

    public Salon(int id,int capacidadAlumnos, int piso, String identificador, Edificio edificio ){
        this.id = id; 
        this.capacidadAlumnos = capacidadAlumnos;
        this.piso = piso;
        this.identificador = identificador;
        this.edificio = edificio;
    }

    public boolean matches(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matches'");
    }

    public int getId() {
        return id;
    }

    public int getCapacidadAlumnos() {
        return capacidadAlumnos;
    }

    public int getPiso() {
        return piso;
    }

    public String getIdentificador() {
        return identificador;
    }

    public Edificio getEdificio() {
        return edificio;
    }

    public void setCapacidadAlumnos(int capacidadAlumnos2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCapacidadAlumnos'");
    }

    public void setIdentificador(String identificador2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setIdentificador'");
    }

    public void setPiso(int piso2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPiso'");
    }
}
