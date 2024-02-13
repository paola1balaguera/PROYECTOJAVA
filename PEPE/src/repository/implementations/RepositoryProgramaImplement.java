package repository.implementations;

import java.util.ArrayList;
import java.util.List;

import repository.models.Programa;
import repository.interfaces.ModelCrudGenericoString;

public class RepositoryProgramaImplement implements ModelCrudGenericoString<Programa> {
    
    private List<Programa> listaProgramas = new ArrayList<>();

    @Override
    public List<Programa> listar() {
        return listaProgramas;
    }

    @Override
    public Programa busqueda(String nombre) {
        for (Programa programa : listaProgramas) {
            if (programa.getNombre() == nombre) {
                return programa;
            }
        }
        return null;
    }

    @Override
    public void crear(Programa programa) {
        listaProgramas.add(programa);
    }

    @Override
    public void editar(Programa programa) {
        for (int i = 0; i < listaProgramas.size(); i++) {
            if (listaProgramas.get(i).getNombre() == programa.getNombre()) {
                listaProgramas.set(i, programa);
                break;
            }
        }
    }

    @Override
    public void eliminar(String nombre) {
        for (Programa programa : listaProgramas) {
            if (programa.getNombre() == nombre) {
                listaProgramas.remove(programa);
                break;
            }
        }
    }
}
