package Bo;

import Entidad.Especie;
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
public class EspecieBo {

    private String mensaje = "";

    public String agregarEspecie(Especie especie) throws SQLException {

        String sql = "{CALL PKG_ESPECIE.insertar_especie(?)}";

        Connection con = Conexion.Conectar();

        PreparedStatement pstmt = con.prepareCall(sql);

        try {

            pstmt.setString(1, especie.getNombre_especie());

            mensaje = "Especie agregada de manera correcta";
            pstmt.execute();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No se pudo agregar la especie \n" + e.getMessage();
        }
        return mensaje;
    }

    public ArrayList buscarIdEspecie(int idConsulta) {

        ArrayList<Especie> especie = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_ESPECIE.buscar_especie_id (?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setInt(1, idConsulta);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    int id_especie = rs.getInt("ID");
                    String nombre_especie = rs.getString("NombreEspecie");

                    especie.add(new Especie(id_especie, nombre_especie));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener el id de la especie: " + e);
        }

        return especie;
    }

    public ArrayList buscarNombreEspecie(String p_especie) {
        ArrayList<Especie> especie = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_ESPECIE.buscar_nombre_especie(?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setString(1, p_especie);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    int id_especie = rs.getInt("ID");
                    String nombre_especie = rs.getString("NombreEspecie");

                    especie.add(new Especie(id_especie, nombre_especie));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener el nombre de la especie: " + e);
        }

        return especie;
    }

    public String actualizarEspecie(Especie especie) throws SQLException {

        String sql = "{CALL PKG_ESPECIE.actualizar_especie(?, ?)}";
        Connection con = Conexion.Conectar();
        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, especie.getId_especie());
            pstmt.setString(2, especie.getNombre_especie());

            mensaje = "Especie actualizado exitosamente";
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            mensaje = "No se pudo realizar la actualizacion de la especie: \n" + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarEspecie(int id_especie) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_ESPECIE.eliminar_especie(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, id_especie);

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
