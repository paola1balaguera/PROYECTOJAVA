package services.implementations;

import exceptions.NullExceptions;
import repository.interfaces.ModelCrudGenerico;
import repository.models.Persona;
import services.ServiceCrudGenerico;

import java.util.List;


public class ServicePersonaImplement implements ServiceCrudGenerico<Persona> {

    //modelCrudGenerico es la interfaz que se declara en Repository
    private final ModelCrudGenerico crudRepositoryPersona;

    public ServicePersonaImplement(ModelCrudGenerico crudRepositoryPersona){
        this.crudRepositoryPersona = crudRepositoryPersona;
    }

    @Override
    public List<Persona> listar(){
        return this.crudRepositoryPersona.listar();
    }

    @Override
    public Persona busqueda(int id) throws NullExceptions{

        Persona persona = (Persona) this.crudRepositoryPersona.busqueda(id);
        if(persona!= null){
            return persona;
        }
        else{
            throw new NullExceptions("No se encontro Persona");
        }   
    }

    @SuppressWarnings("unchecked")
    @Override
    public void crear(Persona persona){
        this.crudRepositoryPersona.crear(persona);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void editar(Persona persona){
        this.crudRepositoryPersona.editar(persona);
    }

    @Override
    public void eliminar(int id){
        this.crudRepositoryPersona.eliminar(id);
    }

}
