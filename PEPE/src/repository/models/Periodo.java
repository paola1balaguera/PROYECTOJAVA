package repository.models;
import java.util.Date;

public class Periodo {
    private int id;
    private int codigo;
    private Date año;
    private int semestre;

    public Periodo(int id, int codigo, Date año, int semestre){
        this.id = id;
        this.codigo = codigo;
        this.año = año;
        this.semestre = semestre;
    }

    public int getId() {
        return id;
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getAño() {
        return año;
    }

    public int getSemestre() {
        return semestre;
    }
}
