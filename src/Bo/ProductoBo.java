package Bo;

import Entidad.Producto;
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
public class ProductoBo {

    private String mensaje = "";

    public String agregarProducto(Producto producto) throws SQLException {

        String sql = "{CALL PKG_PRODUCTO.insertar_producto(?, ?, ?, ?, ?, ?, ?)}";

        Connection con = Conexion.Conectar();

        PreparedStatement pstmt = con.prepareCall(sql);

        try {

            pstmt.setString(1, producto.getCodigo_producto());
            pstmt.setInt(2, producto.getId_categoria());
            pstmt.setString(3, producto.getNombre_producto());
            pstmt.setFloat(4, producto.getPrecio());
            pstmt.setFloat(5, producto.getIva());
            pstmt.setInt(6, producto.getCantidad());
            pstmt.setString(7, producto.getImagen());

            pstmt.execute();
            pstmt.close();
            con.close();
            mensaje = "Producto agregado de manera correcta";

        } catch (SQLException e) {

            mensaje = "No se pudo agregar el producto \n" + e.getMessage();
        }
        return mensaje;
    }

    public ArrayList buscarProductoId(int idConsulta) {

        ArrayList<Producto> productos = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_PRODUCTO.buscar_producto_id(?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setInt(1, idConsulta);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    Integer productoID = rs.getInt("ID");
                    String codigo_producto = rs.getString("CodigoProducto");
                    String nombre_producto = rs.getString("NombreProducto");
                    int categoria = rs.getInt("IDCategoria");
                    float precio = rs.getFloat("Precio");
                    Integer cantidad = rs.getInt("Cantidad");
                    String img = rs.getString("ImgProducto");

                    productos.add(new Producto(productoID, codigo_producto, categoria, nombre_producto, precio, cantidad, img));
                } while (rs.next());
            } else {
                JOptionPane.showMessageDialog(null, "No se encontraron coincidencias.");
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error al obtener el id del producto: " + e);
        }

        return productos;
    }

    public ArrayList buscarProductoNombre(String p_producto_nombre) {
        ArrayList<Producto> productos = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callProcedure = "{call PKG_PRODUCTO.buscar_producto_nombre(?, ?)}";
            CallableStatement stmt = con.prepareCall(callProcedure);

            stmt.setString(1, p_producto_nombre);

            stmt.registerOutParameter(2, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(2);

            if (rs != null && rs.next()) {
                do {
                    Integer productoID = rs.getInt("ID");
                    String codigo_producto = rs.getString("CodigoProducto");
                    String nombre_producto = rs.getString("NombreProducto");
                    int categoria = rs.getInt("IDCategoria");
                    float precio = rs.getFloat("Precio");
                    Integer cantidad = rs.getInt("Cantidad");
                    String img = rs.getString("ImgProducto");

                    productos.add(new Producto(productoID, codigo_producto, categoria, nombre_producto, precio, cantidad, img));
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

        return productos;
    }

    public String actualizarProducto(Producto producto) throws SQLException {

        String sql = "{CALL PKG_PRODUCTO.actualizar_producto(?, ?, ?, ?, ?, ?, ?, ?)}";
        Connection con = Conexion.Conectar();
        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, producto.getId_producto());
            pstmt.setString(2, producto.getCodigo_producto());
            pstmt.setInt(3, producto.getId_categoria());
            pstmt.setString(4, producto.getNombre_producto());
            pstmt.setFloat(5, producto.getPrecio());
            pstmt.setFloat(6, producto.getIva());
            pstmt.setInt(7, producto.getCantidad());
            pstmt.setString(8, producto.getImagen());

            pstmt.execute();
            pstmt.close();
            con.close();
            mensaje = "Producto actualizado exitosamente";
        } catch (SQLException e) {
            mensaje = "No se pudo realizar la actualizacion de la Persona: \n" + e.getMessage();
            System.out.println(e.getMessage());
        }
        return mensaje;
    }

    public String eliminarProducto(int id_producto) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_CITA.eliminar_cita(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, id_producto);

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
