/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

import java.util.Date;

/**
 *
 * @author Derek
 */
public class Hospitalizacion {
    int id_hospitalizacion;
    int id_mascota;
    String fecha_entrada;
    String descripcion;
    float precio;

    public Hospitalizacion() {
    }

    public Hospitalizacion(int id_hospitalizacion, int id_mascota, String fecha_entrada, String descripcion, float precio) {
        this.id_hospitalizacion = id_hospitalizacion;
        this.id_mascota = id_mascota;
        this.fecha_entrada = fecha_entrada;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public int getId_hospitalizacion() {
        return id_hospitalizacion;
    }

    public void setId_hospitalizacion(int id_hospitalizacion) {
        this.id_hospitalizacion = id_hospitalizacion;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public String getFecha_entrada() {
        return fecha_entrada;
    }

    public void setFecha_entrada(String fecha_entrada) {
        this.fecha_entrada = fecha_entrada;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Hospitalizacion{" + "id_hospitalizacion=" + id_hospitalizacion + ", id_mascota=" + id_mascota + ", fecha_entrada=" + fecha_entrada + ", descripcion=" + descripcion + ", precio=" + precio + '}';
    }
    
    
}
