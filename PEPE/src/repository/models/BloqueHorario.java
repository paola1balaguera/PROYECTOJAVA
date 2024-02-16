package repository.models;

import java.sql.Time;
import java.time.LocalTime;


public class BloqueHorario {
    private int id;
    private LocalTime horaInicio;
    private LocalTime horaFin;

    public BloqueHorario(int id, LocalTime horaInicio, LocalTime horaFin){
        this.id = id;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public BloqueHorario(int id2, Time horaInicio2, Time horaFin2) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }
}
