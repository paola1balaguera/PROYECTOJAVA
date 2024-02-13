package repository.interfaces;

import java.util.List;
import repository.models.*;

public interface ModelHorario {
    
    List<Horario> listar();

    Horario busquedaPorAlumno(int id);
    
}
