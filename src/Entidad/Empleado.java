/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author fidelitas
 */
public class Empleado {

    int id_empleado;
    String carnet;
    String contrasenna;
    int id_persona;
    String nombre_per;
    int estado;
    int id_servicio;
    String nombre_ser;

    public Empleado(String carnet, String contrasenna, int id_persona, int estado, int id_servicio) {
        this.carnet = carnet;
        this.contrasenna = contrasenna;
        this.id_persona = id_persona;
        this.estado = estado;
        this.id_servicio = id_servicio;
    }

    public Empleado(int id_empleado, String carnet, String contrasenna, int id_persona, int estado, int id_servicio) {
        this.id_empleado = id_empleado;
        this.carnet = carnet;
        this.contrasenna = contrasenna;
        this.id_persona = id_persona;
        this.estado = estado;
        this.id_servicio = id_servicio;
    }

    public Empleado(int id_empleado,  int id_persona, String carnet, String contrasenna, String nombre_per, int estado, int id_servicio, String nombre_ser) {
        this.id_empleado = id_empleado;
        this.carnet = carnet;
        this.contrasenna = contrasenna;
        this.id_persona = id_persona;
        this.nombre_per = nombre_per;
        this.estado = estado;
        this.id_servicio = id_servicio;
        this.nombre_ser = nombre_ser;
    }

    // ESTE LO OCUPO PARA INICIO DE SESION.
    public Empleado(int id_empleado, String carnet, String contrasenna) {
        this.id_empleado = id_empleado;
        this.carnet = carnet;
        this.contrasenna = contrasenna;
    }

    public Empleado() {
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getContrasenna() {
        return contrasenna;
    }

    public void setContrasenna(String contrasenna) {
        this.contrasenna = contrasenna;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }   

    public String getNombre_per() {
        return nombre_per;
    }

    public String getNombre_ser() {
        return nombre_ser;
    }

    @Override
    public String toString() {
        return "Empleado{" + "carnet=" + carnet + ", id_persona=" + id_persona + ", estado=" + estado + ", id_servicio=" + id_servicio + '}';
    }
}