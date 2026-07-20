<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>VivaioMente - Il tuo Carrello</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
</head>
<body>

<%@ include file="/WEB-INF/jsp/navbar.jsp" %>

<main class="container">
    <h1 class="page-title" style="margin-top: 30px;">Il tuo Carrello</h1>

    <div class="cart-container">

        <c:choose>
            <%-- Se il carrello è vuoto --%>
            <c:when test="${empty sessionScope.carrello or sessionScope.carrello.numeroArticoli == 0}">
                <div class="cart-empty-message">
                    <h3 style="color: #666666;">Il tuo carrello è attualmente vuoto 🍂</h3>
                    <br>
                    <a href="${ctx}/Catalogo" class="btn" style="text-decoration: none;">Esplora il Catalogo</a>
                </div>
            </c:when>

            <%-- Se ci sono prodotti --%>
            <c:otherwise>
                <table class="cart-table">
                    <thead>
                    <tr>
                        <th>Prodotto</th>
                        <th>Prezzo Unitario</th>
                        <th style="text-align: center;">Quantità</th>
                        <th style="text-align: right;">Totale</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${sessionScope.carrello.articoli}">
                        <tr>
                            <td class="cart-product-cell">
                                <img src="${ctx}/images/piante/${item.pianta.idPianta}.jpg"
                                     class="cart-thumbnail"
                                     onerror="this.onerror=null; this.src='${ctx}/images/placeholder.png';">
                                    ${item.pianta.nomeComune}
                            </td>
                            <td class="cart-price">
                                <fmt:formatNumber value="${item.pianta.prezzo}" type="currency" currencySymbol="€"/>
                            </td>
                            <!-- Cella Quantità con i pulsanti + e - -->
                            <td style="text-align: center;">
                                <div style="display: flex; align-items: center; justify-content: center; gap: 5px;">
                                    <button type="button" onclick="modificaQuantita(${item.pianta.idPianta}, -1)"
                                            style="cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; background: #fff; border-radius: 4px;">-</button>

                                    <input type="number" value="${item.quantita}" readonly class="cart-qty-input" style="width: 40px; margin: 0;">

                                    <button type="button" onclick="modificaQuantita(${item.pianta.idPianta}, 1)"
                                            style="cursor: pointer; padding: 5px 10px; border: 1px solid #ccc; background: #fff; border-radius: 4px;">+</button>
                                </div>
                            </td>

                            <!-- Cella Totale con il tasto Cestino -->
                            <td class="cart-item-total">
                                <fmt:formatNumber value="${item.prezzoTotale}" type="currency" currencySymbol="€"/>

                                <button type="button" onclick="rimuoviProdotto(${item.pianta.idPianta})"
                                        style="margin-left: 15px; color: #d32f2f; background: none; border: none; cursor: pointer; font-size: 18px;"
                                        title="Rimuovi dal carrello">🗑️</button>
                            </td>
                            <td class="cart-item-total">
                                <fmt:formatNumber value="${item.prezzoTotale}" type="currency" currencySymbol="€"/>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div class="cart-footer">
                    <div>
                        <a href="${ctx}/Catalogo" class="back-to-shopping">&larr; Continua lo shopping</a>
                    </div>
                    <div style="text-align: right;">
                        <p class="cart-total-text">
                            Totale parziale:
                            <span class="cart-total-amount">
                                <fmt:formatNumber value="${sessionScope.carrello.totale}" type="currency" currencySymbol="€"/>
                            </span>
                        </p>

                        <form action="${ctx}/Checkout" method="GET" style="margin: 0;">
                            <button type="submit" class="btn" style="padding: 12px 25px; font-size: 15px; cursor: pointer;">
                                Procedi al checkout
                            </button>
                        </form>
                    </div>
                </div>
            </c:otherwise>
        </c:choose>

    </div>
</main>

<script src="${ctx}/js/main.js"></script>
</body>
</html>