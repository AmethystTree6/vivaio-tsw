<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VivaioMente - Catalogo Piante</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">

    <style>
        .catalogo-layout {
            display: grid;
            grid-template-columns: 260px 1fr;
            gap: 30px;
            margin-top: 30px;
        }

        .filter-sidebar {
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 2px 6px rgba(0,0,0,0.06);
            height: fit-content;
        }

        .filter-group {
            margin-bottom: 18px;
        }

        .filter-group label {
            display: block;
            font-weight: bold;
            font-size: 14px;
            margin-bottom: 6px;
        }

        .filter-group input, .filter-group select {
            width: 100%;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        .products-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(240px, 1fr));
            gap: 25px;
        }

        .product-card {
            background: var(--white);
            border: 1px solid var(--border-color);
            border-radius: var(--border-radius);
            padding: 15px;
            text-align: center;
            box-shadow: 0 2px 5px rgba(0,0,0,0.05);
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }

        .product-image-container {
            height: 180px;
            overflow: hidden;
            border-radius: 6px;
            margin-bottom: 15px;
            background-color: #f9f9f9;
        }

        .product-image-container img {
            width: 100%;
            height: 100%;
            object-fit: cover;
        }

        @media (max-width: 768px) {
            .catalogo-layout {
                grid-template-columns: 1fr;
            }
        }
    </style>
</head>
<body>

<%@ include file="/WEB-INF/jsp/navbar.jsp" %>

<main class="container" style="max-width: 1200px; margin: 0 auto; padding: 20px;">

    <h1 style="text-align: center; color: var(--primary-color); margin-top: 10px;">Le Nostre Piante</h1>

    <div class="catalogo-layout">

        <!-- SIDEBAR FILTRI -->
        <aside class="filter-sidebar">
            <h3 style="margin-top: 0; border-bottom: 2px solid #2e7d32; padding-bottom: 8px; color: #2e7d32;">Filtra Catalogo</h3>

            <form action="${ctx}/Catalogo" method="GET">

                <!-- TESTO RICERCA -->
                <div class="filter-group">
                    <label>Cerca per Nome</label>
                    <input type="text" name="q" value="${param.q}" placeholder="es. Ficus, Orchidea...">
                </div>

                <!-- CATEGORIA -->
                <div class="filter-group">
                    <label>Categoria</label>
                    <select name="categoria">
                        <option value="">Tutte le categorie</option>
                        <c:forEach var="cat" items="${categorie}">
                            <option value="${cat.idCategoria}" ${param.categoria == cat.idCategoria ? 'selected' : ''}>
                                    ${cat.nomeCategoria}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <!-- ESPOSIZIONE -->
                <div class="filter-group">
                    <label>Esposizione Luce</label>
                    <select name="esposizione">
                        <option value="">Tutte</option>
                        <option value="Sole" ${param.esposizione == 'Sole' ? 'selected' : ''}>Sole</option>
                        <option value="Mezzombra" ${param.esposizione == 'Mezzombra' ? 'selected' : ''}>Mezz'ombra</option>
                        <option value="Ombra" ${param.esposizione == 'Ombra' ? 'selected' : ''}>Ombra</option>
                    </select>
                </div>

                <!-- FASCIA DI PREZZO -->
                <div class="filter-group">
                    <label>Prezzo (€)</label>
                    <div style="display: flex; gap: 8px;">
                        <input type="number" step="0.01" name="prezzoMin" placeholder="Min" value="${param.prezzoMin}">
                        <input type="number" step="0.01" name="prezzoMax" placeholder="Max" value="${param.prezzoMax}">
                    </div>
                </div>

                <!-- ORDINAMENTO -->
                <div class="filter-group">
                    <label>Ordina per</label>
                    <select name="orderBy">
                        <option value="">Novità</option>
                        <option value="prezzo_asc" ${param.orderBy == 'prezzo_asc' ? 'selected' : ''}>Prezzo: cresce</option>
                        <option value="prezzo_desc" ${param.orderBy == 'prezzo_desc' ? 'selected' : ''}>Prezzo: decresce</option>
                        <option value="nome" ${param.orderBy == 'nome' ? 'selected' : ''}>Nome A-Z</option>
                    </select>
                </div>

                <button type="submit" style="width: 100%; background-color: #2e7d32; color: white; border: none; padding: 10px; border-radius: 4px; font-weight: bold; cursor: pointer; margin-top: 10px;">
                    Applica Filtri
                </button>

                <a href="${ctx}/Catalogo" style="display: block; text-align: center; font-size: 13px; color: #c62828; margin-top: 10px; text-decoration: none;">
                    Azzera Filtri
                </a>
            </form>
        </aside>

        <!-- AREA PRODOTTI -->
        <section>
            <c:if test="${not empty param.q}">
                <p style="margin-top: 0; font-size: 15px;">Risultati della ricerca per: <strong>"${param.q}"</strong></p>
            </c:if>

            <div class="products-grid">
                <c:choose>
                    <c:when test="${not empty requestScope.prodotti}">
                        <c:forEach var="pianta" items="${requestScope.prodotti}">
                            <div class="product-card">

                                <div class="product-image-container">
                                    <img src="${ctx}/images/piante/${pianta.idPianta}.jpg"
                                         alt="${pianta.nomeComune}"
                                         onerror="this.onerror=null; this.src='${ctx}/images/placeholder.png';">
                                </div>

                                <div class="product-info">
                                    <h3 style="margin: 0 0 4px 0;">${pianta.nomeComune}</h3>
                                    <p style="font-size: 13px; color: #666; font-style: italic; margin-bottom: 8px;">
                                            ${pianta.nomeScientifico}
                                    </p>

                                    <p style="font-size: 13px; color: #555; height: 38px; overflow: hidden; display: -webkit-box; -webkit-line-clamp: 2; -webkit-box-orient: vertical; margin-bottom: 12px;">
                                            ${pianta.descrizione}
                                    </p>

                                    <p style="font-size: 12px; color: #2e7d32; font-weight: bold; margin-bottom: 8px;">
                                        ✓ Disponibile (${pianta.quantitaMagazzino} pz)
                                    </p>

                                    <p class="product-price" style="font-weight: bold; font-size: 18px; color: var(--primary-color); margin-bottom: 15px;">
                                        <fmt:formatNumber value="${pianta.prezzo}" type="currency" currencySymbol="€" maxFractionDigits="2"/>
                                    </p>

                                    <button class="btn" style="width: 100%;" onclick="aggiungiAlCarrello(${pianta.idPianta})">
                                        Aggiungi al Carrello
                                    </button>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>

                    <c:otherwise>
                        <p style="grid-column: 1 / -1; text-align: center; color: #666; padding: 40px; background: white; border-radius: 8px;">
                            Nessuna pianta trovata con i filtri selezionati.
                        </p>
                    </c:otherwise>
                </c:choose>
            </div>
        </section>
    </div>
</main>
<div id="toast-cart"></div>
<script src="${ctx}/js/main.js"></script>
</body>
</html>