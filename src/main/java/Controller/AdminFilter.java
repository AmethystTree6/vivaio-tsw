package Controller;

import java.io.IOException;

import Model.Beans.Utente;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminFilter  implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);

        Utente utente = (session != null) ? (Utente) session.getAttribute("cliente") : null;

        // Se non è loggato o il ruolo non è "admin", blocchiamo l'accesso
        if (utente == null || !"admin".equalsIgnoreCase(utente.getRuolo())) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN, "Accesso negato: Area riservata agli amministratori.");
            return;
        }

        // Se è admin, la richiesta prosegue normalmente
        chain.doFilter(request, response);
    }
}
