<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VivaioMente - Accesso e Registrazione</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<!-- Inclusione della Navbar -->
<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<main class="container" style="max-width: 600px; margin-top: 50px;">
    <h1 class="page-title" style="text-align: center;">Area Utente</h1>

    <div style="background-color: #ffffff; padding: 30px; border: 1px solid #e0e0e0; border-radius: 6px; box-shadow: 0 2px 4px rgba(0,0,0,0.04);">
        <!-- Form di Login Principale -->
        <%
            String msgErrore = (String) request.getAttribute("messaggioErrore");
            if (msgErrore != null) {
        %>
        <div style="background-color: #ffebee; color: #c62828; padding: 12px; border-radius: 4px; border: 1px solid #ef9a9a; font-size: 14px; margin-bottom: 20px; font-weight: 500;">
            <%= msgErrore %>
        </div>
        <%
            }
        %>
        <form action="Login" method="POST" style="margin-bottom: 30px;">
            <h2 style="color: #1b5e20; font-size: 20px; margin-bottom: 20px;">Accedi al tuo profilo</h2>

            <div class="dropdown-form-group" style="margin-bottom: 15px;">
                <label style="display: block; margin-bottom: 5px; font-size: 14px; color: #333;">Username o Email</label>
                <input type="text" name="username" placeholder="Inserisci il tuo username" required style="width: 100%; padding: 10px; border: 1px solid #cccccc; border-radius: 4px;">
            </div>

            <div class="dropdown-form-group" style="margin-bottom: 20px;">
                <label style="display: block; margin-bottom: 5px; font-size: 14px; color: #333;">Password</label>
                <input type="password" name="password" placeholder="Inserisci la tua password" required style="width: 100%; padding: 10px; border: 1px solid #cccccc; border-radius: 4px;">
            </div>

            <button type="submit" class="dropdown-login-btn" style="padding: 12px; font-size: 15px;">Accedi</button>
        </form>

        <hr class="dropdown-divider">

        <!-- Sezione di invito alla registrazione -->
        <div style="text-align: center; margin-top: 20px;">
            <h3 style="font-size: 16px; color: #555555; margin-bottom: 10px;">Non hai ancora un account?</h3>
            <p style="font-size: 14px; color: #666666; margin-bottom: 15px;">Registrati per tracciare i tuoi ordini e salvare le tue piante preferite.</p>
            <a href="registrazione.jsp" style="display: inline-block; background-color: #2e7d32; color: white; text-decoration: none; padding: 10px 20px; border-radius: 4px; font-weight: 600; font-size: 14px; transition: background-color 0.2s;">
                Crea un nuovo account
            </a>
        </div>
    </div>
</main>

<script src="js/login.js"></script>
</body>
</html>