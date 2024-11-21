package entidades;

public class ParametroSistema {
    
    private Integer idParametroSistema;
    private String nombre;
    private String descripcion;
    private String valor;

    public ParametroSistema() {
    }

    public Integer getIdParametroSistema() {
        return idParametroSistema;
    }

    public void setIdParametroSistema(Integer idParametroSistema) {
        this.idParametroSistema = idParametroSistema;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

}
