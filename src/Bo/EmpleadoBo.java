/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bo;

import Entidad.Empleado;
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
public class EmpleadoBo {

    private String mensaje = "";

    public String agregarEmpleado(Empleado emp) throws SQLException {

        String sql = "{CALL PKG_EMPLEADO.insertar_empleado(?, ?, ?, ?, ?)}";

        Connection con = Conexion.Conectar();

        PreparedStatement pstmt = con.prepareCall(sql);

        try {

            pstmt.setString(1, emp.getCarnet());
            pstmt.setString(2, emp.getContrasenna());
            pstmt.setInt(3, emp.getId_persona());
            pstmt.setInt(4, emp.getEstado());
            pstmt.setInt(5, emp.getId_servicio());

            mensaje = "Empleado agregado de manera correcta";
            pstmt.execute();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            mensaje = "No se pudo agregar al empleado \n" + e.getMessage();

        }

        return mensaje;
    }

    public ArrayList buscarEmpleadoCarnet(String carnetC) {

        ArrayList<Empleado> empleados = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callFunction = "{ ? = call PKG_EMPLEADO.buscar_empleado_carnet(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.setString(2, carnetC);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            if (rs != null && rs.next()) {
                do {
                    int id_em = rs.getInt("ID");
                    int id_per = rs.getInt("IDPersona");
                    String carnet = rs.getString("Carnet");
                    String nombre_per = rs.getString("Nombre");
                    String cont = rs.getString("Pass");
                    int estado = rs.getInt("Estado");
                    int servicio = rs.getInt("IDServicio");
                    String nom_servicio = rs.getString("Servicio");

                    empleados.add(new Empleado(id_em, id_per, carnet, cont, nombre_per, estado, servicio, nom_servicio));

                } while (rs.next());
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias" + e);
            System.out.println("Error: " + e);
        }
        return empleados;
    }

    public ArrayList buscarEmpleadoNombre(String p_nombre) {
        ArrayList<Empleado> empleados = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callFunction = "{ ? = call PKG_EMPLEADO.buscar_nombre_empleado(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.setString(2, p_nombre);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            if (rs != null && rs.next()) {
                do {
                    int id_em = rs.getInt("ID");
                    int id_per = rs.getInt("IDPersona");
                    String carnet = rs.getString("Carnet");
                    String nombre_per = rs.getString("Nombre");
                    String cont = rs.getString("Pass");
                    int estado = rs.getInt("Estado");
                    int servicio = rs.getInt("IDServicio");
                    String nom_servicio = rs.getString("Servicio");

                    empleados.add(new Empleado(id_em, id_per, carnet, cont, nombre_per, estado, servicio, nom_servicio));

                } while (rs.next());
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias" + e);
            System.out.println("Error: " + e);
        }
        return empleados;
    }

    public String actualizarEmpleado(Empleado emp) throws SQLException {

        String sql = "{CALL PKG_EMPLEADO.actualizar_empleado(?, ?, ?, ?, ?, ?)}";
        Connection con = Conexion.Conectar();
        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, emp.getId_empleado());
            pstmt.setString(2, emp.getCarnet());
            pstmt.setString(3, emp.getContrasenna());
            pstmt.setInt(4, emp.getId_persona());
            pstmt.setInt(5, emp.getEstado());
            pstmt.setInt(6, emp.getId_servicio());

            mensaje = "Empleado actualizado exitosamente";
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            mensaje = "No se pudo realizar la actualizacion del Empleado: \n" + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarEmpleado(int id_empleado) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_EMPLEADO.eliminar_empleado(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, id_empleado);

            stmt.execute();

            mensaje = stmt.getString(1);

            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias" + e);
            System.out.println("Error: " + e);
        }

        return mensaje;
    }
}
