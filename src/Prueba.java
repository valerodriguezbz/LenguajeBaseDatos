
import Bo.PersonaBo;
import Entidad.Persona;
import java.sql.SQLException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author fidelitas
 */
public class Prueba {

    Persona per = new Persona();
    PersonaBo perBo = new PersonaBo();
    String mensaje = "";

    public void insertar() throws SQLException {
        per.setNombre("Derek");
        per.setPrimer_apellido("Castillo");
        per.setSegundo_apellido("Quiros");
        per.setTelefono("1111111");
        per.setCorreo("aaa@gmail.com");
        per.setId_rol(2);
        mensaje = perBo.agregarPersona(per);
    }

    public void eliminar() throws SQLException {

        mensaje = perBo.eliminarPersona(3);
        System.out.println(mensaje);

    }

    public static void main(String[] args) throws SQLException {
        Prueba prue = new Prueba();
        prue.insertar();
//        prue.eliminar();
    }
}
