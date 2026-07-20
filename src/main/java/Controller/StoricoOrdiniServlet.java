package Controller;

import jakarta.servlet.annotation.WebServlet;
import Model.Beans.Utente;
import Model.Beans.Ordine;
import Model.DAO.OrdineDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/StoricoOrdini")
public class StoricoOrdiniServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("cliente");

        // Muro di sicurezza: se non sei loggato, vai al login
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        OrdineDAO ordineDAO = new OrdineDAO();
        try {
            // Peschiamo gli ordini dell'utente
            List<Ordine> ordini = ordineDAO.getOrdiniByUtente(utente.getIdUtente());

            // Passiamo gli ordini alla JSP
            request.setAttribute("storicoOrdini", ordini);
            request.getRequestDispatcher("/WEB-INF/jsp/storico_ordini.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            // Gestione errore base
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel recupero dello storico.");
        }
    }
}
