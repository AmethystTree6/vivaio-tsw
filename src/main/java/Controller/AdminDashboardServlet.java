package Controller;

import Model.Beans.Ordine;
import Model.DAO.OrdineDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AdminDashboardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        OrdineDAO ordineDAO = new OrdineDAO();
        try {
            // Recuperiamo tutti gli ordini del sistema
            List<Ordine> tuttiGliOrdini = ordineDAO.doRetrieveAll();
            request.setAttribute("ordini", tuttiGliOrdini);

            request.getRequestDispatcher("/WEB-INF/jsp/admin/dashboard.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel caricamento della dashboard admin.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Gestione cambio stato dell'ordine
        int idOrdine = Integer.parseInt(request.getParameter("idOrdine"));
        String nuovoStato = request.getParameter("nuovoStato");

        OrdineDAO ordineDAO = new OrdineDAO();
        try {
            ordineDAO.doUpdateStato(idOrdine, nuovoStato); // Assicurati di avere questo metodo nel tuo OrdineDAO
            response.sendRedirect(request.getContextPath() + "/admin/Dashboard");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nell'aggiornamento dello stato dell'ordine.");
        }
    }

}
