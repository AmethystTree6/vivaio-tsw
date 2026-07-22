<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Catalogo Piante - Admin</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<main class="container" style="max-width: 1100px; margin: 40px auto; padding: 20px;">

    <div style="display: flex; justify-content: space-between; align-items: center; margin-bottom: 25px;">
        <div>
            <a href="${ctx}/admin/Dashboard" style="color: #2e7d32; text-decoration: none; font-weight: bold;">← Torna alla Dashboard</a>
            <h1 style="margin: 5px 0 0 0;">Gestione Catalogo Piante</h1>
        </div>

        <!-- PULSANTE PER AGGIUNGERE NUOVA PIANTA -->
        <a href="${ctx}/admin/AggiungiPianta" class="btn" style="background-color: #2e7d32; color: white; padding: 10px 18px; text-decoration: none; border-radius: 4px; font-weight: bold;">
            ➕ Aggiungi Nuova Pianta
        </a>
    </div>

    <!-- TABELLA PIANTE -->
    <table style="width: 100%; border-collapse: collapse; background: white; box-shadow: 0 1px 3px rgba(0,0,0,0.1);">
        <thead>
        <tr style="background-color: #2e7d32; color: white; text-align: left;">
            <th style="padding: 12px;">ID</th>
            <th style="padding: 12px;">Nome Comune</th>
            <th style="padding: 12px;">Nome Scientifico</th>
            <th style="padding: 12px;">Categoria</th>
            <th style="padding: 12px;">Prezzo</th>
            <th style="padding: 12px;">Giacenza</th>
            <th style="padding: 12px; text-align: center;">Stato</th>
            <th style="padding: 12px; text-align: center;">Azione</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${piante}">
            <tr style="border-bottom: 1px solid #eee;">
                <td style="padding: 12px;"><strong>#${p.idPianta}</strong></td>
                <td style="padding: 12px; font-weight: bold;">${p.nomeComune}</td>
                <td style="padding: 12px;"><em>${p.nomeScientifico}</em></td>
                <td style="padding: 12px;">
                    <c:choose>
                        <c:when test="${not empty p.categoria.nomeCategoria}">
                            ${p.categoria.nomeCategoria}
                        </c:when>
                        <c:otherwise>
                            <em>Non specificata</em>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="padding: 12px;"><fmt:formatNumber value="${p.prezzo}" type="currency" currencySymbol="€"/></td>
                <td style="padding: 12px;">${p.quantitaMagazzino} pz</td>
                <td style="padding: 12px; text-align: center;">
                        <span style="color: ${p.disponibile ? '#2e7d32' : '#c62828'}; font-weight: bold;">
                                ${p.disponibile ? 'Disponibile' : 'Non Disponibile'}
                        </span>
                </td>
                <td style="padding: 12px; text-align: center;">
                    <a href="${ctx}/admin/ModificaPianta?idPianta=${p.idPianta}" style="background-color: #1976d2; color: white; padding: 6px 12px; text-decoration: none; border-radius: 4px; font-size: 13px; font-weight: bold;">
                        ✏️ Modifica
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

</body>
</html>