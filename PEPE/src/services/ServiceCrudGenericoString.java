package services;

import java.util.List;

import exceptions.NullExceptions;
import exceptions.TipoDatoErroneo;
import repository.models.Asignatura;
import repository.models.Facultad;
import repository.models.Periodo;
import repository.models.Salon;

public interface ServiceCrudGenericoString <T>{
    List<T> listar();

    Salon busqueda(String nombre) throws NullExceptions, TipoDatoErroneo;

    void crear(T ejemplo);

    void editar(T ejemplo);

    void eliminar(String nombre);
}
