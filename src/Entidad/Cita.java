package Entidad;

import java.util.Date;

public class Cita {

    private int id_cita;
    private Date fecha;
    private String hora;
    private int id_mascota;
    private int id_servicio;
    private String estado;
    private String nombre_mascota;
    private String nombre_Servicio;
    private String nombre_persona;

    public Cita(Date fecha, String hora, int id_mascota, int id_servicio, String estado) {
        this.fecha = fecha;
        this.hora = hora;
        this.id_mascota = id_mascota;
        this.id_servicio = id_servicio;
        this.estado=estado;
    }

    public Cita(int id_cita, Date fecha, String hora, int id_mascota, int id_servicio, String estado) {
        this.id_cita = id_cita;
        this.fecha = fecha;
        this.hora = hora;
        this.id_mascota = id_mascota;
        this.id_servicio = id_servicio;
        this.estado=estado;
    }

    public Cita(int id_cita, Date fecha, String hora, int id_mascota, int id_servicio, String nombre_mascota, String nombre_Servicio, String nombre_persona, String estado) {
        this.id_cita = id_cita;
        this.fecha = fecha;
        this.hora = hora;
        this.id_mascota = id_mascota;
        this.id_servicio = id_servicio;
        this.nombre_mascota = nombre_mascota;
        this.nombre_Servicio = nombre_Servicio;
        this.nombre_persona = nombre_persona;
        this.estado=estado;
    }

    public Cita() {
    }

    public int getId_cita() {
        return id_cita;
    }

    public void setId_cita(int id_cita) {
        this.id_cita = id_cita;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_servicio() {
        return id_servicio;
    }

    public void setId_servicio(int id_servicio) {
        this.id_servicio = id_servicio;
    }

    public String getNombre_mascota() {
        return nombre_mascota;
    }

    public String getNombre_Servicio() {
        return nombre_Servicio;
    }

    public String getNombre_persona() {
        return nombre_persona;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cita{" + "id_cita=" + id_cita + ", fecha=" + fecha + ", hora=" + hora + ", id_mascota=" + id_mascota + ", id_servicio=" + id_servicio + ", nombre_mascota=" + nombre_mascota + ", nombre_Servicio=" + nombre_Servicio + ", nombre_persona=" + nombre_persona + '}';
    }
}