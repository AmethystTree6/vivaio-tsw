
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VivaioMente - Registrazione</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">

    <style> /*Aggiungiamo css specifico solo a questa jsp come file interno, invece di un file esterno*/
        .auth-container {
            max-width: 500px;
            margin: 40px auto;
            background: var(--white);
            padding: 30px;
            border-radius: var(--border-radius);
            box-shadow: 0 4px 10px rgba(0,0,0,0.1);
        }
        .form-group { margin-bottom: 20px; }
        .form-group label { display: block; margin-bottom: 5px; font-weight: bold; }
        .form-group input {
            width: 100%; padding: 10px;
            border: 1px solid var(--border-color);
            border-radius: var(--border-radius);
            outline: none;
        }
        .form-group input:focus { border-color: var(--primary-color); }
        .error-msg { color: red; font-size: 13px; margin-top: 5px; display: none; }
    </style>
</head>
<body>

<!-- NAVBAR -->
<%@ include file="WEB-INF/jsp/navbar.jsp" %>

<main class="container">
    <div class="auth-container">
        <h2 style="text-align: center; color: var(--primary-color); margin-bottom: 20px;">Crea un Account</h2>

        <!-- messaggio di errore alternativo in caso di JScript disabilitato -->
        <c:if test="${not empty requestScope.erroreReg}">
            <div style="color: red; text-align: center; margin-bottom: 15px; font-weight: bold;">
                    ${requestScope.erroreReg}
            </div>
        </c:if>

        <form id="registrazioneForm" action="${ctx}/Registrazione" method="POST">
            <div class="form-group">
                <label for="reg-nome">Nome</label>
                <input type="text" id="reg-nome" name="nome" required>
            </div>

            <div class="form-group">
                <label for="reg-cognome">Cognome</label>
                <input type="text" id="reg-cognome" name="cognome" required>
            </div>

            <div class="form-group">
                <label for="reg-email">Email</label>
                <input type="email" id="reg-email" name="email" required>
                <!-- Questo span verrà riempito da AJAX -->
                <div id="email-feedback" class="error-msg">Email già in uso!</div>
            </div>
            <div class="form-group">
                <label for="reg-pwd">Password</label>
                <!-- Pattern che accetta QUALSIASI carattere (inclusi simboli e caratteri speciali) -->
                <input type="password" id="reg-pwd" name="password"
                       pattern="^(?=.*[A-Za-z])(?=.*\d).{8,}$"
                       title="La password deve contenere almeno 8 caratteri, di cui almeno una lettera e un numero" required>
            </div>

            <div class="form-group">
                <label for="reg-pwd-confirm">Conferma Password</label>
                <input type="password" id="reg-pwd-confirm" required>
                <div id="pwd-feedback" class="error-msg">Le password non combaciano!</div>
            </div>

            <button type="submit" id="btn-registrati" class="btn" style="width: 100%; margin-top: 10px;">Registrati</button>
        </form>

        <p style="text-align: center; margin-top: 15px; font-size: 14px;">
            Hai già un account? <a href="${ctx}/login.jsp" style="color: var(--primary-color); font-weight: bold;">Accedi</a>
        </p>
    </div>
</main>

<script src="${ctx}/js/main.js"></script>
</body>
</html>