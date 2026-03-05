package cl.club.vinilos.dao;

import cl.club.vinilos.modelo.Prestamo;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAO {

    public boolean registrar(int idSocio, int idVinilo) {
        String sqlIns = "INSERT INTO prestamos (id_socio, id_vinilo, fecha_prestamo) VALUES (?, ?, ?)";
        String sqlUpd = "UPDATE vinilos SET disponible = false WHERE id_vinilo = ?";
        Connection conn = null;
        try {
            conn = ConexionBD.obtenerConexion();
            conn.setAutoCommit(false);
            try (PreparedStatement psI = conn.prepareStatement(sqlIns);
                 PreparedStatement psU = conn.prepareStatement(sqlUpd)) {
                psI.setInt(1, idSocio);
                psI.setInt(2, idVinilo);
                psI.setDate(3, Date.valueOf(LocalDate.now()));
                psI.executeUpdate();
                psU.setInt(1, idVinilo);
                psU.executeUpdate();
                conn.commit();
                return true;
            } catch (SQLException e) {
                if (conn != null) conn.rollback();
                return false;
            }
        } catch (SQLException e) { return false; }
    }

    public boolean devolverConValidacion(int idVinilo, int idSocio) {
        // La validación ocurre aquí: el WHERE exige que coincidan Vinilo, Socio y que no esté devuelto
        String sqlPre = "UPDATE prestamos SET fecha_devolucion = CURRENT_DATE " +
                        "WHERE id_vinilo = ? AND id_socio = ? AND fecha_devolucion IS NULL";
        String sqlVin = "UPDATE vinilos SET disponible = true WHERE id_vinilo = ?";
        Connection conn = null;
        try {
            conn = ConexionBD.obtenerConexion();
            conn.setAutoCommit(false);
            try (PreparedStatement psP = conn.prepareStatement(sqlPre);
                 PreparedStatement psV = conn.prepareStatement(sqlVin)) {
                psP.setInt(1, idVinilo);
                psP.setInt(2, idSocio);
                if (psP.executeUpdate() > 0) {
                    psV.setInt(1, idVinilo);
                    psV.executeUpdate();
                    conn.commit();
                    return true;
                }
                if (conn != null) conn.rollback();
                return false;
            } catch (SQLException e) {
                if (conn != null) conn.rollback();
                return false;
            }
        } catch (SQLException e) { return false; }
    }

    public List<Prestamo> listarPorSocio(int idSocio) {
        List<Prestamo> lista = new ArrayList<>();
        String sql = "SELECT p.*, v.titulo FROM prestamos p " +
                     "JOIN vinilos v ON p.id_vinilo = v.id_vinilo " +
                     "WHERE p.id_socio = ? AND p.fecha_devolucion IS NULL";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idSocio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Prestamo p = new Prestamo();
                p.setIdVinilo(rs.getInt("id_vinilo"));
                p.setTituloVinilo(rs.getString("titulo"));
                p.setFechaPrestamo(rs.getDate("fecha_prestamo"));
                lista.add(p);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}