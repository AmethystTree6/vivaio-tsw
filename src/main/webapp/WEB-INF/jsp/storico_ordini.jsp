Java

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>VivaioMente - Storico Ordini</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
    <style>
        .badge-stato { padding: 5px 10px; border-radius: 20px; font-size: 12px; font-weight: bold; }
        .stato-preparazione { background-color: #fff3cd; color: #856404; }
        .stato-spedito { background-color: #cce5ff; color: #004085; }
        .stato-consegnato { background-color: #d4edda; color: #155724; }
    </style>
</head>
<body>

<%@ include file="/WEB-INF/jsp/navbar.jsp" %>

<main class="container" style="margin-top: 40px;">
    <h1 style="color: #2e7d32; margin-bottom: 30px;">I miei Ordini</h1>

    <c:choose>
        <c:when test="${empty storicoOrdini}">
            <!-- Se la lista è vuota -->
            <div style="text-align: center; padding: 40px; background: #f9f9f9; border-radius: 8px;">
                <h3 style="color: #555;">Non hai ancora effettuato ordini.</h3>
                <a href="${ctx}/Catalogo" class="btn" style="margin-top: 15px;">Vai al Catalogo</a>
            </div>
        </c:when>
        <c:otherwise>
            <!-- Se ci sono ordini, mostriamo la tabella -->
            <table class="cart-table" style="width: 100%; border-collapse: collapse;">
                <thead>
                <tr style="background-color: #2e7d32; color: white;">
                    <th style="padding: 15px; text-align: left;">N. Ordine</th>
                    <th style="padding: 15px; text-align: left;">Data</th>
                    <th style="padding: 15px; text-align: center;">Stato</th>
                    <th style="padding: 15px; text-align: right;">Totale</th>
                    <th style="padding: 15px; text-align: center;">Dettagli</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="ordine" items="${storicoOrdini}">
                    <tr style="border-bottom: 1px solid #ddd;">
                        <td style="padding: 15px;">#${ordine.idOrdine}</td>
                        <td style="padding: 15px;">
                            <fmt:formatDate value="${ordine.dataOrdine}" pattern="dd/MM/yyyy" />
                        </td>
                        <td style="padding: 15px; text-align: center;">
                            <!-- Assegniamo il colore in base allo stato -->
                            <c:choose>
                                <c:when test="${ordine.stato == 'Consegnato'}">
                                    <span class="badge-stato stato-consegnato">Consegnato</span>
                                </c:when>
                                <c:when test="${ordine.stato == 'Spedito'}">
                                    <span class="badge-stato stato-spedito">Spedito</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="badge-stato stato-preparazione">${ordine.stato}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td style="padding: 15px; text-align: right; font-weight: bold;">
                            <fmt:formatNumber value="${ordine.totale}" type="currency" currencySymbol="€"/>
                        </td>
                        <td style="padding: 15px; text-align: center;">
                            <!-- Il pulsante Dettagli lo faremo funzionare dopo! -->
                            <a href="${ctx}/DettaglioOrdine?id=${ordine.idOrdine}" style="color: #2e7d32; text-decoration: underline;">Vedi Dettagli</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</main>

</body>
</html>