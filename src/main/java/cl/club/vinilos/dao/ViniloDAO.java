package cl.club.vinilos.dao;

import cl.club.vinilos.modelo.Vinilo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                Vinilo v = new Vinilo(
                    rs.getInt("id_vinilo"),
                    rs.getString("titulo"),
                    rs.getString("artista"),
                    rs.getInt("anio_lanzamiento"),
                    rs.getString("genero")
                );
                lista.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean agregar(Vinilo vinilo) {
        String sql = "INSERT INTO vinilos (titulo, artista, anio_lanzamiento, genero) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, vinilo.getTitulo());
            ps.setString(2, vinilo.getArtista());
            ps.setInt(3, vinilo.getAnioLanzamiento());
            ps.setString(4, vinilo.getGenero());
            
            return ps.executeUpdate() > 0;
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Vinilo obtenerPorId(int id) {
        Vinilo vinilo = null;
        String sql = "SELECT * FROM vinilos WHERE id_vinilo = ?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    vinilo = new Vinilo(
                        rs.getInt("id_vinilo"),
                        rs.getString("titulo"),
                        rs.getString("artista"),
                        rs.getInt("anio_lanzamiento"),
                        rs.getString("genero")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vinilo;
    }

    public boolean actualizar(Vinilo vinilo) {
        String sql = "UPDATE vinilos SET titulo=?, artista=?, anio_lanzamiento=?, genero=? WHERE id_vinilo=?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, vinilo.getTitulo());
            ps.setString(2, vinilo.getArtista());
            ps.setInt(3, vinilo.getAnioLanzamiento());
            ps.setString(4, vinilo.getGenero());
            ps.setInt(5, vinilo.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean eliminar(int id) {
        String sql = "DELETE FROM vinilos WHERE id_vinilo=?";
        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}