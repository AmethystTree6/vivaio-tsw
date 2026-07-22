console.log("1. File main.js caricato dal server!");

document.addEventListener("DOMContentLoaded", function() {
    console.log("2. DOM caricato, cerco il form...");

    const loginForm = document.getElementById("loginFormNav");
    const errorMsgDiv = document.getElementById("login-error-msg");

    if (loginForm) {
        console.log("3. Form trovato! Listener agganciato.");

        loginForm.addEventListener("submit", function(event) {
            event.preventDefault();
            console.log("4. Submit intercettato! Fermo il ricaricamento della pagina.");

            const formData = new URLSearchParams(new FormData(loginForm));
            console.log("5. Dati pronti per l'invio alla rotta: " + loginForm.action);

            fetch(loginForm.action, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded',
                    'X-Requested-With': 'XMLHttpRequest'
                },
                body: formData.toString()
            })
                .then(response => {
                    console.log("6. Risposta ricevuta dal server. Stato HTTP:", response.status);
                    return response.json();
                })
                .then(data => {
                    console.log("7. Dati JSON decodificati:", data);
                    if (data.status === 'success') {
                        window.location.reload();
                    } else if (data.status === 'error') {
                        errorMsgDiv.textContent = data.message;
                        errorMsgDiv.style.display = 'block';
                    }
                })
                .catch(error => {
                    console.error("ERRORE AJAX:", error);
                });
        });
    } else {
        console.warn("ATTENZIONE: Form loginFormNav NON trovato nella pagina!");
    }


    /* Registrazione e validazione input con AJAX */
    const regForm = document.getElementById("registrazioneForm");

    if (regForm) {
        const emailInput = document.getElementById("reg-email");
        const emailFeedback = document.getElementById("email-feedback");
        const pwdInput = document.getElementById("reg-pwd");
        const pwdConfirm = document.getElementById("reg-pwd-confirm");
        const pwdFeedback = document.getElementById("pwd-feedback");
        const btnRegistrati = document.getElementById("btn-registrati");

        let emailValida = false;

        // 1. Controllo dell'Email mentre l'utente digita
        emailInput.addEventListener("input", function() {
            const email = emailInput.value.trim();

            if (email.length > 5 && email.includes("@")) {
                fetch('VerificaEmail?email=' + encodeURIComponent(email))
                    .then(response => response.json())
                    .then(data => {
                        if (data.esiste) {
                            emailFeedback.style.display = "block";
                            emailFeedback.textContent = "Questa email è già registrata!";
                            emailValida = false;
                            btnRegistrati.disabled = true; // Blocca il tasto
                        } else {
                            emailFeedback.style.display = "none";
                            emailValida = true;
                            btnRegistrati.disabled = false; // Sblocca il tasto
                        }
                    })
                    .catch(err => console.error("Errore controllo email:", err));
            }
        });

        // 2. Controllo che le password combacino al Submit
        regForm.addEventListener("submit", function(event) {
            if (pwdInput.value !== pwdConfirm.value) {
                event.preventDefault(); // Ferma l'invio
                pwdFeedback.style.display = "block";
            } else {
                pwdFeedback.style.display = "none";
            }

            if (!emailValida) {
                event.preventDefault(); // Ferma l'invio se l'email è già in uso
                emailInput.focus();
            }
        });
    }
});

function aggiungiAlCarrello(idPianta) {
    const params = new URLSearchParams();
    params.append('action', 'aggiungi');
    params.append('id', idPianta);

    fetch('GestioneCarrello', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: params.toString()
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                const toast = document.getElementById("toast-cart");
                if (toast) {
                    // Scriviamo il messaggio
                    toast.innerText = "🌱 Aggiunto! Totale articoli: " + data.numeroArticoli;

                    // Applicazione stili inline per garantire visibilità e posizionamento fluttuante
                    toast.style.position = "fixed";
                    toast.style.bottom = "30px";
                    toast.style.right = "30px";
                    toast.style.backgroundColor = "#2e7d32"; // Verde VivaioMente
                    toast.style.color = "#ffffff";
                    toast.style.padding = "14px 22px";
                    toast.style.borderRadius = "8px";
                    toast.style.boxShadow = "0 4px 12px rgba(0,0,0,0.2)";
                    toast.style.fontSize = "15px";
                    toast.style.fontWeight = "bold";
                    toast.style.zIndex = "9999"; // Stia SOPRA a qualsiasi elemento
                    toast.style.transition = "opacity 0.3s ease, transform 0.3s ease";

                    // Lo rendiamo visibile
                    toast.style.display = "block";
                    toast.style.opacity = "1";
                    toast.style.transform = "translateY(0)";

                    // Aggiorniamo anche il badge della navbar se presente
                    const badge = document.getElementById("cart-badge");
                    if (badge) {
                        badge.innerText = data.numeroArticoli;
                        badge.style.display = "inline-block";
                    }

                    // Scompare animato dopo 3 secondi
                    setTimeout(function(){
                        toast.style.opacity = "0";
                        toast.style.transform = "translateY(10px)";
                        setTimeout(function() {
                            toast.style.display = "none";
                        }, 300); // tempo per completare la dissolvenza
                    }, 3000);
                }
            } else {
                console.error(data.errore);
            }
        })
        .catch(error => console.error('Errore:', error));
}

function modificaQuantita(idPianta, variazione) {
    const params = new URLSearchParams();
    params.append('action', 'modifica');
    params.append('id', idPianta);
    params.append('variazione', variazione); // +1 o -1

    fetch('GestioneCarrello', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: params.toString()
    })
        .then(response => response.json())
        .then(data => {
            if(data.success) {
                location.reload(); // Ricarica la pagina istantaneamente per aggiornare i prezzi
            }
        });
}

function rimuoviProdotto(idPianta) {
    if (!confirm("Sei sicuro di voler rimuovere questa pianta dal carrello?")) return;

    const params = new URLSearchParams();
    params.append('action', 'rimuovi');
    params.append('id', idPianta);

    fetch('GestioneCarrello', {
        method: 'POST',
        headers: {'Content-Type': 'application/x-www-form-urlencoded'},
        body: params.toString()
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                location.reload(); // Ricarica la pagina istantaneamente
            }
        });
}