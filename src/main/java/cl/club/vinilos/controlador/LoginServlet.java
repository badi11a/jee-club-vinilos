package cl.club.vinilos.controlador;

import cl.club.vinilos.dao.SocioDAO;
import cl.club.vinilos.modelo.Socio;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    private SocioDAO socioDAO = new SocioDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String email = request.getParameter("email");
        String pass = request.getParameter("password");

        Socio socio = socioDAO.validarSocio(email, pass);

        if (socio != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", socio);
            response.sendRedirect("vinilos");
        } else {
            request.setAttribute("error", "Credenciales inválidas");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}