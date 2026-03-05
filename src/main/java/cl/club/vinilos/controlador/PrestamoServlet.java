package cl.club.vinilos.controlador;

import cl.club.vinilos.dao.PrestamoDAO;
import cl.club.vinilos.modelo.Socio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/solicitarPrestamo")
public class PrestamoServlet extends HttpServlet {

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

        String idViniloStr = request.getParameter("id");
        
        if (idViniloStr != null) {
            int idVinilo = Integer.parseInt(idViniloStr);
            boolean exito = prestamoDAO.registrar(socio.getIdSocio(), idVinilo);

            if (exito) {
                request.setAttribute("mensaje", "Préstamo registrado");
            } else {
                request.setAttribute("error", "Error al procesar");
            }
        }

        request.getRequestDispatcher("vinilos").forward(request, response);
    }
}