package Controlador;

public class clsTipoPuesto {

    private int m_iPuestoId;
    private String m_sPuestoNombre;
    private double m_dPuestoSueldo;
    private String m_sPuestoEstado;

    // Constructor vacío
    public clsTipoPuesto() {
    }

    // Constructor lleno
    public clsTipoPuesto(int id, String nombre, double sueldo, String estado) {
        this.m_iPuestoId = id;
        this.m_sPuestoNombre = nombre;
        this.m_dPuestoSueldo = sueldo;
        this.m_sPuestoEstado = estado;
    }

    // GETTERS Y SETTERS
    public int getM_iPuestoId() {
        return m_iPuestoId;
    }

    public void setM_iPuestoId(int m_iPuestoId) {
        this.m_iPuestoId = m_iPuestoId;
    }

    public String getM_sPuestoNombre() {
        return m_sPuestoNombre;
    }

    public void setM_sPuestoNombre(String m_sPuestoNombre) {
        this.m_sPuestoNombre = m_sPuestoNombre;
    }

    public double getM_dPuestoSueldo() {
        return m_dPuestoSueldo;
    }

    public void setM_dPuestoSueldo(double m_dPuestoSueldo) {
        this.m_dPuestoSueldo = m_dPuestoSueldo;
    }

    public String getM_sPuestoEstado() {
        return m_sPuestoEstado;
    }

    public void setM_sPuestoEstado(String m_sPuestoEstado) {
        this.m_sPuestoEstado = m_sPuestoEstado;
    }
}
