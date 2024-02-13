package services;

import java.util.List;

import exceptions.NullExceptions;
import exceptions.TipoDatoErroneo;

public interface ServiceCrudGenerico <T>{
    List<T> listar();

    T busqueda(int id) throws NullExceptions, TipoDatoErroneo;

    void crear(T ejemplo);

    void editar(T ejemplo);

    void eliminar(int id);

}
