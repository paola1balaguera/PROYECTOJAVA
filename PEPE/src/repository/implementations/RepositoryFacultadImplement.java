package repository.implementations;

import java.util.ArrayList;
import java.util.List;

import repository.models.Facultad;

import repository.interfaces.ModelCrudGenericoString;

public class RepositoryFacultadImplement implements ModelCrudGenericoString<Facultad> {
    
    private List<Facultad> listaFacultades = new ArrayList<>();

    @Override
    public List<Facultad> listar() {
        return listaFacultades;
    }

    @Override
    public Facultad busqueda(String nombre) {
        for (Facultad facultad : listaFacultades) {
            if (facultad.getNombre() == nombre) {
                return facultad;
            }
        }
        return null;
    }

    @Override
    public void crear(Facultad facultad) {
        listaFacultades.add(facultad);
    }

    @Override
    public void editar(Facultad facultad) {
        for (int i = 0; i < listaFacultades.size(); i++) {
            if (listaFacultades.get(i).getNombre() == facultad.getNombre()) {
                listaFacultades.set(i, facultad);
                break;
            }
        }
    }

    @Override
    public void eliminar(String nombre) {
        for (Facultad facultad : listaFacultades) {
            if (facultad.getNombre() == nombre) {
                listaFacultades.remove(facultad);
                break;
            }
        }
    }
}
