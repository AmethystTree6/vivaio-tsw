package Controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import Model.Beans.Utente;
import Model.DAO.UtenteDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Registrazione")
public class RegistrazioneServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // validazione dei valori server side
        if (nome == null || cognome == null || email == null || password == null ||
                nome.trim().isEmpty() || cognome.trim().isEmpty() || email.trim().isEmpty() || password.trim().isEmpty()) {

            request.setAttribute("erroreReg", "Tutti i campi sono obbligatori.");
            request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
            return;
        }

        UtenteDAO dao = new UtenteDAO();

        // ricontrollo server-side della email
        if (dao.doRetrieveByEmail(email) != null) {
            request.setAttribute("erroreReg", "Email già registrata nel sistema.");
            request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
            return;
        }


        Utente nuovoUtente = new Utente();
        nuovoUtente.setNome(nome);
        nuovoUtente.setCognome(cognome);
        nuovoUtente.setEmail(email);
        nuovoUtente.setPasswordHash(password);
        nuovoUtente.setRuolo("cliente");

        // 5. Salvataggio nel DB
        boolean inserito = dao.doInsert(nuovoUtente);

        if (inserito) {
            // Effettuiamo l'auto-login! Recuperiamo l'utente appena creato dal DB per avere l'ID generato
            Utente utenteLoggato = dao.doRetrieveByEmail(email);

            HttpSession session = request.getSession();
            session.setAttribute("cliente", utenteLoggato); // Usiamo "cliente" come concordato

            // Redirect alla home page
            response.sendRedirect(request.getContextPath() + "/");
        } else {
            request.setAttribute("erroreReg", "Errore interno durante la registrazione. Riprova.");
            request.getRequestDispatcher("/registrazione.jsp").forward(request, response);
        }
    }
}
