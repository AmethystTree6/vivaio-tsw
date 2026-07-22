<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<%@ taglib uri="jakarta.tags.fmt" prefix="fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Gestione Catalogo Piante - Admin</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<main class="container" style="max-width: 1000px; margin: 40px auto; padding: 20px;">

    <div style="margin-bottom: 20px;">
        <a href="${ctx}/admin/Dashboard" style="color: #2e7d32; text-decoration: none; font-weight: bold;">← Torna alla Dashboard Admin</a>
    </div>

    <h1>Gestione Catalogo Piante</h1>

    <!-- Feedback -->
    <c:if test="${not empty messaggioSuccesso}">
        <div style="background-color: #e8f5e9; color: #2e7d32; padding: 12px; border-radius: 4px; margin-bottom: 20px;">
                ${messaggioSuccesso}
        </div>
    </c:if>
    <c:if test="${not empty errore}">
        <div style="background-color: #ffebee; color: #c62828; padding: 12px; border-radius: 4px; margin-bottom: 20px;">
                ${errore}
        </div>
    </c:if>

    <!-- FORM AGGIUNTA / MODIFICA PIANTA -->
    <section style="background: white; padding: 25px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); margin-bottom: 40px;">
        <h2 style="margin-top: 0;">
            <c:choose>
                <c:when test="${not empty piantaDaModificare}">
                    ✏️ Modifica Pianta #${piantaDaModificare.idPianta}
                    <a href="${ctx}/admin/GestionePiante" style="font-size: 14px; margin-left: 15px; color: #d32f2f; text-decoration: none;">(Annulla modifica)</a>
                </c:when>
                <c:otherwise>
                    ➕ Aggiungi una nuova Pianta
                </c:otherwise>
            </c:choose>
        </h2>

        <form action="${ctx}/admin/GestionePiante" method="post" style="display: grid; grid-template-columns: 1fr 1fr; gap: 15px;">

            <!-- Campo Nascosto per identificare se siamo in MODIFICA -->
            <input type="hidden" name="idPianta" value="${piantaDaModificare.idPianta}">

            <!-- CATEGORIA -->
            <div>
                <label style="display:block; font-weight:bold;">Categoria *</label>
                <select name="idCategoria" required style="width:100%; padding:8px;">
                    <option value="">-- Seleziona Categoria --</option>
                    <c:forEach var="cat" items="${categorie}">
                        <option value="${cat.idCategoria}" ${piantaDaModificare.categoria.idCategoria == cat.idCategoria ? 'selected' : ''}>
                                ${cat.nomeCategoria}
                        </option>
                    </c:forEach>
                </select>
            </div>

            <!-- NOME COMUNE -->
            <div>
                <label style="display:block; font-weight:bold;">Nome Comune *</label>
                <input type="text" name="nomeComune" required value="${piantaDaModificare.nomeComune}" style="width:100%; padding:8px;">
            </div>

            <!-- NOME SCIENTIFICO -->
            <div>
                <label style="display:block; font-weight:bold;">Nome Scientifico</label>
                <input type="text" name="nomeScientifico" value="${piantaDaModificare.nomeScientifico}" style="width:100%; padding:8px;">
            </div>

            <!-- PREZZO -->
            <div>
                <label style="display:block; font-weight:bold;">Prezzo (€) *</label>
                <input type="number" step="0.01" name="prezzo" required value="${piantaDaModificare.prezzo}" style="width:100%; padding:8px;">
            </div>

            <!-- ESPOSIZIONE -->
            <div>
                <label style="display:block; font-weight:bold;">Esposizione</label>
                <select name="esposizione" style="width:100%; padding:8px;">
                    <option value="Sole" ${piantaDaModificare.esposizione == 'Sole' ? 'selected' : ''}>Sole</option>
                    <option value="Mezzombra" ${piantaDaModificare.esposizione == 'Mezzombra' ? 'selected' : ''}>Mezz'ombra</option>
                    <option value="Ombra" ${piantaDaModificare.esposizione == 'Ombra' ? 'selected' : ''}>Ombra</option>
                </select>
            </div>

            <!-- ALTEZZA -->
            <div>
                <label style="display:block; font-weight:bold;">Altezza (cm)</label>
                <input type="number" step="0.1" name="altezza" value="${piantaDaModificare.altezza}" style="width:100%; padding:8px;">
            </div>

            <!-- DIAMETRO VASO -->
            <div>
                <label style="display:block; font-weight:bold;">Diametro Vaso (cm)</label>
                <input type="number" step="0.1" name="diametroVaso" value="${piantaDaModificare.diametroVaso}" style="width:100%; padding:8px;">
            </div>

            <!-- QUANTITÀ MAGAZZINO -->
            <div>
                <label style="display:block; font-weight:bold;">Quantità Magazzino *</label>
                <input type="number" name="quantitaMagazzino" required value="${not empty piantaDaModificare ? piantaDaModificare.quantitaMagazzino : 10}" style="width:100%; padding:8px;">
            </div>

            <!-- DISPONIBILE -->
            <div style="grid-column: span 2; display: flex; align-items: center; gap: 10px;">
                <input type="checkbox" id="disponibile" name="disponibile" value="true" ${empty piantaDaModificare || piantaDaModificare.disponibile ? 'checked' : ''} style="width: 18px; height: 18px;">
                <label for="disponibile" style="font-weight:bold; cursor: pointer;">Prodotto subito disponibile per la vendita</label>
            </div>

            <!-- DESCRIZIONE -->
            <div style="grid-column: span 2;">
                <label style="display:block; font-weight:bold;">Descrizione</label>
                <textarea name="descrizione" rows="3" style="width:100%; padding:8px;">${piantaDaModificare.descrizione}</textarea>
            </div>

            <div style="grid-column: span 2;">
                <button type="submit" style="background-color: #2e7d32; color: white; padding: 12px 20px; border: none; border-radius: 4px; font-weight: bold; cursor: pointer;">
                    ${not empty piantaDaModificare ? 'Salva Modifiche' : 'Salva Nuova Pianta'}
                </button>
            </div>
        </form>
    </section>

    <!-- TABELLA PIANTE ESISTENTI -->
    <h2>Piante attualmente in Catalogo</h2>
    <table style="width: 100%; border-collapse: collapse; background: white; margin-top: 15px;">
        <thead>
        <tr style="background-color: #f5f5f5; text-align: left;">
            <th style="padding: 10px; border-bottom: 2px solid #ddd;">ID</th>
            <th style="padding: 10px; border-bottom: 2px solid #ddd;">Nome</th>
            <th style="padding: 10px; border-bottom: 2px solid #ddd;">Categoria</th>
            <th style="padding: 10px; border-bottom: 2px solid #ddd;">Prezzo</th>
            <th style="padding: 10px; border-bottom: 2px solid #ddd;">Magazzino</th>
            <th style="padding: 10px; border-bottom: 2px solid #ddd;">Stato</th>
            <th style="padding: 10px; border-bottom: 2px solid #ddd; text-align: center;">Azione</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${piante}">
            <tr style="border-bottom: 1px solid #eee;">
                <td style="padding: 10px;">${p.idPianta}</td>
                <td style="padding: 10px;"><strong>${p.nomeComune}</strong> <em>(${p.nomeScientifico})</em></td>
                <td style="padding: 10px;">
                    <c:choose>
                        <c:when test="${not empty p.categoria.nome}">
                            ${p.categoria.nome}
                        </c:when>
                        <c:otherwise>
                            <em>Non specificata</em>
                        </c:otherwise>
                    </c:choose>
                </td>
                <td style="padding: 10px;"><fmt:formatNumber value="${p.prezzo}" type="currency" currencySymbol="€"/></td>
                <td style="padding: 10px;">${p.quantitaMagazzino} pz</td>
                <td style="padding: 10px;">
                        <span style="color: ${p.disponibile ? '#2e7d32' : '#c62828'}; font-weight: bold;">
                                ${p.disponibile ? 'Disponibile' : 'Non Disponibile'}
                        </span>
                </td>
                <td style="padding: 10px; text-align: center;">
                    <a href="${ctx}/admin/GestionePiante?idPianta=${p.idPianta}" style="background-color: #1976d2; color: white; padding: 5px 10px; text-decoration: none; border-radius: 3px; font-size: 13px; font-weight: bold;">
                        ✏️ Modifica
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</main>

</body>
</html>