package Bo;

import Entidad.Cita;
import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

public class CitaBo {

    private String mensaje = "";

    public String agregarCita(Cita cita) throws SQLException {

        String sql = "{CALL PKG_CITA.INSERTAR_CITA(?, ?, ?, ?, ?)}";

        Connection con = Conexion.Conectar();

        CallableStatement pstmt = con.prepareCall(sql);

        try {

            java.sql.Date sqlDate = new java.sql.Date(cita.getFecha().getTime());

            pstmt.setDate(1, sqlDate);
            pstmt.setString(2, cita.getHora());
            pstmt.setInt(3, cita.getId_mascota());
            pstmt.setInt(4, cita.getId_servicio());
            pstmt.setString(5, cita.getEstado());

            mensaje = "Cita agregada de manera correcta";
            pstmt.execute();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
            mensaje = "No se pudo agregar la cita \n" + e.getMessage();

        }

        return mensaje;
    }

    public ArrayList buscarCitaMascota(String mascota) {

        ArrayList<Cita> citas = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callFunction = "{ ? = call PKG_CITA.buscar_cita_mascota(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.setString(2, mascota);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            if (rs != null && rs.next()) {
                do {
                    int id = rs.getInt("ID");
                    Date fecha = rs.getDate("Fecha");
                    String hora = rs.getString("Hora");
                    int id_mascota = rs.getInt("IDMascota");
                    int id_servico = rs.getInt("IDServicio");
                    String estado = rs.getString("Estado");

                    citas.add(new Cita(id, fecha, hora, id_mascota, id_servico, estado));

                } while (rs.next());
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias" + e);
            System.out.println("Error: " + e);
        }
        return citas;
    }

    public String actualizarCita(Cita cita) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_CITA.actualizar_cita(?, ?, ?, ?, ?, ?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, cita.getId_cita());
            java.sql.Date sqlDate = new java.sql.Date(cita.getFecha().getTime());

            stmt.setDate(3, sqlDate);
            stmt.setString(4, cita.getHora());
            stmt.setInt(5, cita.getId_mascota());
            stmt.setInt(6, cita.getId_servicio());
            stmt.setString(7, cita.getEstado());

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

    public String eliminarCita(int id_cita) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_CITA.eliminar_cita(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, id_cita);

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
