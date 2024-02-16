package repository.models;

public class AsignaturaPeriodo {
    private int id;
    private int codigo;
    private Profesor profesor;
    private Periodo periodo;
    private Asignatura asignatura;


    public AsignaturaPeriodo(int id, int codigo, Profesor profesor,Periodo periodo,Asignatura asignatura ){
        this.id = id;
        this.codigo = codigo;
        this.profesor = profesor;
        this.periodo = periodo;
        this.asignatura = asignatura;
    }


    public AsignaturaPeriodo(int id2, Alumno asignatura2, AsignaturaPeriodo periodo2) {
        //TODO Auto-generated constructor stub
    }


    public int getId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getId'");
    }

}
