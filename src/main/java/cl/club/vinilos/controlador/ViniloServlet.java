package cl.club.vinilos.controlador;

import cl.club.vinilos.dao.ViniloDAO;
import cl.club.vinilos.modelo.Vinilo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/vinilos")
public class ViniloServlet extends HttpServlet {
    private ViniloDAO viniloDAO = new ViniloDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        if (accion == null) {
            List<Vinilo> lista = viniloDAO.listarTodos();
            request.setAttribute("vinilos", lista);
            request.getRequestDispatcher("catalogo.jsp").forward(request, response);
        } else if (accion.equals("eliminar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            viniloDAO.eliminar(id);
            response.sendRedirect("vinilos");
        } else if (accion.equals("editar")) {
            int id = Integer.parseInt(request.getParameter("id"));
            Vinilo v = viniloDAO.obtenerPorId(id);
            request.setAttribute("vinilo", v);
            request.getRequestDispatcher("formulario-vinilo.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String idStr = request.getParameter("idVinilo");
        String titulo = request.getParameter("titulo");
        String artista = request.getParameter("artista");
        int anio = Integer.parseInt(request.getParameter("anioLanzamiento"));
        String genero = request.getParameter("genero");

        Vinilo v = new Vinilo();
        v.setTitulo(titulo);
        v.setArtista(artista);
        v.setAnioLanzamiento(anio);
        v.setGenero(genero);

        if (idStr == null || idStr.isEmpty()) {
            viniloDAO.agregar(v);
        } else {
            v.setIdVinilo(Integer.parseInt(idStr));
            viniloDAO.actualizar(v);
        }
        response.sendRedirect("vinilos");
    }
}