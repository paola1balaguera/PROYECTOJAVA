package repository.models;

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
}
