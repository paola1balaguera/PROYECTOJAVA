package repository.models;

public class Tarifa {
    private int id;
    private double valorCredito;
    private Periodo periodo;
    private Programa programa;
    private String tipo;

    public Tarifa(int id,double costoTotal,Programa programa,double valorCredito,Periodo periodo){
        this.id = id;
        this.programa = programa;
        this.valorCredito = valorCredito;
        this.periodo = periodo;
    }

    public Tarifa(int id2, double valorCredito2, Periodo periodo2, Programa programa2) {
        //TODO Auto-generated constructor stub
    }

    public Tarifa(int int1, String string, double double1) {
        //TODO Auto-generated constructor stub
    }

    public int getId() {
        return id;
    }

    public double getValorCredito() {
        return valorCredito;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setValorCredito(double valorCredito2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setValorCredito'");
    }

    public void setPeriodo(Periodo periodo2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPeriodo'");
    }

    public void setPrograma(Programa programa2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrograma'");
    }

    public String getNombre() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNombre'");
    }

    public double getMonto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getMonto'");
    }

    public String getTipo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getTipo'");
    }
}
