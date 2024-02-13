package repository.implementations;

import java.util.List;

import repository.models.*;
import repository.interfaces.*;

public class RepositoryHorarioImplement implements ModelHorario {
    
    private List<Horario> listaHorarios;

    @Override
    public List<Horario> listar() {
        return listaHorarios;
    }

   @Override
   public Horario busquedaPorAlumno(int id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'busquedaPorAlumno'");
   }

    /* @Override
    public Horario busquedaPorAlumno(int id) {
        for (Horario horario : listaHorarios) {
            if (horario.getIdAlumno() == id) {
                return horario;
            }
        }
        return null;
    } */
}

