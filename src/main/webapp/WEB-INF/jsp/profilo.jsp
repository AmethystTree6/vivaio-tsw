<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Il mio Profilo</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<main class="container" style="max-width: 600px; margin: 40px auto; padding: 20px;">
    <h2>Gestione Profilo</h2>
    <p style="color: #666;">Qui puoi modificare i tuoi dati personali.</p>

    <!-- Messaggi di feedback -->
    <c:if test="${not empty errore}">
        <div style="background-color: #ffebee; color: #c62828; padding: 12px; border-radius: 4px; margin-bottom: 20px;">
                ${errore}
        </div>
    </c:if>

    <c:if test="${not empty messaggioSuccesso}">
        <div style="background-color: #e8f5e9; color: #2e7d32; padding: 12px; border-radius: 4px; margin-bottom: 20px;">
                ${messaggioSuccesso}
        </div>
    </c:if>

    <form action="${ctx}/ModificaProfilo" method="post" style="display: flex; flex-direction: column; gap: 15px;">
        <div>
            <label for="nome" style="display: block; font-weight: bold; margin-bottom: 5px;">Nome *</label>
            <input type="text" id="nome" name="nome" value="${sessionScope.cliente.nome}" required style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px;">
        </div>

        <div>
            <label for="cognome" style="display: block; font-weight: bold; margin-bottom: 5px;">Cognome *</label>
            <input type="text" id="cognome" name="cognome" value="${sessionScope.cliente.cognome}" required style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px;">
        </div>

        <div>
            <label for="email" style="display: block; font-weight: bold; margin-bottom: 5px;">Email *</label>
            <input type="email" id="email" name="email" value="${sessionScope.cliente.email}" required style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px;">
        </div>

        <div>
            <label for="telefono" style="display: block; font-weight: bold; margin-bottom: 5px;">Telefono</label>
            <input type="tel" id="telefono" name="telefono" value="${sessionScope.cliente.telefono}" style="width: 100%; padding: 10px; border: 1px solid #ccc; border-radius: 4px;">
        </div>

        <button type="submit" style="background-color: #2e7d32; color: white; padding: 12px; border: none; border-radius: 4px; font-weight: bold; cursor: pointer; margin-top: 10px;">
            Salva Modifiche
        </button>
    </form>
</main>

</body>
</html>
