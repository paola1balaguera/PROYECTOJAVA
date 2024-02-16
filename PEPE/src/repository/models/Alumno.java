package repository.models;

public class Alumno {
    private int id;
    private Persona persona; 
    private Programa programa;

    public Alumno(int id2, String nombre, String apellido) {}
    public Alumno(int id, Persona nombre, Programa apellido) {
        this.id = id;
        this.persona = nombre;
        this.programa = apellido;
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
