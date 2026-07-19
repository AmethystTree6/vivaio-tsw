package Controller;

import Model.DAO.UtenteDAO;
import Model.Beans.Utente;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        String xRequestedWith = request.getHeader("X-Requested-With");
        boolean isAjax = "XMLHttpRequest".equals(xRequestedWith);

        UtenteDAO dao = new UtenteDAO();
        Utente utente = dao.doRetrieveByEmail(email);

        // Verifica delle credenziali con hash db
        if (utente != null && utente.getPasswordHash() != null && utente.getPasswordHash().equals(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("cliente", utente);

            if (isAjax) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"status\":\"success\",\"username\":\"" + utente.getNome() + "\"}");
            } else {
                response.sendRedirect("index.jsp");
            }
        } else {
            if (isAjax) {
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"status\":\"error\",\"message\":\"Email o password non valide.\"}");
            } else {
                request.setAttribute("messaggioErrore", "Email o password errati.");
                request.getRequestDispatcher("/login.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}