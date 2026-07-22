<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VivaioMente - Il tuo Vivaio Online</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css?v=5">

    <style>

        :root {
            --primary-color: #A1E6A6;
        }


        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #e6f0a0;
        }


        .hero-banner-centered {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            text-align: center;
            padding: 60px 20px;
            background-color: var(--primary-color);
            color: #1b5e20;
            margin-bottom: 40px;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.08);
        }

        .hero-banner-centered h1 {
            font-size: 2.8rem;
            margin-top: 0;
            margin-bottom: 15px;
            text-align: center;
            color: #1b5e20;
            font-weight: bold;
        }

        .hero-banner-centered p {
            font-size: 1.2rem;
            max-width: 650px;
            margin: 0 auto 25px auto;
            line-height: 1.6;
            text-align: center;
            color: #2b4c2d;
        }

        /* BOTTONE D'AZIONE */
        .hero-banner-centered .btn-cta {
            background-color: #2e7d32;
            color: #ffffff;
            padding: 14px 32px;
            border-radius: 25px;
            text-decoration: none;
            font-weight: bold;
            font-size: 1.1rem;
            display: inline-block;
            transition: all 0.3s ease;
            box-shadow: 0 3px 6px rgba(0,0,0,0.15);
        }

        .hero-banner-centered .btn-cta:hover {
            background-color: #1b5e20;
            transform: translateY(-2px);
            box-shadow: 0 5px 12px rgba(0,0,0,0.25);
        }

        .home-main-container {
            max-width: 1000px;
            margin: 0 auto;
            padding: 0 20px 40px 20px;
            text-align: center;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<!-- HERO BANNER CENTRATO -->
<div class="hero-banner-centered">
    <h1>Benvenuto su VivaioMente! 🌿</h1>
    <p>Il tuo e-commerce di fiducia per piante da interno ed esterno. Spedizione sicura e qualità garantita dai nostri esperti botanici.</p>
    <a href="${ctx}/Catalogo" class="btn-cta">Esplora il Catalogo</a>
</div>

<!-- CONTENUTI CENTRATI -->
<main class="home-main-container">
    <h2 style="color: #1b5e20; margin-bottom: 25px; font-size: 1.8rem;">I Nostri Punti di Forza</h2>
    <div style="display: flex; gap: 20px; justify-content: center; flex-wrap: wrap; margin-bottom: 40px;">
        <div style="background: rgba(255, 255, 255, 0.9); padding: 25px; border-radius: 12px; flex: 1; min-width: 220px; box-shadow: 0 4px 10px rgba(0,0,0,0.06);">
            <span style="font-size: 2.2rem; display: block; margin-bottom: 10px;">🚚</span>
            <h3 style="color: #2e7d32; margin-top: 0;">Spedizione Sicura</h3>
            <p style="color: #444; font-size: 0.95rem; line-height: 1.4;">Imballaggi protettivi studiati per far viaggiare le piante in totale sicurezza.</p>
        </div>
        <div style="background: rgba(255, 255, 255, 0.9); padding: 25px; border-radius: 12px; flex: 1; min-width: 220px; box-shadow: 0 4px 10px rgba(0,0,0,0.06);">
            <span style="font-size: 2.2rem; display: block; margin-bottom: 10px;">🌱</span>
            <h3 style="color: #2e7d32; margin-top: 0;">Qualità Garantita</h3>
            <p style="color: #444; font-size: 0.95rem; line-height: 1.4;">Esemplari sani e rigogliosi provenienti dai migliori vivai.</p>
        </div>
        <div style="background: rgba(255, 255, 255, 0.9); padding: 25px; border-radius: 12px; flex: 1; min-width: 220px; box-shadow: 0 4px 10px rgba(0,0,0,0.06);">
            <span style="font-size: 2.2rem; display: block; margin-bottom: 10px;">💚</span>
            <h3 style="color: #2e7d32; margin-top: 0;">Supporto Botanico</h3>
            <p style="color: #444; font-size: 0.95rem; line-height: 1.4;">Consigli di cura per far crescere il tuo pollice verde.</p>
        </div>
    </div>
</main>

<script src="${ctx}/js/main.js"></script>
</body>
</html>