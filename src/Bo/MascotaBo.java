package Bo;

import Entidad.Mascota;
import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author kyran
 */
public class MascotaBo {

    private String mensaje = "";

    public String agregarMascota(Mascota mac) throws SQLException {

        String sql = "{CALL PKG_MASCOTA.insertar_mascota(?, ?, ?, ?, ?, ?, ?, ?)}";

        Connection con = Conexion.Conectar();

        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, mac.getId_persona());
            pstmt.setString(2, mac.getNombre_mascota());
            pstmt.setInt(3, mac.getId_especie());
            pstmt.setString(4, mac.getRaza());
            pstmt.setInt(5, mac.getEdad());
            pstmt.setFloat(6, mac.getPeso());
            pstmt.setString(7, mac.getGenero());
            pstmt.setInt(8, mac.getEsterilizada());

            mensaje = "Mascota agregada de manera correcta";
            pstmt.execute();
            pstmt.close();
            con.close();

        } catch (SQLException e) {
            mensaje = "No se pudo agregar la mascota \n" + e.getMessage();
        }
        return mensaje;
    }

    public ArrayList buscarNombreMascota(String p_nombre) {
        ArrayList<Mascota> mascotas = new ArrayList<>();

        try {
            Connection con = Conexion.Conectar();

            String callFunction = "{ ? = call PKG_MASCOTA.buscar_nombre_mascota(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.setString(2, p_nombre);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            if (rs != null && rs.next()) {
                do {
                    int id_mas = rs.getInt("ID");
                    int id_per = rs.getInt("IDPersona");
                    String nombre_per = rs.getString("NombrePersona");
                    String nombre_mas = rs.getString("NombreMascota");
                    int id_especie = rs.getInt("IDEspecie");
                    String nom_espe = rs.getString("NombreEspecie");
                    String raza = rs.getString("Raza");
                    int edad = rs.getInt("Edad");
                    float peso = rs.getFloat("Peso");
                    String genero = rs.getString("Genero");
                    int esterilizada = rs.getInt("Esterilizada");

                    mascotas.add(new Mascota(id_mas, id_per, nombre_per, nombre_mas,
                            id_especie, nom_espe, raza, edad, peso, genero, esterilizada));
                } while (rs.next());
            }
            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "No se encontraron coincidencias" + e);
            System.out.println("Error: " + e);
        }
        return mascotas;
    }

    public String actualizarMascota(Mascota mac) throws SQLException {

        String sql = "{CALL PKG_MASCOTA.actualizar_mascota(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        Connection con = Conexion.Conectar();
        PreparedStatement pstmt = con.prepareCall(sql);

        try {
            pstmt.setInt(1, mac.getId_mascota());
            pstmt.setInt(2, mac.getId_persona());
            pstmt.setString(3, mac.getNombre_mascota());
            pstmt.setInt(4, mac.getId_especie());
            pstmt.setString(5, mac.getRaza());
            pstmt.setInt(6, mac.getEdad());
            pstmt.setFloat(7, mac.getPeso());
            pstmt.setString(8, mac.getGenero());
            pstmt.setInt(9, mac.getEsterilizada());

            mensaje = "Mascota actualizada exitosamente";
            pstmt.execute();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            mensaje = "No se pudo realizar la actualizacion de la Mascota: \n" + e.getMessage();
        }
        return mensaje;
    }

    public String eliminarMascota(int id_mascota) throws SQLException {
        try {
            Connection con = Conexion.Conectar();
            String callFunction = "{ ? = call PKG_MASCOTA.eliminar_mascota(?) }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.VARCHAR);

            stmt.setInt(2, id_mascota);

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
