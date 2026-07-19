<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VivaioMente - Catalogo Piante</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">

    <style>
        /* Griglia base per le card (se non l'hai già nel tuo style.css) */
        .products-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 25px;
            margin-top: 20px;
        }
        .product-card {
            background: var(--white);
            border: 1px solid var(--border-color);
            border-radius: var(--border-radius);
            padding: 15px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0,0,0,0.05);
        }
        .product-image-container {
            height: 200px;
            overflow: hidden;
            border-radius: 6px;
            margin-bottom: 15px;
            background-color: #f9f9f9;
        }
        .product-image-container img {
            width: 100%;
            height: 100%;
            object-fit: cover; /* Taglia l'immagine senza deformarla */
        }
    </style>
</head>
<body>

<!-- INCLUSIONE DELLA NAVBAR -->
<%@ include file="/WEB-INF/jsp/navbar.jsp" %>

<main class="container">
    <h1 style="text-align: center; color: var(--primary-color); margin-top: 30px;">Le Nostre Piante</h1>

    <div class="products-grid">
        <c:choose>
            <c:when test="${not empty requestScope.prodotti}">
                <!-- Ciclo JSTL che sostituisce il for Java -->
                <c:forEach var="pianta" items="${requestScope.prodotti}">
                    <div class="product-card">

                        <!-- Prendiamo le immagini esterne da una cartella in TO-DO -->
                        <div class="product-image-container">
                            <img src="${ctx}/images/piante/${pianta.idPianta}.jpg"
                                 alt="${pianta.nomeComune}"
                                 onerror="this.onerror=null; this.src='${ctx}/images/placeholder.png';">
                        </div>

                        <div class="product-info">
                            <h3>${pianta.nomeComune}</h3>
                            <p style="font-size: 13px; color: #666; font-style: italic; margin-bottom: 8px;">
                                    ${pianta.nomeScientifico}
                            </p>
                            <!-- Taglia il testo troppo lungo su 2 righe -->
                            <p style="font-size: 13px; color: #555; height: 38px; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; margin-bottom: 12px;">
                                    ${pianta.descrizione}
                            </p>


                            <p class="product-price" style="font-weight: bold; font-size: 18px; color: var(--primary-color); margin-bottom: 15px;">
                                <fmt:formatNumber value="${pianta.prezzo}" type="currency" currencySymbol="€" maxFractionDigits="2"/>
                            </p>

                            <button class="btn" style="width: 100%;" onclick="aggiungiAlCarrello(${pianta.idPianta})">
                                Aggiungi al Carrello
                            </button>
                        </div>
                    </div>
                </c:forEach>
            </c:when>

            <c:otherwise>
                <p style="grid-column: 1 / -1; text-align: center; color: #666; padding: 40px;">
                    Nessuna pianta trovata nel catalogo al momento. Torna a trovarci!
                </p>
            </c:otherwise>
        </c:choose>
    </div>
</main>

<script src="${ctx}/js/main.js"></script>
</body>
</html>