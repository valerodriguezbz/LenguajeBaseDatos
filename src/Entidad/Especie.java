package Entidad;

/**
 *
 * @author vrb00
 */
public class Especie {

    private int id_especie;
    private String nombre_especie;

    public Especie(int id_especie, String nombre_especie) {
        this.id_especie = id_especie;
        this.nombre_especie = nombre_especie;
    }

    public Especie(String nombre_especie) {
        this.nombre_especie = nombre_especie;
    }

    public Especie() {
    }

    public int getId_especie() {
        return id_especie;
    }

    public void setId_especie(int id_especie) {
        this.id_especie = id_especie;
    }

    public String getNombre_especie() {
        return nombre_especie;
    }

    public void setNombre_especie(String nombre_especie) {
        this.nombre_especie = nombre_especie;
    }

    @Override
    public String toString() {
        return "Especie{" + "id_especie=" + id_especie + ", nombre_especie=" + nombre_especie + '}';
    }
}