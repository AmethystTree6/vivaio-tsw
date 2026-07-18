package Model.Beans;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Utente {

    private int idUtente;

    private String nome;

    private String cognome;

    private String email;

    private String passwordHash;

    private String telefono;

    private String ruolo;

    private Date dataRegistrazione;

    private List<Indirizzo> indirizzi;

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public Date getDataRegistrazione() {
        return dataRegistrazione;
    }

    public void setDataRegistrazione(Date dataRegistrazione) {
        this.dataRegistrazione = dataRegistrazione;
    }

    public List<Indirizzo> getIndirizzi() {
        return indirizzi;
    }

    public void setIndirizzi(List<Indirizzo> indirizzi) {
        this.indirizzi = indirizzi;
    }

    public void addIndirizzo(Indirizzo indirizzo) {
        this.indirizzi.add(indirizzo);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Utente utente = (Utente) o;
        return idUtente == utente.idUtente && Objects.equals(nome, utente.nome) && Objects.equals(cognome, utente.cognome) && Objects.equals(email, utente.email) && Objects.equals(passwordHash, utente.passwordHash) && Objects.equals(telefono, utente.telefono) && Objects.equals(ruolo, utente.ruolo) && Objects.equals(dataRegistrazione, utente.dataRegistrazione) && Objects.equals(indirizzi, utente.indirizzi);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUtente, nome, cognome, email, passwordHash, telefono, ruolo, dataRegistrazione, indirizzi);
    }

    @Override
    public String toString() {
        return "Utente{" +
                "idUtente=" + idUtente +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                ", telefono='" + telefono + '\'' +
                ", ruolo='" + ruolo + '\'' +
                ", dataRegistrazione=" + dataRegistrazione +
                ", indirizzi=" + indirizzi +
                '}';
    }
}
