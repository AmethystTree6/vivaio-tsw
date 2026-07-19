package Controller;

import Model.Beans.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Recuperiamo l'oggetto utente usando il Bean corretto (Utente)
        Utente utenteLoggato = (Utente) session.getAttribute("cliente");

        if (utenteLoggato == null) {
            // Se l'utente non è loggato, lo rimbalziamo al login inserendo un messaggio di errore
            request.setAttribute("messaggioErrore", "Accedi al tuo account personale per poter completare l'acquisto.");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            // Se è loggato, può procedere alla pagina di checkout reale
            request.getRequestDispatcher("/checkout.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}