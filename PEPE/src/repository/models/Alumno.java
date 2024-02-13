package repository.models;

public class Alumno {
    private int id;
    private Persona persona; 
    private Programa programa;

    public Alumno() {}
    public Alumno(int id, Persona persona, Programa programa) {
        this.id = id;
        this.persona = persona;
        this.programa = programa;
    }

    public int getId() {
        return id;
    }

    public Persona getPersona() {
        return persona;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public void imprimir(){
        System.out.println("Nombre: "+this.persona.getNombres());
        System.out.println("Apellidos:"+this.persona.getApellidos());

    }
}
