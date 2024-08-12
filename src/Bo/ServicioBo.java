package Bo;

import Entidad.Servicio;
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
public class ServicioBo {

    private String mensaje = "";

    public String agregarServicio(Servicio servicio) throws SQLException {

        String sql = "{CALL PKG_SERVICIO.insertar_servicio(?, ?)}";

        Connection con = Conexion.Conectar();

        PreparedStatement pstmt = con.prepareCall(sql);

        try {

            pstmt.setString(1, servicio.getNombre_servicio());
            pstmt.setInt(2, servicio.getPrecio());

            mensaje = "Servicio agregado de manera correcta";
            pstmt.execute();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No se pudo agregar el servicio \n" + e.getMessage();
        }
        return mensaje;
    }

    public ArrayList buscarIdServicio(int idConsulta) {

        ArrayList<Servicio> servicio = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_SERVICIO.buscar_servicio_id (?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setInt(1, idConsulta);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    int id_Servicio = rs.getInt("ID");
                    String nombre_servicio = rs.getString("NombreServicio");
                    int precio = rs.getInt("Precio");

                    servicio.add(new Servicio(id_Servicio, nombre_servicio, precio));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Ocurrio un error al obtener el id del servicio: " + e);
        }

        return servicio;
    }

    public ArrayList buscarNombreServicio(String p_servicio) {
        ArrayList<Servicio> servicio = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_SERVICIO.buscar_nombre_servicio(?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setString(1, p_servicio);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    int id_Servicio = rs.getInt("ID");
                    String nombre_servicio = rs.getString("NombreServicio");
                    int precio = rs.getInt("Precio");

                    servicio.add(new Servicio(id_Servicio, nombre_servicio, precio));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Ocurrio un error al obtener el nombre del servicio: " + e);
        }

        return servicio;
    }

    public String actualizarServicio(Servicio servicio) throws SQLException {

        String sql = "{CALL PKG_SERVICIO.actualizar_servicio(?, ?, ?)}";
        Connection con = Conexion.Conectar();
        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, servicio.getId_servicio());
            pstmt.setString(2, servicio.getNombre_servicio());
            pstmt.setInt(3, servicio.getPrecio());

            mensaje = "Servicio actualizado exitosamente";
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            mensaje = "No se pudo realizar la actualizacion del Servicio: \n" + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarServicio(int id_servicio) throws SQLException {
        Connection con = Conexion.Conectar();
        String procedureCall = "{call PKG_SERVICIO.eliminar_servicio(?)}";

        try (CallableStatement cs = con.prepareCall(procedureCall)) {
            cs.setInt(1, id_servicio);
            cs.execute();
            mensaje = "Servicio eliminado exitosamente.";
        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "Ocurrio un error al eliminar el servicio. " + e;
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
