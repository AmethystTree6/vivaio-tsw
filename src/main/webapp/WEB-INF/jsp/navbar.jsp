<nav class="navbar">
    <!-- LOGO DEL VIVAIO -->
    <div class="nav-logo">
        <a href="index.jsp"> VivaioMente</a>
    </div>

    <!-- BARRA DI RICERCA -->
    <div class="nav-search">
        <form action="Catalogo" method="get">
            <input type="text" name="query" placeholder="Cerca una pianta (es. Ficus)..." required>
            <button type="submit" class="search-btn"></button>
        </form>
    </div>

    <!-- LINK E ICONE SULLA DESTRA -->
    <ul class="nav-links">
        <li><a href="catalogo.jsp">Catalogo</a></li>

        <!-- CONTENITORE ICONA + TENDINA LOGIN -->
        <li class="user-dropdown-container">
            <a href="login.jsp" class="nav-icon-link">
                <span class="icon"></span> Account
            </a>

            <!-- TENDINA CHE COMPARE AL PASSAGGIO DEL MOUSE -->
            <div class="user-dropdown-menu">
                <form id="dropdown-login-form" onsubmit="loginAsincrono(event)">
                    <h3>Accedi</h3>
                    <div class="dropdown-form-group">
                        <input type="text" id="drop-username" placeholder="Username" required>
                    </div>
                    <div class="dropdown-form-group">
                        <input type="password" id="drop-password" placeholder="Password" required>
                    </div>
                    <button type="submit" class="dropdown-login-btn">Entra</button>
                </form>
                <hr class="dropdown-divider">
                <p class="dropdown-text">Nuovo cliente? <a href="registrazione.jsp">Registrati qui</a></p>
            </div>
        </li>

        <!-- CARRELLO -->
        <li>
            <a href="carrello.jsp" class="nav-icon-link">
                <span class="icon"></span> Carrello <span class="cart-count">0</span>
            </a>
        </li>
    </ul>
</nav>