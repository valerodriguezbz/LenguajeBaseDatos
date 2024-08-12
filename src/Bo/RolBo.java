package Bo;

import Entidad.Rol;
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
 * @author vrb00
 */
public class RolBo {

    private String mensaje = "";

    public String agregarRol(Rol rol) throws SQLException {

        String sql = "{CALL PKG_ROL.insertar_rol(?)}";

        Connection con = Conexion.Conectar();

        PreparedStatement pstmt = con.prepareCall(sql);

        try {

            pstmt.setString(1, rol.getNombre_rol());

            mensaje = "Rol agregado de manera correcta";
            pstmt.execute();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No se pudo agregar el rol \n" + e.getMessage();
        }
        return mensaje;
    }

    public ArrayList buscarRolId(int idConsulta) {

        ArrayList<Rol> rol = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_ROL.buscar_rol_id (?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setInt(1, idConsulta);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            while (rs.next()) {
                int id_rol = rs.getInt("ID");
                String nombre_rol = rs.getString("Nombre");

                rol.add(new Rol(id_rol, nombre_rol));
            }

            if (rs != null && rs.next()) {
                do {
                    int id_rol = rs.getInt("ID");
                    String nombre_rol = rs.getString("Nombre");

                    rol.add(new Rol(id_rol, nombre_rol));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener el id del rol: " + e);
        }

        return rol;
    }

    public ArrayList buscarNombreRol(String p_rol) {
        ArrayList<Rol> rol = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_ROL.buscar_nombre_rol(?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setString(1, p_rol);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    int id_rol = rs.getInt("ID");
                    String nombre_rol = rs.getString("Rol");

                    rol.add(new Rol(id_rol, nombre_rol));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener el nombre del rol: " + e);
        }

        return rol;
    }

    public String actualizarRol(Rol rol) throws SQLException {

        String sql = "{CALL PKG_ROL.actualizar_rol(?, ?)}";
        Connection con = Conexion.Conectar();
        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, rol.getId_rol());
            pstmt.setString(2, rol.getNombre_rol());

            mensaje = "Rol actualizado exitosamente";
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            mensaje = "No se pudo realizar la actualizacion del rol: \n" + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarRol(int id_rol) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_ROL.eliminar_rol(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, id_rol);

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
