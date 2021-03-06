package controlador;
// Generated 05-feb-2019 13:05:42 by Hibernate Tools 4.0.1.Final

/**
 * Empleado generated by hbm2java
 */
public class Empleado implements java.io.Serializable {

    private int idEmpleado;
    private Puesto puesto;
    private String nombre;
    private String ape1;
    private String ape2;
    private long minutos;

    public Empleado(String ape1, String nombre, Puesto puesto) {
        this.puesto = puesto;
        this.nombre = nombre;
        this.ape1 = ape1;
    }

    public Empleado() {
    }

    public Empleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Empleado(int idEmpleado, Puesto puesto, String nombre, String ape1, String ape2, long minutos) {
        this.idEmpleado = idEmpleado;
        this.puesto = puesto;
        this.nombre = nombre;
        this.ape1 = ape1;
        this.ape2 = ape2;
        this.minutos = minutos;
    }

    public int getIdEmpleado() {
        return this.idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Puesto getPuesto() {
        return this.puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApe1() {
        return this.ape1;
    }

    public void setApe1(String ape1) {
        this.ape1 = ape1;
    }

    public String getApe2() {
        return this.ape2;
    }

    public void setApe2(String ape2) {
        this.ape2 = ape2;
    }

    public long getMinutos() {
        return this.minutos;
    }

    public void setMinutos(long minutos) {
        this.minutos = minutos;
    }

    @Override
    public String toString() {
        return "Empleado{" + "idEmpleado=" + idEmpleado + ", puesto=" + puesto + ", nombre=" + nombre + ", ape1=" + ape1 + ", ape2=" + ape2 + ", minutos=" + minutos + '}';
    }

}
