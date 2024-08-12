package Bo;

import Entidad.Categoria;
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
public class CategoriaBo {

    private String mensaje = "";

    public String agregarCategoria(Categoria categoria) throws SQLException {

        String sql = "{CALL PKG_CATEGORIA.insertar_categoria(?)}";

        Connection con = Conexion.Conectar();

        PreparedStatement pstmt = con.prepareCall(sql);

        try {

            pstmt.setString(1, categoria.getNombre_categoria());

            mensaje = "Categoria agregada de manera correcta";
            pstmt.execute();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
            mensaje = "No se pudo agregar la categoria \n" + e.getMessage();
        }
        return mensaje;
    }

    public ArrayList buscarCategoriaId(int idConsulta) {

        ArrayList<Categoria> categoria = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_CATEGORIA.buscar_categoria_id (?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setInt(1, idConsulta);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    int id_categoria = rs.getInt("ID");
                    String nombre_categoria = rs.getString("NombreCategoria");

                    categoria.add(new Categoria(id_categoria, nombre_categoria));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener el id de la categoria: " + e);
        }

        return categoria;
    }

    public ArrayList buscarNombreCategoria(String p_categoria) {
        ArrayList<Categoria> categoria = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_CATEGORIA.buscar_nombre_categoria(?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setString(1, p_categoria);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    int id_categoria = rs.getInt("ID");
                    String nombre_categoria = rs.getString("NombreCategoria");

                    categoria.add(new Categoria(id_categoria, nombre_categoria));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener el id de la categoria: " + e);
        }

        return categoria;
    }

    public String actualizarCategoria(Categoria categoria) throws SQLException {

        String sql = "{CALL PKG_CATEGORIA.actualizar_categoria(?, ?)}";
        Connection con = Conexion.Conectar();
        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, categoria.getId_categoria());
            pstmt.setString(2, categoria.getNombre_categoria());

            mensaje = "Categoria actualizada exitosamente";
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            mensaje = "No se pudo realizar la actualizacion a la categoria: \n" + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarCategoria(int id_categoria) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_CATEGORIA.eliminar_categoria(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, id_categoria);

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
