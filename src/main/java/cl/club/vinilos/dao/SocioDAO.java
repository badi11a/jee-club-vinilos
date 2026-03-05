package cl.club.vinilos.dao;

import cl.club.vinilos.modelo.Socio;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SocioDAO {

    public Socio validarSocio(String email, String password) {
        Socio socio = null;
        String sql = "SELECT id_socio, nombre, email, password FROM socios WHERE email = ? AND password = ?";

        try (Connection conn = ConexionBD.obtenerConexion();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    socio = new Socio();
                    socio.setIdSocio(rs.getInt("id_socio"));
                    socio.setNombre(rs.getString("nombre"));
                    socio.setEmail(rs.getString("email"));
                    socio.setPassword(rs.getString("password"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return socio;
    }
}