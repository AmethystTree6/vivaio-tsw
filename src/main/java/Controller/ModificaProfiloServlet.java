package Controller;

import Model.Beans.Utente;
import Model.DAO.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/ModificaProfilo")
public class ModificaProfiloServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("cliente");

        // Controllo di sicurezza: l'utente dev'essere loggato
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Mostra la pagina col form pre-compilato
        request.getRequestDispatcher("/WEB-INF/jsp/profilo.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Utente utente = (Utente) session.getAttribute("cliente");

        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        // Recuperiamo i dati inviati dal form
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");

        // Validazione base
        if (nome == null || nome.trim().isEmpty() ||
                cognome == null || cognome.trim().isEmpty() ||
                email == null || email.trim().isEmpty()) {

            request.setAttribute("errore", "Nome, Cognome ed Email sono campi obbligatori.");
            request.getRequestDispatcher("/WEB-INF/jsp/profilo.jsp").forward(request, response);
            return;
        }

        // Aggiorniamo i dati dell'oggetto Utente
        utente.setNome(nome.trim());
        utente.setCognome(cognome.trim());
        utente.setEmail(email.trim());
        utente.setTelefono(telefono != null ? telefono.trim() : "");

        // Eseguiamo l'update sul Database usando il tuo DAO
        UtenteDAO utenteDAO = new UtenteDAO();
        boolean success = utenteDAO.doUpdate(utente);

        if (success) {
            // FONDAMENTALE: aggiorniamo l'utente in sessione con i nuovi dati
            session.setAttribute("cliente", utente);
            request.setAttribute("messaggioSuccesso", "Profilo aggiornato con successo!");
        } else {
            request.setAttribute("errore", "Errore durante l'aggiornamento nel database.");
        }

        request.getRequestDispatcher("/WEB-INF/jsp/profilo.jsp").forward(request, response);
    }
}
