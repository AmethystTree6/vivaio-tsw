<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!-- Taglib aggiornata a Jakarta per Tomcat 10+ -->
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<!-- Salviamo il contextPath in una variabile corta per comodità -->
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<nav class="navbar">
    <!-- LOGO -->
    <div class="nav-logo">
        <a href="${ctx}/">VivaioMente</a>
    </div>

    <!-- BARRA DI RICERCA -->
    <div class="nav-search">
        <!-- Invierà la query 'q' alla servlet dello Shop -->
        <form action="${ctx}/shop" method="GET">
            <input type="text" name="q" placeholder="Cerca piante, categorie..." required>
            <button type="submit" class="search-btn">🔍</button>
        </form>
    </div>

    <!-- LINK DESTRI -->
    <ul class="nav-links">
        <li><a href="${ctx}/Catalogo">Catalogo</a></li>

        <!-- CARRELLO -->
        <li>
            <a href="${ctx}/Carrello">Carrello
                <!-- Calcoliamo gli elementi nel carrello in sessione -->
                <c:set var="cartSize" value="${empty sessionScope.carrello ? 0 : sessionScope.carrello.numeroArticoli}" />

                <!-- ID cart-badge serve al nostro script AJAX per aggiornare il numero! -->
                <span class="cart-count" id="cart-badge" style="display: ${cartSize > 0 ? 'inline-block' : 'none'};">
                    ${cartSize}
                </span>
            </a>
        </li>

        <!-- MENU ACCOUNT (Dropdown) -->
        <li class="user-dropdown-container">
            <a href="#" style="cursor: pointer;">
                <c:choose>
                    <%-- Usiamo 'cliente' come concordato nella tua Servlet --%>
                    <c:when test="${not empty sessionScope.cliente}">
                        Ciao, ${sessionScope.cliente.nome} ▼
                    </c:when>
                    <c:otherwise>
                        Account ▼
                    </c:otherwise>
                </c:choose>
            </a>

            <div class="user-dropdown-menu">
                <c:choose>
                    <%-- CASO 1: UTENTE NON LOGGATO --%>
                    <c:when test="${empty sessionScope.cliente}">
                        <h3>Accedi</h3>
                        <!-- Rotta aggiornata a /Login -->
                        <form id="loginFormNav" action="${ctx}/Login" method="POST">
                            <div class="dropdown-form-group">
                                <input type="email" name="email" placeholder="Email" required>
                            </div>
                            <div class="dropdown-form-group">
                                <input type="password" name="password" placeholder="Password" required>
                            </div>
                            <!-- Questo span ci servirà per mostrare gli errori AJAX senza ricaricare la pagina -->
                            <div id="login-error-msg" style="color: red; font-size: 12px; margin-bottom: 10px; display: none;"></div>

                            <button type="submit" class="btn dropdown-login-btn">Login</button>
                        </form>
                        <hr class="dropdown-divider">
                        <p class="dropdown-text">
                            Nuovo utente? <a href="${ctx}/registrazione.jsp">Registrati qui</a>
                        </p>
                    </c:when>

                    <%-- CASO 2: UTENTE LOGGATO --%>
                    <c:otherwise>
                        <h3>Il mio profilo</h3>
                        <ul style="list-style: none; display: flex; flex-direction: column; gap: 10px;">

                                <%-- Se l'utente è un Admin, mostra la rotta speciale --%>
                            <c:if test="${sessionScope.cliente.ruolo == 'admin'}">
                                <li><a href="${ctx}/admin/dashboard" style="color: var(--primary-color);">⚙️ Pannello Admin</a></li>
                                <hr class="dropdown-divider" style="margin: 5px 0;">
                            </c:if>

                            <li><a href="${ctx}/profilo" style="color: var(--text-main);">👤 I miei dati</a></li>
                            <li><a href="${ctx}/ordini" style="color: var(--text-main);">📦 I miei ordini</a></li>

                            <li>
                                <hr class="dropdown-divider">
                                <!-- Logout form -->
                                <form action="${ctx}/Logout" method="POST">
                                    <button type="submit" class="btn dropdown-login-btn" style="background-color: #d32f2f;">Esci</button>
                                </form>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </li>
    </ul>
    <!-- Aggiungo anche qui lo script di js per metterlo in tutto il sito -->
    <script src="${pageContext.request.contextPath}/js/main.js"></script>
</nav>
<div id="toast-cart"></div>