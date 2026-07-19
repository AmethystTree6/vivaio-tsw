<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Model.Beans.Pianta" %>

<html>
<head>
    <title>VivaioMente - Catalogo Piante</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<main class="container">
    <h1 class="page-title">Le Nostre Piante</h1>

    <div class="products-grid">
        <%
            // Recuperiamo la List tipizzata passata dalla CatalogoServlet
            List<Pianta> prodotti = (List<Pianta>) request.getAttribute("prodotti");

            if (prodotti != null && !prodotti.isEmpty()) {
                for (Pianta pianta : prodotti) {
        %>
        <div class="product-card">
            <div class="product-image-container">
                <span>Anteprima Pianta</span>
            </div>
            <div class="product-info">
                <!-- Metodi getter reali del Bean Pianta del tuo collega -->
                <h3><%= pianta.getNomeComune() %></h3>
                <p style="font-size: 13px; color: #666666; margin-bottom: 8px; font-style: italic;">
                    <%= pianta.getNomeScientifico() %>
                </p>
                <p style="font-size: 13px; color: #555555; margin-bottom: 12px; height: 40px; overflow: hidden; text-overflow: ellipsis;">
                    <%= pianta.getDescrizione() %>
                </p>
                <p class="product-price"><%= String.format("%.2f", pianta.getPrezzo()) %> EUR</p>

                <button class="add-to-cart-btn" onclick="aggiungiAlCarrello(<%= pianta.getIdPianta() %>)">
                    Aggiungi al Carrello
                </button>
            </div>
        </div>
        <%
            }
        } else {
        %>
        <p style="grid-column: 1 / -1; text-align: center; color: #666666; padding: 40px;">
            Nessuna pianta trovata nel catalogo.
        </p>
        <%
            }
        %>
    </div>
</main>

<script src="js/login.js"></script>
</body>
</html>