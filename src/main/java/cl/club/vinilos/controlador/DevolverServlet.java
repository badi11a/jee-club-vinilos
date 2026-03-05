package cl.club.vinilos.controlador;

import cl.club.vinilos.dao.PrestamoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/devolverVinilo")
public class DevolverServlet extends HttpServlet {
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        try {
            int idPrestamo = Integer.parseInt(request.getParameter("idPrestamo"));
            int idVinilo = Integer.parseInt(request.getParameter("idVinilo"));
            prestamoDAO.devolver(idPrestamo, idVinilo);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        
        response.sendRedirect("mis-prestamos");
    }
}