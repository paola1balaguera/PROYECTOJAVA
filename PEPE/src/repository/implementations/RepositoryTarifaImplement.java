package repository.implementations;

import java.util.List;

import repository.models.*;
import repository.interfaces.*;

public class RepositoryTarifaImplement implements ModelCrudGenerico<Tarifa> {
    
    private List<Tarifa> listaTarifas;

    @Override
    public List<Tarifa> listar() {
        return listaTarifas;
    }

    @Override
    public Tarifa busqueda(int id) {
        for (Tarifa tarifa : listaTarifas) {
            if (tarifa.getId() == id) {
                return tarifa;
            }
        }
        return null;
    }

    @Override
    public void crear(Tarifa tarifa) {
        listaTarifas.add(tarifa);
    }

    @Override
    public void editar(Tarifa tarifa) {
        for (int i = 0; i < listaTarifas.size(); i++) {
            if (listaTarifas.get(i).getId() == tarifa.getId()) {
                listaTarifas.set(i, tarifa);
                break;
            }
        }
    }

    @Override
    public void eliminar(int id) {
        for (Tarifa tarifa : listaTarifas) {
            if (tarifa.getId() == id) {
                listaTarifas.remove(tarifa);
                break;
            }
        }
    }
}
