<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <!-- tag viewport aiuta a rendere il sito responsive anche sui telefoni, permettendo un migliore resize -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>VivaioMente - Home</title>

    <!-- Link al CSS usando il contextPath per percorsi assoluti sicuri -->
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<!-- INCLUSIONE DELLA NAVBAR -->
<%@ include file="WEB-INF/jsp/navbar.jsp" %>

<!-- CORPO CENTRALE -->
<main class="hero-section">
    <h1>Benvenuto su VivaioMente!</h1>
    <p>Il tuo e-commerce di fiducia per piante da interno ed esterno.</p>

    <!-- Un bottone per spingere l'utente verso il catalogo -->
    <a href="${pageContext.request.contextPath}/Catalogo" class="btn">Esplora il Catalogo</a>
</main>

<!-- INCLUSIONE DEL FOOTER (TO-DO) -->
<%-- <%@ include file="WEB-INF/jsp/footer.jsp" %> --%>

<!-- Aggiunta dello script di login -->
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</body>
</html>