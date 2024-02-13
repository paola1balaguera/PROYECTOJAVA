package services;

import java.util.List;

import repository.models.*;

public interface ServiceHorario {

    List<Horario> listar();

    Horario busquedaPorAlumno(int id);
    
}
