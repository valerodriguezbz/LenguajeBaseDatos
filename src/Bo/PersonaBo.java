/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bo;

import Entidad.Persona;
import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author fidelitas
 */
public class PersonaBo {

    private String mensaje = "";

    public String agregarPersona(Persona per) throws SQLException {

        String sql = "{CALL PKG_PERSONA.insertar_persona(?, ?, ?, ?, ?, ?)}";

        Connection con = Conexion.Conectar();

        PreparedStatement pstmt = con.prepareCall(sql);

        try {

            pstmt.setString(1, per.getNombre());
            pstmt.setString(2, per.getPrimer_apellido());
            pstmt.setString(3, per.getSegundo_apellido());
            pstmt.setString(4, per.getTelefono());
            pstmt.setString(5, per.getCorreo());
            pstmt.setInt(6, per.getId_rol());

            mensaje = "Persona agregada de manera correcta";
            pstmt.execute();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            mensaje = "No se pudo agregar la persona \n" + e.getMessage();

        }

        return mensaje;
    }

    public ArrayList buscarPersonaId(int idConsulta) {

        ArrayList<Persona> personas = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_PERSONA.buscar_persona_id(?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            // Establecer el parámetro de entrada del procedimiento
            stmt.setInt(1, idConsulta);

            // Establecer el parámetro de salida del procedimiento
            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            // Ejecutar el procedimiento
            stmt.execute();

            // Obtener el cursor como un ResultSet
            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    int id_p = rs.getInt("ID");
                    String nombre = rs.getString("Nombre");
                    String primerApellido = rs.getString("Primer_Apellido");
                    String segundoApellido = rs.getString("Segundo_Apellido");
                    String telefono = rs.getString("Telefono");
                    String correo = rs.getString("Correo");
                    int idRol = rs.getInt("Rol");

                    // Agregar los datos a la lista de personas
                    personas.add(new Persona(id_p, nombre, primerApellido, segundoApellido, telefono, correo, idRol));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener el id de la persona: " + e);
        }

        return personas;
    }

    public ArrayList buscarPersonaNombre(String p_nombre) {
        ArrayList<Persona> personas = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_PERSONA.buscar_persona_nombre(?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            // Establecer el parámetro de entrada del procedimiento
            stmt.setString(1, p_nombre);

            // Establecer el parámetro de salida del procedimiento
            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            // Ejecutar el procedimiento
            stmt.execute();

            // Obtener el cursor como un ResultSet
            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    int id_p = rs.getInt("ID");
                    String nombre = rs.getString("Nombre");
                    String primerApellido = rs.getString("Primer_Apellido");
                    String segundoApellido = rs.getString("Segundo_Apellido");
                    String telefono = rs.getString("Telefono");
                    String correo = rs.getString("Correo");
                    int idRol = rs.getInt("Rol");

                    // Agregar los datos a la lista de personas
                    personas.add(new Persona(id_p, nombre, primerApellido, segundoApellido, telefono, correo, idRol));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener el nombre de la persona: " + e);
        }

        return personas;
    }

    public String actualizarPersona(Persona per) throws SQLException {

        String sql = "{CALL PKG_PERSONA.actualizar_persona(?, ?, ?, ?, ?, ?, ?)}";
        Connection con = Conexion.Conectar();
        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, per.getId_persona());
            pstmt.setString(2, per.getNombre());
            pstmt.setString(3, per.getPrimer_apellido());
            pstmt.setString(4, per.getSegundo_apellido());
            pstmt.setString(5, per.getTelefono());
            pstmt.setString(6, per.getCorreo());
            pstmt.setInt(7, per.getId_rol());

            mensaje = "Persona actualizada exitosamente";
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            mensaje = "No se pudo realizar la actualizacion de la Persona: \n" + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarPersona(int id_persona) throws SQLException {
        Connection con = Conexion.Conectar();
        String procedureCall = "{ call PKG_PERSONA.eliminar_persona(?) }";

        try (CallableStatement cs = con.prepareCall(procedureCall)) {
            cs.setInt(1, id_persona);
            cs.execute();
            mensaje = "Persona eliminada exitosamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Ocurrio un error.";
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                mensaje = mensaje + " Ocurrio un error al cerrar la conexion" + e.getMessage();
            }
        }
        return mensaje;
    }
}
