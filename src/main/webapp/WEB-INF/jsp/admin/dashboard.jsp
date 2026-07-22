<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Pannello Amministratore - Vivaio</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<main class="container" style="max-width: 1100px; margin: 40px auto; padding: 20px;">
    <h1 style="color: #2e7d32;">Pannello di Controllo Amministratore</h1>

    <div style="margin-bottom: 30px; display: flex; gap: 15px;">
        <a href="${ctx}/admin/GestionePiante" class="btn" style="background-color: #1b5e20; color: white; padding: 10px 18px; text-decoration: none; border-radius: 4px; font-weight: bold;">
            🌿 Gestisci Catalogo Piante
        </a>
        <a href="${ctx}/admin/AggiungiPianta" class="btn" style="background-color: #2e7d32; color: white; padding: 10px 18px; text-decoration: none; border-radius: 4px; font-weight: bold;">
            ➕ Aggiungi Nuova Pianta
        </a>
    </div>

    <h2>Tutti gli Ordini dei Clienti</h2>

    <table style="width: 100%; border-collapse: collapse; margin-top: 15px; background: white; box-shadow: 0 1px 3px rgba(0,0,0,0.1);">
        <thead>
        <tr style="background-color: #2e7d32; color: white;">
            <th style="padding: 12px; text-align: left;">ID Ordine</th>
            <th style="padding: 12px; text-align: left;">Cliente (ID)</th>
            <th style="padding: 12px; text-align: center;">Data</th>
            <th style="padding: 12px; text-align: right;">Totale</th>
            <th style="padding: 12px; text-align: center;">Stato Attuale</th>
            <th style="padding: 12px; text-align: center;">Azione</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="ord" items="${ordini}">
            <tr style="border-bottom: 1px solid #eee;">
                <td style="padding: 12px;"><strong>#${ord.idOrdine}</strong></td>
                <td style="padding: 12px;">${ord.utente.nome} ${ord.utente.cognome} (ID: ${ord.utente.idUtente})</td>
                <td style="padding: 12px; text-align: center;">
                    <fmt:formatDate value="${ord.dataOrdine}" pattern="dd/MM/yyyy HH:mm" />
                </td>
                <td style="padding: 12px; text-align: right;">
                    <fmt:formatNumber value="${ord.totale}" type="currency" currencySymbol="€"/>
                </td>
                <td style="padding: 12px; text-align: center; font-weight: bold;">
                        ${ord.stato}
                </td>
                <td style="padding: 12px; text-align: center;">
                    <!-- Form per aggiornare lo stato al volo -->
                    <form action="${ctx}/admin/Dashboard" method="post" style="display: flex; gap: 5px; justify-content: center;">
                        <input type="hidden" name="idOrdine" value="${ord.idOrdine}">
                        <select name="nuovoStato" style="padding: 5px;">
                            <option value="In elaborazione" ${ord.stato == 'In elaborazione' ? 'selected' : ''}>In elaborazione</option>
                            <option value="Spedito" ${ord.stato == 'Spedito' ? 'selected' : ''}>Spedito</option>
                            <option value="Consegnato" ${ord.stato == 'Consegnato' ? 'selected' : ''}>Consegnato</option>
                            <option value="Annullato" ${ord.stato == 'Annullato' ? 'selected' : ''}>Annullato</option>
                        </select>
                        <button type="submit" style="background-color: #388e3c; color: white; border: none; padding: 5px 10px; cursor: pointer; border-radius: 3px;">
                            Salva
                        </button>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

</body>
</html>
