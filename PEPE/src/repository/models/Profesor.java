package repository.models;

public class Profesor {
    private int id;
    private Persona persona;
    private Facultad departamento;

    public Profesor(int id, Persona persona, Facultad departamento) {
        this.id = id;
        this.persona = persona;
    }

    public int getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public Facultad getDepartamento() {
        return departamento;
    }
}
