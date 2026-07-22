    package Controller;

    import Model.Beans.Categoria;
    import Model.Beans.PiantaFilter;
    import Model.DAO.CategoriaDAO;
    import Model.DAO.PiantaDAO;
    import Model.Beans.Pianta;
    import jakarta.servlet.RequestDispatcher;
    import jakarta.servlet.ServletException;
    import jakarta.servlet.annotation.WebServlet;
    import jakarta.servlet.http.HttpServlet;
    import jakarta.servlet.http.HttpServletRequest;
    import jakarta.servlet.http.HttpServletResponse;

    import java.io.IOException;
    import java.sql.SQLException;
    import java.util.List;

    @WebServlet("/Catalogo")
    public class CatalogoServlet extends HttpServlet {
        private static final long serialVersionUID = 1L;

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {

            PiantaFilter filter = new PiantaFilter();
            filter.setQuery(request.getParameter("q"));
            filter.setEsposizione(request.getParameter("esposizione"));
            filter.setOrderBy(request.getParameter("orderBy"));

            try {
                if (request.getParameter("categoria") != null && !request.getParameter("categoria").isEmpty()) {
                    filter.setIdCategoria(Integer.parseInt(request.getParameter("categoria")));
                }
                if (request.getParameter("prezzoMin") != null && !request.getParameter("prezzoMin").isEmpty()) {
                    filter.setPrezzoMin(Double.parseDouble(request.getParameter("prezzoMin")));
                }
                if (request.getParameter("prezzoMax") != null && !request.getParameter("prezzoMax").isEmpty()) {
                    filter.setPrezzoMax(Double.parseDouble(request.getParameter("prezzoMax")));
                }
            } catch (NumberFormatException ignored) {}

            PiantaDAO piantaDAO = new PiantaDAO();
            CategoriaDAO categoriaDAO = new CategoriaDAO();

            try {
                List<Pianta> prodotti = piantaDAO.doFilter(filter);
                List<Categoria> categorie = categoriaDAO.doRetrieveAll();

                request.setAttribute("prodotti", prodotti);
                request.setAttribute("categorie", categorie);

            } catch (SQLException e) {
                e.printStackTrace();
                request.setAttribute("errore", "Errore nel caricamento dei prodotti.");
            }

            request.getRequestDispatcher("/WEB-INF/jsp/catalogo.jsp").forward(request, response);
        }

        @Override
        protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
            doGet(request, response);
        }
    }