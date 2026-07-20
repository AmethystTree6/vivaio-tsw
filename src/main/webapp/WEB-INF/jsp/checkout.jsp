<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>VivaioMente - Ordine Confermato</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
</head>
<body>

<%@ include file="/WEB-INF/jsp/navbar.jsp" %>

<main class="container" style="text-align: center; margin-top: 60px; padding: 40px; background: #fff; border-radius: 8px; box-shadow: 0 4px 6px rgba(0,0,0,0.05);">
    <h1 style="color: #2e7d32; font-size: 40px; margin-bottom: 20px;">🎉 Ordine Confermato!</h1>
    <p style="font-size: 18px; color: #555; margin-bottom: 30px;">
        Grazie per il tuo acquisto! Le tue nuove piantine stanno già preparando le radici per il viaggio verso casa tua.
    </p>
    <a href="${ctx}/Catalogo" class="btn" style="padding: 15px 30px; font-size: 16px;">Torna al Catalogo</a>
</main>

</body>
</html>
