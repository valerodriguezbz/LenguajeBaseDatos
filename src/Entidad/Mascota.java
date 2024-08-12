/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidad;

/**
 *
 * @author kyran
 */
public class Mascota {

    int id_mascota;
    int id_persona;
    String duenio;
    String nombre_mascota;
    int id_especie;
    String especie;
    String raza;
    int edad;
    float peso;
    String genero;
    int esterilizada;

    public Mascota(int id_persona, String nombre_mascota, int id_especie, String raza, int edad, float peso, String genero, int esterilizada) {
        this.id_persona = id_persona;
        this.nombre_mascota = nombre_mascota;
        this.id_especie = id_especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.genero = genero;
        this.esterilizada = esterilizada;
    }

    public Mascota(int id_mascota, int id_persona, String duenio, String nombre_mascota, int id_especie, String especie, String raza, int edad, float peso, String genero, int esterilizada) {
        this.id_mascota = id_mascota;
        this.id_persona = id_persona;
        this.duenio = duenio;
        this.nombre_mascota = nombre_mascota;
        this.id_especie = id_especie;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.peso = peso;
        this.genero = genero;
        this.esterilizada = esterilizada;
    }

    public Mascota() {
    }

    public int getId_mascota() {
        return id_mascota;
    }

    public void setId_mascota(int id_mascota) {
        this.id_mascota = id_mascota;
    }

    public int getId_persona() {
        return id_persona;
    }

    public void setId_persona(int id_persona) {
        this.id_persona = id_persona;
    }

    public String getDuenio() {
        return duenio;
    }

    public void setDuenio(String duenio) {
        this.duenio = duenio;
    }

    public String getNombre_mascota() {
        return nombre_mascota;
    }

    public void setNombre_mascota(String nombre_mascota) {
        this.nombre_mascota = nombre_mascota;
    }

    public int getId_especie() {
        return id_especie;
    }

    public void setId_especie(int id_especie) {
        this.id_especie = id_especie;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getEsterilizada() {
        return esterilizada;
    }

    public void setEsterilizada(int esterilizada) {
        this.esterilizada = esterilizada;
    }

    @Override
    public String toString() {
        return "Mascota{" + "id_mascota=" + id_mascota + ", id_persona=" + id_persona + ", duenio=" + duenio + ", nombre_mascota=" + nombre_mascota + ", id_especie=" + id_especie + ", especie=" + especie + ", raza=" + raza + ", edad=" + edad + ", peso=" + peso + ", genero=" + genero + ", esterilizada=" + esterilizada + '}';
    }
}