package Model.Beans;

import java.util.Objects;

public class DettaglioOrdine {
    private int idDettaglio;

    private Pianta pianta;

    private int quantita;

    private double prezzoUnitario;

    private Ordine ordine;

    public DettaglioOrdine() {
    }

    public DettaglioOrdine(int idDettaglio, Pianta pianta, int quantita, double prezzoUnitario, Ordine ordine) {
        this.idDettaglio = idDettaglio;
        this.pianta = pianta;
        this.quantita = quantita;
        this.prezzoUnitario = prezzoUnitario;
        this.ordine = ordine;
    }

    public int getIdDettaglio() {
        return idDettaglio;
    }

    public void setIdDettaglio(int idDettaglio) {
        this.idDettaglio = idDettaglio;
    }

    public Pianta getPianta() {
        return pianta;
    }

    public void setPianta(Pianta pianta) {
        this.pianta = pianta;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public Ordine getOrdine() {
        return ordine;
    }

    public void setOrdine(Ordine ordine) {
        this.ordine = ordine;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        DettaglioOrdine that = (DettaglioOrdine) o;
        return idDettaglio == that.idDettaglio && quantita == that.quantita && Double.compare(prezzoUnitario, that.prezzoUnitario) == 0 && Objects.equals(pianta, that.pianta);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDettaglio, pianta, quantita, prezzoUnitario);
    }

    @Override
    public String toString() {
        return "DettaglioOrdine{" +
                "idDettaglio=" + idDettaglio +
                ", pianta=" + pianta +
                ", quantita=" + quantita +
                ", prezzoUnitario=" + prezzoUnitario +
                '}';
    }


}
