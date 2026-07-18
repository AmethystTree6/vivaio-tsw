package it.unisa.progetto.model.bean;

import java.io.Serializable;

public class Prodotto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String nome;
    private String descrizione;
    private double prezzo;
    private String ambiente;
    private String esposizione;
    private String difficolta;
    private int stock;
    private String immagine;

    // Costruttore vuoto obbligatorio per le specifiche JavaBean
    public Prodotto() {}

    // Getter e Setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescrizione() { return descrizione; }
    public void setDescrizione(String descrizione) { this.descrizione = descrizione; }

    public double getPrezzo() { return prezzo; }
    public void setPrezzo(double prezzo) { this.prezzo = prezzo; }

    public String getAmbiente() { return ambiente; }
    public void setAmbiente(String ambiente) { this.ambiente = ambiente; }

    public String getEsposizione() { return esposizione; }
    public void setEsposizione(String esposizione) { this.esposizione = esposizione; }

    public String getDifficolta() { return difficolta; }
    public void setDifficolta(String difficolta) { this.difficolta = difficolta; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public String getImmagine() { return immagine; }
    public void setImmagine(String immagine) { this.immagine = immagine; }
}