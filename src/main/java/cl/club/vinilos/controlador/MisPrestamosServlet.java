package cl.club.vinilos.controlador;

import cl.club.vinilos.dao.PrestamoDAO;
import cl.club.vinilos.modelo.Prestamo;
import cl.club.vinilos.modelo.Socio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/mis-prestamos")
public class MisPrestamosServlet extends HttpServlet {
    private PrestamoDAO prestamoDAO = new PrestamoDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Socio socio = (Socio) session.getAttribute("usuario");

        if (socio == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        List<Prestamo> lista = prestamoDAO.listarPorSocio(socio.getIdSocio());
        request.setAttribute("prestamos", lista);
        request.getRequestDispatcher("mis-prestamos.jsp").forward(request, response);
    }
}