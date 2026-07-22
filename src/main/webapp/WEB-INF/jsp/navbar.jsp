<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<nav class="navbar">
    <!-- LOGO -->
    <div class="nav-logo">
        <a href="${ctx}/">VivaioMente</a>
    </div>

    <!-- BARRA DI RICERCA -->
    <div class="nav-search">
        <form action="${ctx}/Catalogo" method="GET">
            <input type="text" name="q" placeholder="Cerca piante..." value="${param.q}" required>
            <button type="submit" class="search-btn">🔍</button>
        </form>
    </div>

    <!-- LINK DESTRI -->
    <ul class="nav-links">
        <li><a href="${ctx}/Catalogo">Catalogo</a></li>

        <!-- CARRELLO -->
        <li>
            <a href="${ctx}/Carrello">Carrello
                <c:set var="cartSize" value="${empty sessionScope.carrello ? 0 : sessionScope.carrello.numeroArticoli}" />
                <span class="cart-count" id="cart-badge" style="display: ${cartSize > 0 ? 'inline-block' : 'none'};">
                    ${cartSize}
                </span>
            </a>
        </li>

        <!-- MENU ACCOUNT -->
        <li class="user-dropdown-container">
            <a href="#" style="cursor: pointer;">
                <c:choose>
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
                    <c:when test="${empty sessionScope.cliente}">
                        <h3>Accedi</h3>
                        <form id="loginFormNav" action="${ctx}/Login" method="POST">
                            <div class="dropdown-form-group">
                                <input type="email" name="email" placeholder="Email" required>
                            </div>
                            <div class="dropdown-form-group">
                                <input type="password" name="password" placeholder="Password" required>
                            </div>
                            <div id="login-error-msg" style="color: red; font-size: 12px; margin-bottom: 10px; display: none;"></div>
                            <button type="submit" class="btn dropdown-login-btn">Login</button>
                        </form>
                        <hr class="dropdown-divider">
                        <p class="dropdown-text">
                            Nuovo utente? <a href="${ctx}/registrazione.jsp">Registrati qui</a>
                        </p>
                    </c:when>
                    <c:otherwise>
                        <h3>Il mio profilo</h3>
                        <ul style="list-style: none; display: flex; flex-direction: column; gap: 10px; padding: 0;">
                            <c:if test="${sessionScope.cliente.ruolo eq 'admin'}">
                                <li>
                                    <a href="${ctx}/admin/Dashboard" style="color: #d32f2f; font-weight: bold;">
                                        ⚙️ Pannello Admin
                                    </a>
                                </li>
                                <hr class="dropdown-divider" style="margin: 5px 0;">
                            </c:if>
                            <li><a href="${ctx}/ModificaProfilo" style="color: var(--text-main);">👤 I miei dati</a></li>
                            <li><a href="${ctx}/StoricoOrdini" style="color: var(--text-main);">📦 I miei ordini</a></li>
                            <li>
                                <hr class="dropdown-divider">
                                <a href="${ctx}/Logout" class="btn dropdown-login-btn" style="background-color: #d32f2f; color: white; display: block; text-align: center; text-decoration: none; padding: 8px 0; border-radius: 4px;">
                                    Esci
                                </a>
                            </li>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>
        </li>
    </ul>

    <script src="${ctx}/js/main.js"></script>
</nav>

<div id="toast-cart"></div>