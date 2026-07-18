package it.unisa.progetto.controller;

import it.unisa.progetto.model.bean.Prodotto;
import it.unisa.progetto.model.dao.ProdottoDAO;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/TestDb")
public class TestDbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>🌱 Test Connessione Vivaio DB</h2>");
        out.println("<hr>");

        ProdottoDAO dao = new ProdottoDAO();
        try {
            // Chiamiamo il metodo doRetrieveAll che hai appena scritto!
            Collection<Prodotto> piante = dao.doRetrieveAll(null);

            if (piante.isEmpty()) {
                out.println("<p style='color: orange;'>Connessione riuscita, ma la tabella prodotti è vuota!</p>");
            } else {
                out.println("<p style='color: green; font-weight: bold;'>Connessione riuscita al 100%!</p>");
                out.println("<h3>Lista piante caricate dal Database:</h3>");
                out.println("<ul>");
                for (Prodotto p : piante) {
                    out.println("<li><strong>" + p.getNome() + "</strong> - " + p.getPrezzo() + " &euro; (" + p.getAmbiente() + ")</li>");
                }
                out.println("</ul>");
            }
        } catch (Exception e) {
            out.println("<p style='color: red; font-weight: bold;'>Errore durante la connessione al Database!</p>");
            out.println("<pre>");
            e.printStackTrace(out);
            out.println("</pre>");
        }

        out.println("</body></html>");
    }
}