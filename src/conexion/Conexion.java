package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    public static Connection Conectar() throws SQLException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            String url = "jdbc:oracle:thin:@//localhost:1521/orcl";
            String usuario = "PROYECTO";
            String pass = "12345";
            System.out.println("Conectado a la base de datos");
            return DriverManager.getConnection(url, usuario, pass);
        } catch (ClassNotFoundException e) {
            throw new SQLException(e.getMessage());
        }
    }
}

//    private Connection conexion = null;
//
//    private String url, usuario, pass;
//
//    public void Conectar() {
//        try {
//            Class.forName("oracle.jdbc.OracleDriver");
//            url = "jdbc:oracle:thin:@//localhost:1521/orcl";
//            usuario = "PROYECTO";
//            pass = "12345";
//
//            conexion = DriverManager.getConnection(url, usuario, pass);
//            System.out.println("Conecion exitosa");
//            
//        } catch (Exception e) {
//            System.out.println("Error al conectar: " + e);
//        }
//    }
//    public void Desconectar() {
//        try {
//            conexion.close();
//            System.out.println("Conexion cerrada");
//        } catch (Exception e) {
//            System.out.println("Error al desconectar");
//        }
//    }
//    public void SelectDePrueba() {
//        try {
//            Statement sa = conexion.createStatement();
//            String consulta = "SELECT * FROM PROYECTO.CATEGORIA";
//            ResultSet rs = sa.executeQuery(consulta);
//
//            while (rs.next()) {
//                Integer id = rs.getInt("ID_CATEGORIA");
//                String categoria = rs.getString("NOMBRE_CATEGORIA");
//             
//                System.out.println("ID: " + id + " || NOMBRE CATEGORIA: " + categoria);
//            }
//
//            sa.close();
//
//        } catch (Exception e) {
//            System.out.println("Error en el select o no hay datos" + e);
//        }
//    }
//    
//    
//    public void insertarMascota(String nombreMascota) {
//        String jdbcUrl = "jdbc:oracle:thin:@//localhost:1521/orcl";
//        String usuario = "PROYECTO";
//        String contrasenna = "12345";
//        
//        // Sentencia SQL para el INSERT
//        String sql = "INSERT INTO Mascota (nombre_mascota) VALUES (30)";
//
//        try {
//            // Registrar el controlador de Oracle
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//
//            // Establecer la conexión
//            Connection connection = DriverManager.getConnection(jdbcUrl, usuario, contrasenna);
//
//            // Preparar la sentencia SQL
//            PreparedStatement pstmt = connection.prepareStatement(sql);
//            pstmt.setString(1, nombreMascota);
//
//            // Ejecutar el INSERT
//            int filasAfectadas = pstmt.executeUpdate();
//
//            if (filasAfectadas > 0) {
//                System.out.println("Registro insertado correctamente en la tabla Mascota.");
//            } else {
//                System.out.println("Error al insertar el registro en la tabla Mascota.");
//            }
//
//            // Cerrar la conexión
//            pstmt.close();
//            connection.close();
//        } catch (ClassNotFoundException e) {
//            System.out.println("Error: No se encontró el controlador JDBC de Oracle.");
//            e.printStackTrace();
//        } catch (SQLException e) {
//            System.out.println("Error al conectar a la base de datos o al realizar el INSERT.");
//            e.printStackTrace();
//        }
//    }

