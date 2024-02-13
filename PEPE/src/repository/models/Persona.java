package repository.models;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import repository.models.*;

public class Persona {
    private int id;
    private TipoDocumento tipo_documento;
    private long documento;
    private String nombres;
    private String apellidos;
    private Direccion direccion;
    private String telefono;
    private Date fecha_nacimiento;
    private Ciudad ciudad;
    private enumSexo sexo;
    public Persona(){}

    public Persona(TipoDocumento tipo_documento, int documento, String nombres,
                   String apellidos, Direccion direccion2, String telefono, Date fecha_nacimiento,
                   enumSexo sexo, Ciudad ciudad2) {
        this.tipo_documento = tipo_documento;
        this.documento = documento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion2;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.sexo = sexo;
        this.ciudad = ciudad2;
    }

    public enum TipoDocumento{
        CC, 
        TI,
        CE
    }

    public enum enumSexo{
        masculino,
        femenino,
        no_definido
    }

    public int getId() {
        return id;
    }

    public TipoDocumento getTipo_documento() {
        return tipo_documento;
    }

    public long getDocumento() {
        return documento;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFecha_nacimiento() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
        String strDate = dateFormat.format(fecha_nacimiento);  
        return strDate;
    }


    public Ciudad getCiudad() {
        return ciudad;
    }

    public int getCiudadId(){
        return ciudad.getId();
    }

    public int getDireccionId(){
        return direccion.getId();
    }

    public enumSexo getSexo() {
        return sexo;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipoDocumento(TipoDocumento tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }


    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFechaNacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setSexo(enumSexo sexo) {
        this.sexo = sexo;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

}
