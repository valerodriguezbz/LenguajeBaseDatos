package Bo;

import Entidad.Facturacion;
import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

public class FacturacionBo {

    private String mensaje = "";

    public Object[] agregarFactura(Facturacion Factura) throws SQLException {
        Object[] result = new Object[4];

        String sql = "{CALL PKG_Factura.INSERTAR_FACTURA(?, ?, ?, ?, ?, ?, ?, ?)}";

        Connection con = Conexion.Conectar();
        CallableStatement cstmt = con.prepareCall(sql);

        try {
            java.sql.Date sqlDate = new java.sql.Date(Factura.getFecha().getTime());

            cstmt.setInt(1, Factura.getId_p());
            cstmt.setDate(2, sqlDate);
            cstmt.setString(3, Factura.getCliente());
            cstmt.setString(4, Factura.getProducto());
            cstmt.setInt(5, Factura.getCantida());
            cstmt.setFloat(6, Factura.getSubTotal());
            cstmt.setFloat(7, Factura.getImpuesto());
            cstmt.setFloat(8, Factura.getMontoTotal());

            cstmt.registerOutParameter(6, OracleTypes.FLOAT); // F_SUBTOTAL
            cstmt.registerOutParameter(7, OracleTypes.FLOAT); // F_IMPUESTO
            cstmt.registerOutParameter(8, OracleTypes.FLOAT); // F_MONTOTOTAL

            cstmt.execute();

            float subTotal = cstmt.getFloat(6);
            float impuesto = cstmt.getFloat(7);
            float montoTotal = cstmt.getFloat(8);

            String mensaje = "Factura agregada de manera correcta. Subtotal: " + subTotal + ", Impuesto: " + impuesto + ", Monto Total: " + montoTotal;

            result[0] = mensaje;
            result[1] = subTotal;
            result[2] = impuesto;
            result[3] = montoTotal;

            cstmt.close();
            con.close();
        } catch (SQLException e) {
            String mensaje = "No se pudo Facturar \n" + e.getMessage();
            result[0] = mensaje;
        }

        return result;
    }

    public String eliminarFactura(int factura) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_Factura.eliminar_factura(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, factura);

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

    public ArrayList buscarFactura(int factura) throws SQLException {
        ArrayList<Facturacion> facturas = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callFunction = "{ ? = call PKG_Factura.buscar_id_factura(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.setInt(2, factura);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            if (rs != null && rs.next()) {
                do {
                    Integer Num_factura = rs.getInt("ID_FACTURA");
                    Date fecha = rs.getDate("FECHA");
                    String cliente = rs.getString("CLIENTE");
                    String producto = rs.getString("PRODUCTO");
                    Integer cantidad = rs.getInt("CANTIDAD");
                    float subTotal= rs.getFloat("SUBTOTAL");
                    float imp= rs.getFloat("IMPUESTO");
                    float montototal = rs.getFloat("MONTOFINAL");

                    facturas.add(new Facturacion(Num_factura, fecha, cliente, producto, cantidad, subTotal,
                    imp,  montototal));

                } while (rs.next());
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias" + e);
            System.out.println("Error: " + e);
        }
        return facturas;
    }
}
