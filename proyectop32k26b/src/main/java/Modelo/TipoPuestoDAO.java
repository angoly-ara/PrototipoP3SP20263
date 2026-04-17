package Modelo;
// Angoly Camila Araujo Mayen 9959-24-17623
import Controlador.clsTipoPuesto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TipoPuestoDAO {

    public int mInsertar(clsTipoPuesto p_objPuesto) {
        int resultado = 0;
        String sql = "INSERT INTO tipo_puesto (puesto_nombre, puesto_sueldo, puesto_estado) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p_objPuesto.getM_sPuestoNombre());
            ps.setDouble(2, p_objPuesto.getM_dPuestoSueldo());
            ps.setString(3, p_objPuesto.getM_sPuestoEstado());
            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error en mInsertar: " + ex.getMessage());
            ex.printStackTrace();
        }

        return resultado;
    }

    public int mActualizar(clsTipoPuesto p_objPuesto) {
        int resultado = 0;
        String sql = "UPDATE tipo_puesto SET puesto_nombre=?, puesto_sueldo=?, puesto_estado=? WHERE puesto_id=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p_objPuesto.getM_sPuestoNombre());
            ps.setDouble(2, p_objPuesto.getM_dPuestoSueldo());
            ps.setString(3, p_objPuesto.getM_sPuestoEstado());
            ps.setInt(4, p_objPuesto.getM_iPuestoId());
            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error en mActualizar: " + ex.getMessage());
            ex.printStackTrace();
        }

        return resultado;
    }

    public int mEliminar(int p_iPuestoId) {
        int resultado = 0;
        String sql = "UPDATE tipo_puesto SET puesto_estado='I' WHERE puesto_id=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, p_iPuestoId);
            resultado = ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("Error en mEliminar: " + ex.getMessage());
            ex.printStackTrace();
        }

        return resultado;
    }

    public List<clsTipoPuesto> mListar() {
        List<clsTipoPuesto> lista = new ArrayList<>();
        String sql = "SELECT * FROM tipo_puesto WHERE puesto_estado='A'";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                clsTipoPuesto puesto = new clsTipoPuesto();
                puesto.setM_iPuestoId(rs.getInt("puesto_id"));
                puesto.setM_sPuestoNombre(rs.getString("puesto_nombre"));
                puesto.setM_dPuestoSueldo(rs.getDouble("puesto_sueldo"));
                puesto.setM_sPuestoEstado(rs.getString("puesto_estado"));
                lista.add(puesto);
            }

        } catch (SQLException ex) {
            System.out.println("Error en mListar: " + ex.getMessage());
            ex.printStackTrace();
        }

        return lista;
    }

    public clsTipoPuesto mBuscar(int id) {
        clsTipoPuesto puesto = null;
        String sql = "SELECT * FROM tipo_puesto WHERE puesto_id=?";

        try (Connection conn = Conexion.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                puesto = new clsTipoPuesto();
                puesto.setM_iPuestoId(rs.getInt("puesto_id"));
                puesto.setM_sPuestoNombre(rs.getString("puesto_nombre"));
                puesto.setM_dPuestoSueldo(rs.getDouble("puesto_sueldo"));
                puesto.setM_sPuestoEstado(rs.getString("puesto_estado"));
            }

        } catch (SQLException ex) {
            System.out.println("Error en mBuscar: " + ex.getMessage());
            ex.printStackTrace();
        }

        return puesto;
    }
}