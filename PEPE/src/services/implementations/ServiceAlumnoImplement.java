package services.implementations;

import exceptions.NullExceptions;
import services.ServiceCrudGenerico;
import repository.models.*;
import repository.interfaces.*;
import java.util.List;



public class ServiceAlumnoImplement implements ServiceCrudGenerico<Alumno> {

    //modelCrudGenerico es la interfaz que se declara en Repository
    private final ModelCrudGenerico crudRepositoryAlmuno;

    public ServiceAlumnoImplement(ModelCrudGenerico crudRepositoryAlmuno){
        this.crudRepositoryAlmuno = crudRepositoryAlmuno;
    }

    @Override
    public List<Alumno> listar(){
        return this.crudRepositoryAlmuno.listar();
    }

    @Override
    public Alumno busqueda(int id) throws NullExceptions{

        Alumno alumno = (Alumno) this.crudRepositoryAlmuno.busqueda(id);
        if(alumno!= null){
            return alumno;
        }
        else{
            throw new NullExceptions("No se encontro Alumno");
        }   
    }

    @SuppressWarnings("unchecked")
    @Override
    public void crear(Alumno alumno){
        this.crudRepositoryAlmuno.crear(alumno);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void editar(Alumno alumno){
        this.crudRepositoryAlmuno.editar(alumno);
    }

    @Override
    public void eliminar(int id){
        this.crudRepositoryAlmuno.eliminar(id);
    }

}
