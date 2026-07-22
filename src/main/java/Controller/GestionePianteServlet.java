package Controller;

import Model.Beans.Categoria;
import Model.Beans.Pianta;
import Model.DAO.CategoriaDAO;
import Model.DAO.PiantaDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class GestionePianteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();
        PiantaDAO piantaDAO = new PiantaDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        try {
            if ("/admin/AggiungiPianta".equalsIgnoreCase(path)) {
                // MOSTRA FORM PER NUOVA PIANTA
                List<Categoria> categorie = categoriaDAO.doRetrieveAll();
                request.setAttribute("categorie", categorie);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/form_pianta.jsp").forward(request, response);

            } else if ("/admin/ModificaPianta".equalsIgnoreCase(path)) {
                // MOSTRA FORM DI MODIFICA
                int idPianta = Integer.parseInt(request.getParameter("idPianta"));
                Pianta pianta = piantaDAO.doRetrieveById(idPianta);
                List<Categoria> categorie = categoriaDAO.doRetrieveAll();

                request.setAttribute("piantaDaModificare", pianta);
                request.setAttribute("categorie", categorie);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/form_pianta.jsp").forward(request, response);

            } else {
                // LISTA DI TUTTE LE PIANTE (/admin/GestionePiante)
                List<Pianta> piante = piantaDAO.doRetrieveAll();
                request.setAttribute("piante", piante);
                request.getRequestDispatcher("/WEB-INF/jsp/admin/lista_piante.jsp").forward(request, response);
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nella gestione delle piante.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String idPiantaStr = request.getParameter("idPianta");
        String nomeComune = request.getParameter("nomeComune");
        String nomeScientifico = request.getParameter("nomeScientifico");
        String descrizione = request.getParameter("descrizione");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String esposizione = request.getParameter("esposizione");

        double altezza = 0.0;
        if (request.getParameter("altezza") != null && !request.getParameter("altezza").isEmpty()) {
            altezza = Double.parseDouble(request.getParameter("altezza"));
        }

        double diametroVaso = 0.0;
        if (request.getParameter("diametroVaso") != null && !request.getParameter("diametroVaso").isEmpty()) {
            diametroVaso = Double.parseDouble(request.getParameter("diametroVaso"));
        }

        int quantitaMagazzino = Integer.parseInt(request.getParameter("quantitaMagazzino"));
        boolean disponibile = request.getParameter("disponibile") != null;

        // Recupero Categoria
        String idCategoriaParam = request.getParameter("idCategoria");
        Categoria categoriaObj = null;
        CategoriaDAO categoriaDAO = new CategoriaDAO();

        try {
            if (idCategoriaParam != null && !idCategoriaParam.trim().isEmpty()) {
                int idCategoria = Integer.parseInt(idCategoriaParam);
                categoriaObj = categoriaDAO.doRetrieveById(idCategoria);
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (categoriaObj == null) {
            categoriaObj = new Categoria();
        }

        // Costruzione Bean Pianta
        Pianta p = new Pianta();
        p.setCategoria(categoriaObj);
        p.setNomeComune(nomeComune);
        p.setNomeScientifico(nomeScientifico);
        p.setDescrizione(descrizione);
        p.setPrezzo(prezzo);
        p.setEsposizione(esposizione);
        p.setAltezza(altezza);
        p.setDiametroVaso(diametroVaso);
        p.setQuantitaMagazzino(quantitaMagazzino);
        p.setDisponibile(disponibile);

        PiantaDAO piantaDAO = new PiantaDAO();
        try {
            if (idPiantaStr != null && !idPiantaStr.trim().isEmpty()) {
                // UPDATE
                p.setIdPianta(Integer.parseInt(idPiantaStr));
                piantaDAO.doUpdate(p);
            } else {
                // INSERT
                piantaDAO.doInsert(p);
            }
            // Dopo il salvataggio torniamo sempre alla lista delle piante
            response.sendRedirect(request.getContextPath() + "/admin/GestionePiante");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Errore nel salvataggio della pianta.");
        }
    }
}
