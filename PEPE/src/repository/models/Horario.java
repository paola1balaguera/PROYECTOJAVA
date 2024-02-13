package repository.models;

import java.util.Date;


public class Horario {
    private int id;
    private Date dia;
    private Salon salon;
    private BloqueHorario bloquehorario;
    private AsignaturaPeriodo asignaturaPeriodo;

    public Horario(int id, Date dia, Salon salon, BloqueHorario bloquehorario, AsignaturaPeriodo asignaturaperiodo){
        this.id = id;
        this.dia = dia;
        this.salon = salon;
        this.bloquehorario = bloquehorario;
        this.asignaturaPeriodo = asignaturaperiodo;
    }

    public boolean matches(String string) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'matches'");
    }

    public int getId() {
        return id;
    }

    public Date getDia() {
        return dia;
    }

    public Salon getSalon() {
        return salon;
    }

    public BloqueHorario getBloqueHorario() {
        return bloquehorario;
    }

    public AsignaturaPeriodo getAsignaturaPeriodo() {
        return asignaturaPeriodo;
    }
}
