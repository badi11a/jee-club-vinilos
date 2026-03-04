package cl.club.vinilos.controlador;

import cl.club.vinilos.dao.ViniloDAO;
import cl.club.vinilos.modelo.Vinilo;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/vinilos")
public class ViniloServlet extends HttpServlet {
    
    private ViniloDAO viniloDAO = new ViniloDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");
        
        if (accion != null) {
            if (accion.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Vinilo vinilo = viniloDAO.obtenerPorId(id);
                request.setAttribute("vinilo", vinilo);
                request.getRequestDispatcher("formulario-vinilo.jsp").forward(request, response);
                return;
            } else if (accion.equals("eliminar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                viniloDAO.eliminar(id);
                response.sendRedirect("vinilos");
                return;
            }
        }
        
        List<Vinilo> listaVinilos = viniloDAO.listarTodos();
        request.setAttribute("vinilos", listaVinilos);
        request.getRequestDispatcher("catalogo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String idStr = request.getParameter("id");
        String titulo = request.getParameter("titulo");
        String artista = request.getParameter("artista");
        int anio = Integer.parseInt(request.getParameter("anio_lanzamiento"));
        String genero = request.getParameter("genero");

        if (idStr != null && !idStr.isEmpty()) {
            int id = Integer.parseInt(idStr);
            Vinilo viniloActualizado = new Vinilo(id, titulo, artista, anio, genero);
            viniloDAO.actualizar(viniloActualizado);
        } else {
            Vinilo nuevoVinilo = new Vinilo(0, titulo, artista, anio, genero);
            viniloDAO.agregar(nuevoVinilo);
        }

        response.sendRedirect("vinilos");
    }
}