package Model.Beans;

import java.util.ArrayList;
import java.util.List;

public class Carrello {
    private List<ItemCarrello> articoli;

    public Carrello() {
        this.articoli = new ArrayList<>();
    }

    public List<ItemCarrello> getArticoli() {
        return articoli;
    }

    public void aggiungiPianta(Pianta p) {
        for (ItemCarrello item : articoli) {
            if (item.getPianta().getIdPianta() == p.getIdPianta()) {
                item.setQuantita(item.getQuantita() + 1);
                return;
            }
        }
        articoli.add(new ItemCarrello(p, 1));
    }

    public int getNumeroArticoli() {
        int totale = 0;
        for (ItemCarrello item : articoli) {
            totale += item.getQuantita();
        }
        return totale;
    }

    public double getTotale() {
        double totale = 0.0;
        for (ItemCarrello item : articoli) {
            totale += item.getPrezzoTotale();
        }
        return totale;
    }
}
