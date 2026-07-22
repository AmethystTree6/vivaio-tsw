<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>${not empty piantaDaModificare ? 'Modifica Pianta' : 'Nuova Pianta'} - Admin</title>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/style.css">
</head>
<body>

<jsp:include page="/WEB-INF/jsp/navbar.jsp" />

<main class="container" style="max-width: 800px; margin: 40px auto; padding: 20px;">

    <div style="margin-bottom: 20px;">
        <a href="${ctx}/admin/GestionePiante" style="color: #2e7d32; text-decoration: none; font-weight: bold;">← Torna alla Lista Piante</a>
    </div>

    <section style="background: white; padding: 30px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0,0,0,0.1);">
        <h1 style="margin-top: 0; color: #2e7d32;">
            <c:choose>
                <c:when test="${not empty piantaDaModificare}">
                    ✏️ Modifica Pianta #${piantaDaModificare.idPianta}
                </c:when>
                <c:otherwise>
                    ➕ Aggiungi Una Nuova Pianta
                </c:otherwise>
            </c:choose>
        </h1>

        <form action="${ctx}/admin/GestionePiante" method="post" style="display: grid; grid-template-columns: 1fr 1fr; gap: 18px; margin-top: 20px;">

            <input type="hidden" name="idPianta" value="${piantaDaModificare.idPianta}">

            <!-- CATEGORIA -->
            <div>
                <label style="display:block; font-weight:bold; margin-bottom: 5px;">Categoria *</label>
                <select name="idCategoria" required style="width:100%; padding:10px; border: 1px solid #ccc; border-radius: 4px;">
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
                <label style="display:block; font-weight:bold; margin-bottom: 5px;">Nome Comune *</label>
                <input type="text" name="nomeComune" required value="${piantaDaModificare.nomeComune}" style="width:100%; padding:10px; border: 1px solid #ccc; border-radius: 4px;">
            </div>

            <!-- NOME SCIENTIFICO -->
            <div>
                <label style="display:block; font-weight:bold; margin-bottom: 5px;">Nome Scientifico</label>
                <input type="text" name="nomeScientifico" value="${piantaDaModificare.nomeScientifico}" style="width:100%; padding:10px; border: 1px solid #ccc; border-radius: 4px;">
            </div>

            <!-- PREZZO -->
            <div>
                <label style="display:block; font-weight:bold; margin-bottom: 5px;">Prezzo (€) *</label>
                <input type="number" step="0.01" name="prezzo" required value="${piantaDaModificare.prezzo}" style="width:100%; padding:10px; border: 1px solid #ccc; border-radius: 4px;">
            </div>

            <!-- ESPOSIZIONE -->
            <div>
                <label style="display:block; font-weight:bold; margin-bottom: 5px;">Esposizione</label>
                <select name="esposizione" style="width:100%; padding:10px; border: 1px solid #ccc; border-radius: 4px;">
                    <option value="Sole" ${piantaDaModificare.esposizione == 'Sole' ? 'selected' : ''}>Sole</option>
                    <option value="Mezzombra" ${piantaDaModificare.esposizione == 'Mezzombra' ? 'selected' : ''}>Mezz'ombra</option>
                    <option value="Ombra" ${piantaDaModificare.esposizione == 'Ombra' ? 'selected' : ''}>Ombra</option>
                </select>
            </div>

            <!-- ALTEZZA -->
            <div>
                <label style="display:block; font-weight:bold; margin-bottom: 5px;">Altezza (cm)</label>
                <input type="number" step="0.1" name="altezza" value="${piantaDaModificare.altezza}" style="width:100%; padding:10px; border: 1px solid #ccc; border-radius: 4px;">
            </div>

            <!-- DIAMETRO VASO -->
            <div>
                <label style="display:block; font-weight:bold; margin-bottom: 5px;">Diametro Vaso (cm)</label>
                <input type="number" step="0.1" name="diametroVaso" value="${piantaDaModificare.diametroVaso}" style="width:100%; padding:10px; border: 1px solid #ccc; border-radius: 4px;">
            </div>

            <!-- QUANTITÀ MAGAZZINO -->
            <div>
                <label style="display:block; font-weight:bold; margin-bottom: 5px;">Quantità Magazzino *</label>
                <input type="number" name="quantitaMagazzino" required value="${not empty piantaDaModificare ? piantaDaModificare.quantitaMagazzino : 10}" style="width:100%; padding:10px; border: 1px solid #ccc; border-radius: 4px;">
            </div>

            <!-- DISPONIBILE -->
            <div style="grid-column: span 2; display: flex; align-items: center; gap: 10px; margin-top: 5px;">
                <input type="checkbox" id="disponibile" name="disponibile" value="true" ${empty piantaDaModificare || piantaDaModificare.disponibile ? 'checked' : ''} style="width: 18px; height: 18px;">
                <label for="disponibile" style="font-weight:bold; cursor: pointer;">Prodotto disponibile per la vendita</label>
            </div>

            <!-- DESCRIZIONE -->
            <div style="grid-column: span 2;">
                <label style="display:block; font-weight:bold; margin-bottom: 5px;">Descrizione</label>
                <textarea name="descrizione" rows="4" style="width:100%; padding:10px; border: 1px solid #ccc; border-radius: 4px;">${piantaDaModificare.descrizione}</textarea>
            </div>

            <div style="grid-column: span 2; margin-top: 10px;">
                <button type="submit" style="background-color: #2e7d32; color: white; padding: 12px; border: none; border-radius: 4px; font-weight: bold; cursor: pointer; width: 100%; font-size: 16px;">
                    ${not empty piantaDaModificare ? 'Salva Modifiche' : 'Salva Nuova Pianta'}
                </button>
            </div>
        </form>
    </section>
</main>

</body>
</html>
