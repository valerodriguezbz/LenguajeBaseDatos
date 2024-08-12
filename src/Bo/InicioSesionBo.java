package Bo;

import Entidad.Empleado;
import conexion.Conexion;
import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author vrb00
 */
public class InicioSesionBo {

    private ArrayList<Empleado> empleado = new ArrayList<>();

    public boolean verificarInicioSesion(String carnet, String pas) {
        boolean correcto = false;

        if (verificarCarnet(carnet)) {

            for (Empleado emp : empleado) {
                if (emp.getCarnet().equalsIgnoreCase(carnet) && emp.getContrasenna().equals(pas)) {
                    correcto = true;
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "El carnet o la consenna son incorrectos.");
        }
        return correcto;
    }

    private boolean verificarCarnet(String carnet) {
        boolean verificar = false;
        try {
            Connection con = Conexion.Conectar();

            String callFunction = "{ ? = call PKG_INICIO_SESION.obtener_empleados_estado_1 }";
            CallableStatement stmt = con.prepareCall(callFunction);

            stmt.registerOutParameter(1, OracleTypes.CURSOR);

            stmt.execute();

            ResultSet rs = (ResultSet) stmt.getObject(1);

            if (rs != null && rs.next()) {
                do {
                    int id_empleado = rs.getInt("ID");
                    String carnet_e = rs.getString("CarnetE");
                    String pass = rs.getString("Pass");

                    empleado.add(new Empleado(id_empleado, carnet_e, pass));
                    verificar = true;

                } while (rs.next());
            }

            rs.close();
            stmt.close();
            con.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ocurrió un error al obtener el id del carnet: " + e);
            System.out.println("Error: " + e);
        }

        return verificar;
    }
}
