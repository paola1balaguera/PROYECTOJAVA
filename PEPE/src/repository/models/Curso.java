package repository.models;

public class Curso {
    private int id;
    private String nombre;
    private String temas;
    private String competencias;


    public Curso(int id, String nombre, String temas, String competencias){
        this.id = id;
        this.nombre = nombre;
        this.temas = temas;
        this.competencias = competencias;
        
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTemas() {
        return temas;
    }

    public String getCompetencias() {
        return competencias;
    }

    public void setTemas(String temas2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setTemas'");
    }

    public void setNombre(String nombre2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setNombre'");
    }

    public void setCompetencias(String competencias2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCompetencias'");
    }
}
