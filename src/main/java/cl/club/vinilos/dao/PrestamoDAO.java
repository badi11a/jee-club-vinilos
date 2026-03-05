package cl.club.vinilos.dao;

import cl.club.vinilos.modelo.Prestamo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public boolean registrar(int idSocio, int idVinilo) {
        String insertPrestamo = "INSERT INTO prestamos (id_socio, id_vinilo, fecha_prestamo) VALUES (?, ?, ?)";
        String updateVinilo = "UPDATE vinilos SET disponible = false WHERE id_vinilo = ?";
        Connection conn = null;
        try {
            conn = ConexionBD.obtenerConexion();
            conn.setAutoCommit(false);
            try (PreparedStatement psIns = conn.prepareStatement(insertPrestamo);
                 PreparedStatement psUpd = conn.prepareStatement(updateVinilo)) {
                psIns.setInt(1, idSocio);
                psIns.setInt(2, idVinilo);
                psIns.setDate(3, Date.valueOf(LocalDate.now()));
                psIns.executeUpdate();
                psUpd.setInt(1, idVinilo);
                psUpd.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                if (conn != null) conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }

    public boolean devolver(int idPrestamo, int idVinilo) {
        String updatePrestamo = "UPDATE prestamos SET fecha_devolucion = ? WHERE id_prestamo = ?";
        String updateVinilo = "UPDATE vinilos SET disponible = true WHERE id_vinilo = ?";
        Connection conn = null;
        try {
            conn = ConexionBD.obtenerConexion();
            conn.setAutoCommit(false);
            try (PreparedStatement psPre = conn.prepareStatement(updatePrestamo);
                 PreparedStatement psVin = conn.prepareStatement(updateVinilo)) {
                psPre.setDate(1, Date.valueOf(LocalDate.now()));
                psPre.setInt(2, idPrestamo);
                psPre.executeUpdate();
                psVin.setInt(1, idVinilo);
                psVin.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                if (conn != null) conn.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try { if (conn != null) conn.close(); } catch (SQLException e) {}
        }
    }

    public List<Prestamo> listarPorSocio(int idSocio) {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT p.*, v.titulo AS titulo_vinilo " +
                     "FROM prestamos p " +
                     "JOIN vinilos v ON p.id_vinilo = v.id_vinilo " +
                     "WHERE p.id_socio = ? AND p.fecha_devolucion IS NULL";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Prestamo p = new Prestamo();
                    p.setIdPrestamo(rs.getInt("id_prestamo"));
                    p.setIdVinilo(rs.getInt("id_vinilo"));
                    p.setTituloVinilo(rs.getString("titulo_vinilo"));
                    p.setFechaPrestamo(rs.getDate("fecha_prestamo"));
                    lista.add(p);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}