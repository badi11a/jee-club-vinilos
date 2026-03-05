package cl.club.vinilos.controlador;

import cl.club.vinilos.dao.PrestamoDAO;
import cl.club.vinilos.modelo.Socio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/devolverVinilo")
public class DevolverServlet extends HttpServlet {
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Socio socio = (Socio) session.getAttribute("usuario");
        String idViniloStr = request.getParameter("idVinilo");

        if (socio != null && idViniloStr != null) {
            prestamoDAO.devolverConValidacion(Integer.parseInt(idViniloStr), socio.getIdSocio());
        }
        response.sendRedirect("mis-prestamos");
    }
}