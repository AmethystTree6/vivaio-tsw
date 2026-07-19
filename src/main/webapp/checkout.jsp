<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="Model.Beans.Utente" %>

<html>
<head>
    <title>VivaioMente - Checkout</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/navbar.jspvbar.jsp" />

<main class="container" style="max-width: 600px; margin-top: 40px;">
    <h1 class="page-title" style="color: #1b5e20; border-bottom: 2px solid #e0e0e0; padding-bottom: 10px;">Completamento Ordine</h1>

    <%
        // Recuperiamo in sicurezza l'utente reale dalla sessione
        Utente utente = (Utente) session.getAttribute("cliente");
        String nome = (utente != null) ? utente.getNome() : "";
        String cognome = (utente != null) ? utente.getCognome() : "";
        String email = (utente != null) ? utente.getEmail() : "";
        String telefono = (utente != null && utente.getTelefono() != null) ? utente.getTelefono() : "";
    %>

    <div style="background-color: #ffffff; padding: 30px; border-radius: 8px; border: 1px solid #e0e0e0; box-shadow: 0 4px 6px rgba(0,0,0,0.02);">
        <form action="InviaOrdine" method="POST">

            <h3 style="color: #2e7d32; margin-bottom: 15px;">Dati di Spedizione</h3>

            <div style="margin-bottom: 15px;">
                <label style="display:block; font-weight:600; margin-bottom:5px; color:#555;">Nome e Cognome</label>
                <input type="text" value="<%= nome %> <%= cognome %>" readonly
                       style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px; background-color:#f5f5f5; color:#777;">
            </div>

            <div style="margin-bottom: 15px;">
                <label style="display:block; font-weight:600; margin-bottom:5px; color:#555;">Email di Contatto</label>
                <input type="email" value="<%= email %>" readonly
                       style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px; background-color:#f5f5f5; color:#777;">
            </div>

            <div style="margin-bottom: 15px;">
                <label style="display:block; font-weight:600; margin-bottom:5px; color:#555;">Recapito Telefonico</label>
                <input type="text" value="<%= telefono %>" placeholder="Inserisci il telefono se mancante"
                       style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;">
            </div>

            <div style="margin-bottom: 25px;">
                <label style="display:block; font-weight:600; margin-bottom:5px; color:#555;">Indirizzo di Consegna Completo</label>
                <input type="text" name="indirizzo" placeholder="Via, Numero Civico, CAP, Città" required
                       style="width:100%; padding:10px; border:1px solid #ccc; border-radius:4px;">
            </div>

            <hr style="border:0; border-top:1px solid #eeeeee; margin-bottom:20px;">

            <h3 style="color: #2e7d32; margin-bottom: 15px;">Metodo di Pagamento</h3>
            <div style="margin-bottom: 25px; padding: 10px; border: 1px solid #e8f5e9; background-color: #f1f8e9; border-radius: 4px;">
                <label style="cursor:pointer; color:#333; font-weight:500;">
                    <input type="radio" name="pagamento" value="Contrassegno" checked style="margin-right: 10px;">
                    Pagamento in contanti alla consegna (Contrassegno)
                </label>
            </div>

            <button type="submit" class="add-to-cart-btn" style="width:100%; padding:14px; font-size:16px; font-weight:bold;">
                Conferma e Invia Ordine
            </button>
        </form>
    </div>
</main>

<script src="js/main.js"></script>
</body>
</html>