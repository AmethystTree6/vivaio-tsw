package Controller;

import Model.DAO.PiantaDAO;
import Model.Beans.Pianta;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/Catalogo")
public class CatalogoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PiantaDAO dao = new PiantaDAO();
        String query = request.getParameter("query");
        List<Pianta> piante = null;

        try {
            // Gestione obbligatoria dell'eccezione lanciata dai metodi del DAO
            if (query != null && !query.trim().isEmpty()) {
                piante = dao.doSearch(query);
            } else {
                piante = dao.doRetrieveAll();
            }
        } catch (SQLException e) {
            request.setAttribute("errore", "Errore nel database durante il caricamento dei prodotti.");
            e.printStackTrace();
        }

        if (piante == null || piante.isEmpty()) {
            request.setAttribute("errore", "Nessun prodotto trovato nel catalogo.");
        }

        request.setAttribute("prodotti", piante);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/catalogo.jsp");        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}