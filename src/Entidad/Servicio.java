package Entidad;

/**
 *
 * @author vrb00
 */
public class Servicio {

    private int id_servicio;
    private String nombre_servicio;
    private int precio;

    public Servicio(int id_servicio, String nombre_servicio, int precio) {
        this.id_servicio = id_servicio;
        this.nombre_servicio = nombre_servicio;
        this.precio = precio;
    }

    public Servicio(String nombre_servicio, int precio) {
        this.nombre_servicio = nombre_servicio;
        this.precio = precio;
    }

    public Servicio() {
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Servicio{" + "id_servicio=" + id_servicio + ", nombre_servicio=" + nombre_servicio + ", precio=" + precio + '}';
    }
}