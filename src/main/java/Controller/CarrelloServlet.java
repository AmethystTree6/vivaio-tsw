package Controller;


import Model.Beans.Carrello;
import Model.Beans.Pianta;
import Model.DAO.PiantaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/GestioneCarrello")
public class CarrelloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Diciamo al browser che risponderemo con un file JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String action = request.getParameter("action");
        HttpSession session = request.getSession();

        // 1. Recuperiamo il carrello dalla sessione, o ne creiamo uno nuovo se è la prima volta
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null) {
            carrello = new Carrello();
            session.setAttribute("carrello", carrello);
        }

        try {
            if ("aggiungi".equals(action)) {
                int idPianta = Integer.parseInt(request.getParameter("id"));
                PiantaDAO dao = new PiantaDAO();

                // 2. Cerchiamo la pianta nel DB per prendere il prezzo e i dati aggiornati
                Pianta pianta = dao.doRetrieveById(idPianta);

                if (pianta != null) {
                    // 3. La aggiungiamo al carrello
                    carrello.aggiungiPianta(pianta);

                    // 4. Rispondiamo con un JSON di successo
                    out.print("{\"success\": true, \"numeroArticoli\": " + carrello.getNumeroArticoli() + "}");
                } else {
                    out.print("{\"success\": false, \"errore\": \"Pianta non trovata nel database\"}");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            out.print("{\"success\": false, \"errore\": \"Errore di connessione al database\"}");
        } catch (NumberFormatException e) {
            out.print("{\"success\": false, \"errore\": \"ID pianta non valido\"}");
        } finally {
            out.flush();
        }
    }
}

