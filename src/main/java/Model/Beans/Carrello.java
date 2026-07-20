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
    public void rimuoviPianta(int idPianta) {
        articoli.removeIf(item -> item.getPianta().getIdPianta() == idPianta);
    }

    public void aggiornaQuantita(int idPianta, int variazione) {
        for (ItemCarrello item : articoli) {
            if (item.getPianta().getIdPianta() == idPianta) {
                int nuovaQuantita = item.getQuantita() + variazione;
                if (nuovaQuantita <= 0) {
                    rimuoviPianta(idPianta); // Se arriva a 0, elimino la pianta
                } else {
                    item.setQuantita(nuovaQuantita);
                }
                return;
            }
        }
    }
}
