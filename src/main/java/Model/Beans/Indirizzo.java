package Model.Beans;

import java.util.Objects;

public class Indirizzo {
    private int idIndirizzo;

    private String via;

    private String numeroCivico;

    private String citta;

    private String cap;

    private String provincia;

    private String paese;

    private String tipo;

    private Utente utente;

    public Indirizzo() {}

    public Indirizzo(int idIndirizzo, String via, String numeroCivico, String citta, String cap, String provincia, String paese, String tipo,Utente utente) {
        this.idIndirizzo = idIndirizzo;
        this.via = via;
        this.numeroCivico = numeroCivico;
        this.citta = citta;
        this.cap = cap;
        this.provincia = provincia;
        this.paese = paese;
        this.tipo = tipo;
        this.utente = utente;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPaese() {
        return paese;
    }

    public void setPaese(String paese) {
        this.paese = paese;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCap() {
        return cap;
    }

    public void setCap(String cap) {
        this.cap = cap;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getNumeroCivico() {
        return numeroCivico;
    }

    public void setNumeroCivico(String numeroCivico) {
        this.numeroCivico = numeroCivico;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getIdIndirizzo() {
        return idIndirizzo;
    }

    public void setIdIndirizzo(int idIndirizzo) {
        this.idIndirizzo = idIndirizzo;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Indirizzo indirizzo = (Indirizzo) o;
        return idIndirizzo == indirizzo.idIndirizzo && Objects.equals(via, indirizzo.via) && Objects.equals(numeroCivico, indirizzo.numeroCivico) && Objects.equals(citta, indirizzo.citta) && Objects.equals(cap, indirizzo.cap) && Objects.equals(provincia, indirizzo.provincia) && Objects.equals(paese, indirizzo.paese) && Objects.equals(tipo, indirizzo.tipo);
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idIndirizzo, via, numeroCivico, citta, cap, provincia, paese, tipo);
    }

    @Override
    public String toString() {
        return "Indirizzo{" +
                "idIndirizzo=" + idIndirizzo +
                ", via='" + via + '\'' +
                ", numeroCivico='" + numeroCivico + '\'' +
                ", citta='" + citta + '\'' +
                ", cap='" + cap + '\'' +
                ", provincia='" + provincia + '\'' +
                ", paese='" + paese + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }


}
