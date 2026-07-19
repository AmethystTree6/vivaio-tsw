<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>VivaioMente - Il tuo Carrello</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<main class="container">
    <h1 class="page-title">Il tuo Carrello</h1>

    <div style="background-color: #ffffff; padding: 25px; border: 1px solid #e0e0e0; border-radius: 6px; box-shadow: 0 2px 4px rgba(0,0,0,0.04);">

        <table style="width: 100%; border-collapse: collapse; text-align: left; margin-bottom: 30px;">
            <thead>
            <tr style="border-bottom: 2px solid #eeeeee; color: #1b5e20; font-weight: bold;">
                <th style="padding: 12px 8px;">Prodotto</th>
                <th style="padding: 12px 8px;">Prezzo Unitario</th>
                <th style="padding: 12px 8px; text-align: center;">Quantità</th>
                <th style="padding: 12px 8px; text-align: right;">Totale</th>
            </tr>
            </thead>
            <tbody>
            <tr style="border-bottom: 1px solid #eeeeee;">
                <td style="padding: 15px 8px; font-weight: 600; color: #333333;">Esempio Pianta</td>
                <td style="padding: 15px 8px; color: #555555;">15.00 €</td>
                <td style="padding: 15px 8px; text-align: center;">
                    <input type="number" value="1" min="1" style="width: 50px; padding: 5px; text-align: center; border: 1px solid #cccccc; border-radius: 4px;">
                </td>
                <td style="padding: 15px 8px; text-align: right; font-weight: 600; color: #2e7d32;">15.00 €</td>
            </tr>
            </tbody>
        </table>

        <div style="display: flex; justify-content: space-between; align-items: center; border-top: 1px solid #eeeeee; padding-top: 20px;">
            <!-- Nel file carrello.jsp, dove c'è il link per tornare indietro -->
            <div>
                <a href="Catalogo" class="back-to-shopping" style="color: #1b5e20; text-decoration: none; font-weight: 600; font-size: 14px;">
                    &larr; Continua lo shopping
                </a>
            </div>
            <div style="text-align: right;">
                <p style="font-size: 16px; color: #555555; margin-bottom: 10px;">
                    Totale parziale: <span style="font-size: 20px; font-weight: 700; color: #2e7d32; margin-left: 10px;">15.00 €</span>
                </p>

                <!-- Azione 2: Invia la richiesta di controllo alla CheckoutServlet -->
                <form action="Checkout" method="GET" style="margin: 0;">
                    <button type="submit" class="add-to-cart-btn" style="padding: 12px 25px; font-size: 15px; cursor: pointer;">
                        Procedi al checkout
                    </button>
                </form>
            </div>
        </div>

    </div>
</main>

<script src="js/login.js"></script>
</body>
</html>