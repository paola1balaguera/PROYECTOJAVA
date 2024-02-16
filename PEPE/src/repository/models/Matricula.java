package repository.models;

public class Matricula {
    private int id;
    private Alumno alumno;
    private AsignaturaPeriodo asignaturaPeriodo;
    //public enum EstadoEnum{aprobada,reprobada,en_curso};
    private EstadoEnum estado;

    public Matricula(int id, Alumno alumno,AsignaturaPeriodo asignaturaPeriodo, EstadoEnum estado){
        this.id = id;
        this.alumno = alumno;
        this.asignaturaPeriodo = asignaturaPeriodo;
        this.estado = estado;
    
    }

    public int getId() {
        return id;
    }
    
    public Alumno getAlumno() {
        return alumno;
    }
    
    public AsignaturaPeriodo getAsignaturaPeriodo() {
        return asignaturaPeriodo;
    }
    
    public EstadoEnum getEstado() {
        return estado;
    }
    

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", alumno=" + alumno +
                ", asignaturaPeriodo=" + asignaturaPeriodo +
                ", estado=" + estado +
                '}';
    }

    public enum EstadoEnum {
            APROBADO,
            REPROBADO,
            EN_CURSO  // Agrega este valor
        }

    public Programa getPrograma() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrograma'");
    }

    public Alumno getEstudiante() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEstudiante'");
    }

    public Object getAsignatura() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAsignatura'");
    }

    public void setAsignaturaPeriodo(AsignaturaPeriodo asignatura) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setAsignaturaPeriodo'");
    }

    public void setEstadoEnum(EstadoEnum estado2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEstadoEnum'");
    }
}
