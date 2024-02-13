package repository.implementations;

import java.util.ArrayList;
import java.util.List;
import repository.models.Alumno;
import repository.interfaces.ModelCrudGenerico;
import services.implementations.ServiceAlumnoImplement;

public class RepositoryAlumnoImplement implements ModelCrudGenerico<Alumno> {
    
    private ServiceAlumnoImplement serviceAlumno; 

    
    private List<Alumno> listaAlumnos = new ArrayList<>();

    @Override
    public List<Alumno> listar() {
        return listaAlumnos;
    }

    @Override
    public Alumno busqueda(int id) {
        
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getId() == id) {
                return alumno;
            }
        }
        return null; 
    }

    @Override
    public void crear(Alumno alumno) {
        
        listaAlumnos.add(alumno);
    }

    @Override
    public void editar(Alumno alumno) {
        
        for (int i = 0; i < listaAlumnos.size(); i++) {
            if (listaAlumnos.get(i).getId() == alumno.getId()) {
                listaAlumnos.set(i, alumno);
                break;
            }
        }
    }

    @Override
    public void eliminar(int id) {
      
        for (Alumno alumno : listaAlumnos) {
            if (alumno.getId() == id) {
                listaAlumnos.remove(alumno);
                break;
            }
        }
    }
}
