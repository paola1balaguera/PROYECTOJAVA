package repository.implementations;

import java.util.ArrayList;
import java.util.List;

import repository.models.Periodo;
import repository.interfaces.ModelCrudGenerico;

public class RepositoryPeriodoImplement implements ModelCrudGenerico<Periodo> {
    
    private List<Periodo> listaPeriodos = new ArrayList<>();

    @Override
    public List<Periodo> listar() {
        return listaPeriodos;
    }

    @Override
    public Periodo busqueda(int id) {
        for (Periodo periodo : listaPeriodos) {
            if (periodo.getId() == id) {
                return periodo;
            }
        }
        return null;
    }

    @Override
    public void crear(Periodo periodo) {
        listaPeriodos.add(periodo);
    }

    @Override
    public void editar(Periodo periodo) {
        for (int i = 0; i < listaPeriodos.size(); i++) {
            if (listaPeriodos.get(i).getId() == periodo.getId()) {
                listaPeriodos.set(i, periodo);
                break;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        for (Periodo periodo : listaPeriodos) {
            if (periodo.getId() == id) {
                listaPeriodos.remove(periodo);
                break;
            }
        }
    }
}
