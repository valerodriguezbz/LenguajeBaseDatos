/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Bo;

import Entidad.Hospitalizacion;
import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;
import java.sql.ResultSet;

/**
 *
 * @author Derek
 */
public class HospitalizacionBo {

    private String mensaje = "";

    public String agregarHospitalizacion(Hospitalizacion hos) throws SQLException {
        Connection con = Conexion.Conectar();
        String sql = "{ call PKG_HOSPITALIZACION.insertar_hospitalizacion(?, ?, ?, ?) }";

        PreparedStatement pstmt = con.prepareCall(sql);

        try {

            pstmt.setInt(1, hos.getId_mascota());
            pstmt.setString(1, hos.getFecha_entrada());
            pstmt.setString(3, hos.getDescripcion());
            pstmt.setFloat(4, hos.getPrecio());

            mensaje = "Hospitalizacion agregada de manera correcta";
            pstmt.execute();
            pstmt.close();
            con.close();

        } catch (SQLException e) {

            mensaje = "No se pudo agregar la hospitalizacion \n" + e.getMessage();

        }

        return mensaje;
    }

    public String actualizarHospitalizacion(Hospitalizacion hos) throws SQLException {

        String sql = "{CALL PKG_HOSPITALIZACION.actualizar_hospitalizacion(?, ?, ?, ?)}";
        Connection con = Conexion.Conectar();
        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, hos.getId_mascota());
            pstmt.setString(2, hos.getFecha_entrada());
            pstmt.setString(3, hos.getDescripcion());
            pstmt.setFloat(4, hos.getPrecio());

            mensaje = "Empleado actualizado exitosamente";
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            mensaje = "No se pudo realizar la actualizacion del Empleado: \n" + e.getMessage();
        }
        return mensaje;
    }
    public ArrayList buscarMascota(int p_id_hospitalizacion) {
    ArrayList<Hospitalizacion> hospitalizado = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callFunction = "{ ? = call PKG_HOSPITALIZACION.buscar_hospitalizacion_id(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.setInt(1,p_id_hospitalizacion);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            if (rs != null && rs.next()) {
                do {
                    int id_hos = rs.getInt("IDHospitalizacion");
                    int id_mas = rs.getInt("IDMascota");
                    String fecha = rs.getString("FechaEntrada");
                    String decripcion = rs.getString("Descripcion");
                    float precio = rs.getFloat("Precio");

                    hospitalizado.add(new Hospitalizacion(id_hos, id_mas, fecha, decripcion, precio));

                } while (rs.next());
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias" + e);
            System.out.println("Error: " + e);
        }
        return hospitalizado;
    }

    public String eliminarHospitalizacion(int id_hospitalizacion) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_HOSPITALIZACION.eliminar_hospitalizacion(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, id_hospitalizacion);

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
