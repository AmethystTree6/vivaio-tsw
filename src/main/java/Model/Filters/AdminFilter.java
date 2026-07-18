package Model.Filters;

import jakarta.servlet.*;

import java.io.IOException;

public class AdminFilter implements Filter {

    //Classe che si inserisce nell'html e gestisce automaticamente la situazione in cui l'utente
    // è admin o meno

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
