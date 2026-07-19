package Model.Beans;

public class ItemCarrello {
    private Pianta pianta;
    private int quantita;

    public ItemCarrello() {
    }

    public ItemCarrello(Pianta pianta, int quantita) {
        this.pianta = pianta;
        this.quantita = quantita;
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
    public double getPrezzoTotale() {
        return  (pianta.getPrezzo()*quantita);
    }
}
