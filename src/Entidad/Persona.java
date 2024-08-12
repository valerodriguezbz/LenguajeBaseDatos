/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author fidelitas
 */
public class Persona {

    int id_persona;
    String nombre;
    String primer_apellido;
    String segundo_apellido;
    String telefono;
    String correo;
    int id_rol;

    public Persona() {
    }

    public Persona(String nombre, String primer_apellido, String segundo_apellido, String telefono, String correo, int id_rol) {
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.id_rol = id_rol;
    }

    public Persona(int id_persona, String nombre, String primer_apellido, String segundo_apellido, String telefono, String correo, int id_rol) {
        this.id_persona = id_persona;
        this.nombre = nombre;
        this.primer_apellido = primer_apellido;
        this.segundo_apellido = segundo_apellido;
        this.telefono = telefono;
        this.correo = correo;
        this.id_rol = id_rol;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimer_apellido() {
        return primer_apellido;
    }

    public void setPrimer_apellido(String primer_apellido) {
        this.primer_apellido = primer_apellido;
    }

    public String getSegundo_apellido() {
        return segundo_apellido;
    }

    public void setSegundo_apellido(String segundo_apellido) {
        this.segundo_apellido = segundo_apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getId_rol() {
        return id_rol;
    }

    public void setId_rol(int id_rol) {
        this.id_rol = id_rol;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }
    
    @Override
    public String toString() {
        return "Persona{" + "nombre=" + nombre + ", primer_apellido=" + primer_apellido + ", segundo_apellido=" + segundo_apellido + ", telefono=" + telefono + ", correo=" + correo + ", id_rol=" + id_rol + '}';
    }
}
