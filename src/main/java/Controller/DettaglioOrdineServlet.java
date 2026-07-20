package Controller;

import Model.Beans.DettaglioOrdine;
import Model.Beans.Ordine;
import Model.Beans.Utente;
import Model.DAO.DettaglioOrdineDAO;
import Model.DAO.OrdineDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/DettaglioOrdine")
public class DettaglioOrdineServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Recuperiamo l'utente dalla sessione (usando la chiave "cliente" come nel tuo Login)
        Utente utente = (Utente) session.getAttribute("cliente");

        // Muro di sicurezza: se l'utente non è loggato, rimandiamo alla pagina di login
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        int idOrdine;
        try {
            // Recuperiamo l'ID dell'ordine cliccato dall'URL (?id=...)
            idOrdine = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            // Se l'ID non è un numero valido, rimandiamo l'utente allo storico generale
            response.sendRedirect(request.getContextPath() + "/StoricoOrdini");
            return;
        }

        // Inizializziamo entrambi i DAO rispettando la regola "1 Tabella = 1 DAO"
        OrdineDAO ordineDAO = new OrdineDAO();
        DettaglioOrdineDAO dettaglioDAO = new DettaglioOrdineDAO();

        try {
            // FASE 1: CONTROLLO DI SICUREZZA
            // Verifichiamo che l'ordine richiesto appartenga effettivamente all'utente loggato
            boolean isOwner = false;
            List<Ordine> iMieiOrdini = ordineDAO.getOrdiniByUtente(utente.getIdUtente());

            for (Ordine o : iMieiOrdini) {
                if (o.getIdOrdine() == idOrdine) {
                    isOwner = true;
                    // Salviamo i dati generali dell'ordine (data, stato, totale) per la JSP
                    request.setAttribute("ordineCorrente", o);
                    break;
                }
            }

            // Se l'ordine non è suo (es. ha cambiato a mano l'ID nell'URL), blocchiamo tutto
            if (!isOwner) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Non sei autorizzato a visualizzare questo ordine.");
                return;
            }

            // FASE 2: RECUPERO DEI DETTAGLI
            // Se i controlli sono passati, peschiamo i dettagli dal nuovo DAO specifico
            List<DettaglioOrdine> dettagli = dettaglioDAO.getDettagliByOrdine(idOrdine);
            request.setAttribute("dettagliOrdine", dettagli);

            // FASE 3: INOLTRO ALLA VISTA
            request.getRequestDispatcher("/WEB-INF/jsp/dettaglio_ordine.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            // In caso di errore col database, mostriamo l'errore 500
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel recupero dei dettagli dell'ordine.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Se per caso arriva una richiesta POST, la gestiamo come una GET
        doGet(request, response);
    }
}
