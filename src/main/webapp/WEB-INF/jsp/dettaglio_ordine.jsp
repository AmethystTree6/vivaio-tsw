<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Dettaglio Ordine #${ordineCorrente.idOrdine}</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
</head>
<body>

<!-- Inclusione della Navbar -->
<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<main class="container" style="margin-top: 40px; max-width: 900px;">
    <!-- Link per tornare indietro -->
    <a href="${ctx}/StoricoOrdini" style="color: #555; text-decoration: none; margin-bottom: 20px; display: inline-block;">&larr; Torna agli Ordini</a>

    <!-- Intestazione dell'ordine -->
    <div style="background-color: #f4f8f4; padding: 20px; border-radius: 8px; margin-bottom: 30px; border-left: 5px solid #2e7d32;">
        <h1 style="color: #2e7d32; margin-bottom: 10px; margin-top: 0;">Ordine #${ordineCorrente.idOrdine}</h1>
        <p style="color: #444; margin: 5px 0; font-size: 16px;">
            Effettuato il: <strong><fmt:formatDate value="${ordineCorrente.dataOrdine}" pattern="dd/MM/yyyy" /></strong> <br>
            Stato attuale: <strong>${ordineCorrente.stato}</strong>
        </p>
    </div>

    <!-- Tabella dei prodotti acquistati -->
    <table class="cart-table" style="width: 100%; border-collapse: collapse; background-color: white; box-shadow: 0 1px 3px rgba(0,0,0,0.1);">
        <thead>
        <tr style="background-color: #2e7d32; color: white;">
            <th style="padding: 15px; text-align: left;">Prodotto</th>
            <th style="padding: 15px; text-align: center;">Quantità</th>
            <th style="padding: 15px; text-align: right;">Prezzo Unitario</th>
            <th style="padding: 15px; text-align: right;">Subtotale</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dettaglio" items="${dettagliOrdine}">
            <tr style="border-bottom: 1px solid #ddd;">
                <td style="padding: 15px; color: #333;">
                    <strong>${dettaglio.pianta.nomeComune}</strong>
                </td>
                <td style="padding: 15px; text-align: center; color: #555;">
                        ${dettaglio.quantita}
                </td>
                <td style="padding: 15px; text-align: right; color: #555;">
                    <!-- Stampa il prezzo unitario congelato al momento dell'acquisto -->
                    <fmt:formatNumber value="${dettaglio.prezzoUnitario}" type="currency" currencySymbol="€"/>
                </td>
                <td style="padding: 15px; text-align: right; font-weight: bold; color: #333;">
                    <!-- Calcola il subtotale (Quantità x Prezzo Unitario) -->
                    <fmt:formatNumber value="${dettaglio.quantita * dettaglio.prezzoUnitario}" type="currency" currencySymbol="€"/>
                </td>
            </tr>
        </c:forEach>
        </tbody>
        <tfoot>
        <tr style="background-color: #e8f5e9;">
            <td colspan="3" style="padding: 15px; text-align: right; font-size: 18px; color: #333;">
                <strong>Totale Ordine:</strong>
            </td>
            <td style="padding: 15px; text-align: right; font-size: 18px; color: #2e7d32; font-weight: bold;">
                <!-- Stampa il totale generale dell'ordine -->
                <fmt:formatNumber value="${ordineCorrente.totale}" type="currency" currencySymbol="€"/>
            </td>
        </tr>
        </tfoot>
    </table>
</main>

<script src="${ctx}/js/main.js"></script>
</body>
</html>