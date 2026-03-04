package cl.club.vinilos.controlador;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        if ("admin@club.cl".equals(email) && "123456".equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", email);
            response.sendRedirect("vinilos");
        } else {
            response.sendRedirect("index.jsp?error=1");
        }
    }
}