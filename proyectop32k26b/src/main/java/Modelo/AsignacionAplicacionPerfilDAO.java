/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Controlador.clsAsignacionAplicacionPerfil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 *
 * @author reyes
 */
public class AsignacionAplicacionPerfilDAO {
    
    // Clase DAO que se encarga de realizar las operaciones
// de base de datos para la tabla Asignacion_Aplicacion_Perfil

    // Sentencia SQL para obtener todos los registros de la tabla
    private static final String SQL_SELECT =
    "SELECT Aplcodigo, Percodigo, APLPins, APLPsel, APLPupd, APLPdel, APLPrep FROM AsignacionAplicacionPerfil";

    // Sentencia SQL para insertar un nuevo registro en la tabla
    private static final String SQL_INSERT =
    "INSERT INTO AsignacionAplicacionPerfil(Aplcodigo, Percodigo, APLPins, APLPsel, APLPupd, APLPdel, APLPrep) VALUES(?,?,?,?,?,?,?)";

    // Sentencia SQL para actualizar un registro existente
    private static final String SQL_UPDATE =
    "UPDATE AsignacionAplicacionPerfil SET Percodigo=?, APLPins=?, APLPsel=?, APLPupd=?, APLPdel=?, APLPrep=? WHERE Aplcodigo=?";

    // Sentencia SQL para eliminar un registro según su AplCódigo
    private static final String SQL_DELETE =
    "DELETE FROM AsignacionAplicacionPerfil WHERE Aplcodigo=?";

    // Sentencia SQL para consultar un registro específico según su AplCódigo
    private static final String SQL_QUERY =
    "SELECT Aplcodigo, Percodigo, APLPins, APLPsel, APLPupd, APLPdel, APLPdel FROM AsignacionAplicacionPerfil WHERE Aplcodigo=?";


    // Método que obtiene todos los registros de la tabla
    public List<clsAsignacionAplicacionPerfil> select() {

        // Variables necesarias para conectarse y ejecutar la consulta
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        // Objeto que representará cada registro de la tabla
        clsAsignacionAplicacionPerfil asignacion = null;

        // Lista donde se guardarán todos los registros encontrados
        List<clsAsignacionAplicacionPerfil> asignaciones = new ArrayList<>();

        try {

            // Se obtiene la conexión a la base de datos
            conn = Conexion.getConnection();

            // Se prepara la consulta SQL
            stmt = conn.prepareStatement(SQL_SELECT);

            // Se ejecuta la consulta
            rs = stmt.executeQuery();

            // Se recorren todos los registros obtenidos
            while (rs.next()) {

                // Se obtienen los valores de cada columna
                int Aplcodigo = rs.getInt("Aplcodigo");
                int Percodigo = rs.getInt("Percodigo");

                String insert = rs.getString("APLPins");
                String select = rs.getString("APLPsel");
                String update = rs.getString("APLPupd");
                String delete = rs.getString("APLPdel");
                String reporte = rs.getString("APLPrep");

                // Se crea el objeto con los valores obtenidos
                asignacion = new clsAsignacionAplicacionPerfil(Aplcodigo, Percodigo);

                // Se asignan los permisos correspondientes
                asignacion.setAPLPins(insert);
                asignacion.setAPLPsel(select);
                asignacion.setAPLPupd(update);
                asignacion.setAPLPdel(delete);
                asignacion.setAPLPrep(reporte);

                // Se agrega el objeto a la lista
                asignaciones.add(asignacion);
            }

        } catch (SQLException ex) {

            // En caso de error se muestra en consola
            ex.printStackTrace(System.out);

        } finally {

            // Se cierran todos los recursos utilizados
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        // Se devuelve la lista con todos los registros
        return asignaciones;
    }


    // Método para insertar un nuevo registro en la tabla
    public int insert(clsAsignacionAplicacionPerfil asignacion) {

        Connection conn = null;
        PreparedStatement stmt = null;

        // Variable que indica cuántos registros fueron afectados
        int rows = 0;

        try {

            // Se obtiene la conexión
            conn = Conexion.getConnection();

            // Se prepara la consulta SQL de inserción
            stmt = conn.prepareStatement(SQL_INSERT);

            // Se asignan los valores a los parámetros de la consulta
            stmt.setInt(1, asignacion.getAplcodigo());
            stmt.setInt(2, asignacion.getPercodigo());
            stmt.setString(3, asignacion.getAPLPins());
            stmt.setString(4, asignacion.getAPLPsel());
            stmt.setString(5, asignacion.getAPLPupd());
            stmt.setString(6, asignacion.getAPLPdel());
            stmt.setString(7, asignacion.getAPLPrep());

            // Se muestra en consola la consulta que se ejecutará
            System.out.println("Ejecutando query:" + SQL_INSERT);

            // Se ejecuta la inserción
            rows = stmt.executeUpdate();

            // Se muestran los registros afectados
            System.out.println("Registros afectados:" + rows);

        } catch (SQLException ex) {

            // Se muestra el error si ocurre alguno
            ex.printStackTrace(System.out);

        } finally {

            // Se cierran los recursos utilizados
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        // Se devuelve la cantidad de registros afectados
        return rows;
    }


    // Método para actualizar un registro existente
    public int update(clsAsignacionAplicacionPerfil asignacion) {

        Connection conn = null;
        PreparedStatement stmt = null;

        // Variable para indicar cuántos registros fueron modificados
        int rows = 0;

        try {

            // Se obtiene la conexión a la base de datos
            conn = Conexion.getConnection();

            // Se muestra la consulta que se ejecutará
            System.out.println("Ejecutando query: " + SQL_UPDATE);

            // Se prepara la consulta de actualización
            stmt = conn.prepareStatement(SQL_UPDATE);

            // Se asignan los nuevos valores
            stmt.setInt(1, asignacion.getPercodigo());
            stmt.setString(2, asignacion.getAPLPins());
            stmt.setString(3, asignacion.getAPLPsel());
            stmt.setString(4, asignacion.getAPLPupd());
            stmt.setString(5, asignacion.getAPLPdel());
            stmt.setString(6, asignacion.getAPLPrep());

            // Se indica qué registro se actualizará
            stmt.setInt(7, asignacion.getAplcodigo());

            // Se ejecuta la actualización
            rows = stmt.executeUpdate();

            // Se muestran los registros modificados
            System.out.println("Registros actualizados:" + rows);

        } catch (SQLException ex) {

            // Se muestra el error si ocurre alguno
            ex.printStackTrace(System.out);

        } finally {

            // Se cierran los recursos utilizados
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }


    // Método para eliminar un registro según su AplCódigo
    public int delete(clsAsignacionAplicacionPerfil asignacion) {

        Connection conn = null;
        PreparedStatement stmt = null;

        int rows = 0;

        try {

            // Se obtiene la conexión a la base de datos
            conn = Conexion.getConnection();

            // Se muestra la consulta que se ejecutará
            System.out.println("Ejecutando query:" + SQL_DELETE);

            // Se prepara la consulta de eliminación
            stmt = conn.prepareStatement(SQL_DELETE);

            // Se asigna el código del registro que se eliminará
            stmt.setInt(1, asignacion.getAplcodigo());

            // Se ejecuta la eliminación
            rows = stmt.executeUpdate();

            // Se muestran los registros eliminados
            System.out.println("Registros eliminados:" + rows);

        } catch (SQLException ex) {

            // Se muestra el error en consola
            ex.printStackTrace(System.out);

        } finally {

            // Se cierran los recursos utilizados
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return rows;
    }


    // Método que busca un registro específico según su AplCódigo
    public clsAsignacionAplicacionPerfil query(clsAsignacionAplicacionPerfil asignacion) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {

            // Se obtiene la conexión
            conn = Conexion.getConnection();

            // Se muestra la consulta que se ejecutará
            System.out.println("Ejecutando query:" + SQL_QUERY);

            // Se prepara la consulta
            stmt = conn.prepareStatement(SQL_QUERY);

            // Se establece el código que se desea buscar
            stmt.setInt(1, asignacion.getAplcodigo());

            // Se ejecuta la consulta
            rs = stmt.executeQuery();

            // Si se encuentra el registro
            while (rs.next()) {

                // Se obtienen los datos del registro
                int AplCodigo = rs.getInt("Aplcodigo");
                int PerCodigo = rs.getInt("Percodigo");

                String insert = rs.getString("APLPins");
                String select = rs.getString("APLPsel");
                String update = rs.getString("APLPupd");
                String delete = rs.getString("APLPdel");
                String reporte = rs.getString("APLPrep");

                // Se crea el objeto con los datos obtenidos
                asignacion = new clsAsignacionAplicacionPerfil(AplCodigo, PerCodigo);

                // Se asignan los permisos
                asignacion.setAPLPins(insert);
                asignacion.setAPLPsel(select);
                asignacion.setAPLPupd(update);
                asignacion.setAPLPdel(delete);
                asignacion.setAPLPrep(reporte);
            }

        } catch (SQLException ex) {

            // Se muestra el error en caso de fallo
            ex.printStackTrace(System.out);

        } finally {

            // Se cierran los recursos utilizados
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        // Se devuelve el registro encontrado
        return asignacion;
    }
    
}
