package Model.Beans;

import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Ordine {

    private int idOrdine;

    private Utente utente;

    private Date dataOrdine;

    private String stato;

    private double totale;

    private List<DettaglioOrdine> dettagli;

    public Ordine() {}

    public Ordine(int idOrdine, Utente utente, Date dataOrdine, String stato, double totale, List<DettaglioOrdine> dettagli) {
        this.idOrdine = idOrdine;
        this.utente = utente;
        this.dataOrdine = dataOrdine;
        this.stato = stato;
        this.totale = totale;
        this.dettagli = dettagli;
    }

    public int getIdOrdine() {
        return idOrdine;
    }

    public void setIdOrdine(int idOrdine) {
        this.idOrdine = idOrdine;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Date getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(Date dataOrdine) {
        this.dataOrdine = dataOrdine;
    }

    public String getStato() {
        return stato;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public double getTotale() {
        return totale;
    }

    public void setTotale(double totale) {
        this.totale = totale;
    }

    public List<DettaglioOrdine> getDettagli() {
        return dettagli;
    }

    public void setDettagli(List<DettaglioOrdine> dettagli) {
        this.dettagli = dettagli;
    }

    public void addDettagli(DettaglioOrdine dettagli) {
        this.dettagli.add(dettagli);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Ordine ordine = (Ordine) o;
        return idOrdine == ordine.idOrdine && Double.compare(totale, ordine.totale) == 0 && Objects.equals(utente, ordine.utente) && Objects.equals(dataOrdine, ordine.dataOrdine) && Objects.equals(stato, ordine.stato) && Objects.equals(dettagli, ordine.dettagli);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idOrdine, utente, dataOrdine, stato, totale, dettagli);
    }

    @Override
    public String toString() {
        return "Ordine{" +
                "idOrdine=" + idOrdine +
                ", utente=" + utente +
                ", dataOrdine=" + dataOrdine +
                ", stato='" + stato + '\'' +
                ", totale=" + totale +
                ", dettagli=" + dettagli +
                '}';
    }
}
