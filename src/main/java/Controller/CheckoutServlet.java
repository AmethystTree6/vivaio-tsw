package Controller;

import Model.Beans.Carrello;
import Model.Beans.Utente;
import Model.DAO.OrdineDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/Checkout")
public class CheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        //Check login utente
        Utente utente = (Utente) session.getAttribute("cliente");
        if (utente == null) {
            response.sendRedirect(request.getContextPath() + "/login.jsp");
            return;
        }

        //Recupero del carrello, se vuoto si ritorna al catalogo
        Carrello carrello = (Carrello) session.getAttribute("carrello");
        if (carrello == null || carrello.getNumeroArticoli() == 0) {
            response.sendRedirect(request.getContextPath() + "/Catalogo");
            return;
        }

        //Salvataggio in db
        OrdineDAO ordineDAO = new OrdineDAO();
        try {
            ordineDAO.salvaOrdineCompleto(utente.getIdUtente(), carrello);

            session.removeAttribute("carrello");

            request.getRequestDispatcher("/WEB-INF/jsp/grazie.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errore", "Si è verificato un problema durante la registrazione dell'ordine.");
            request.getRequestDispatcher("/WEB-INF/jsp/carrello.jsp").forward(request, response);
        }
    }
}
