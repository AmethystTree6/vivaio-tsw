package Controller;

import Model.Beans.Utente;
import Model.DAO.UtenteDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/VerificaEmail")
public class VerificaEmailServlet extends HttpServlet { private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        if (email == null || email.trim().isEmpty()) {
            out.print("{\"esiste\": false}");
            return;
        }

        UtenteDAO dao = new UtenteDAO();
        Utente utenteEsistente = dao.doRetrieveByEmail(email);

        // Se l'utente non è null, l'email esiste già
        if (utenteEsistente != null) {
            out.print("{\"esiste\": true}");
        } else {
            out.print("{\"esiste\": false}");
        }
        out.flush();
    }
}
