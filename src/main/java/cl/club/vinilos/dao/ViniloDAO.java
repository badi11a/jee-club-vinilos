package cl.club.vinilos.dao;

import cl.club.vinilos.modelo.Vinilo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViniloDAO {

    public List<Vinilo> listarTodos() {
        List<Vinilo> lista = new ArrayList<>();
        String sql = "SELECT * FROM vinilos";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(new Vinilo(
                    rs.getInt("id_vinilo"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getInt("anio_lanzamiento"),
                    rs.getString("genero"),
                    rs.getBoolean("disponible")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(Vinilo vinilo) {
        String sql = "INSERT INTO vinilos (titulo, artista, anio_lanzamiento, genero, disponible) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vinilo.getTitulo());
            ps.setString(2, vinilo.getArtista());
            ps.setInt(3, vinilo.getAnioLanzamiento());
            ps.setString(4, vinilo.getGenero());
            ps.setBoolean(5, true);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Vinilo obtenerPorId(int idVinilo) {
        Vinilo v = null;
        String sql = "SELECT * FROM vinilos WHERE id_vinilo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVinilo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    v = new Vinilo(
                        rs.getInt("id_vinilo"),
                        rs.getString("titulo"),
                        rs.getString("artista"),
                        rs.getInt("anio_lanzamiento"),
                        rs.getString("genero"),
                        rs.getBoolean("disponible")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    public boolean actualizar(Vinilo v) {
        String sql = "UPDATE vinilos SET titulo=?, artista=?, anio_lanzamiento=?, genero=?, disponible=? WHERE id_vinilo=?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getTitulo());
            ps.setString(2, v.getArtista());
            ps.setInt(3, v.getAnioLanzamiento());
            ps.setString(4, v.getGenero());
            ps.setBoolean(5, v.isDisponible());
            ps.setInt(6, v.getIdVinilo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar(int idVinilo) {
        String sql = "DELETE FROM vinilos WHERE id_vinilo=?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idVinilo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}