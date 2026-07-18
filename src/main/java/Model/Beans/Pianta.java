package Model.Beans;

import java.util.Objects;

public class Pianta {
    private int idPianta;

    private Categoria categoria;

    private String nomeComune;

    private String nomeScientifico;

    private String descrizione;

    private double prezzo;

    private String esposizione;

    private double altezza;

    private double diametroVaso;

    private int quantitaMagazzino;

    private boolean disponibile;

    public Pianta() {
    }

    public Pianta(int idPianta, Categoria categoria, String nomeComune, String nomeScientifico,
                  String descrizione, double prezzo, String esposizione, double altezza, double diametroVaso,
                  int quantitaMagazzino, boolean disponibile) {

        this.idPianta = idPianta;
        this.categoria = categoria;
        this.nomeComune = nomeComune;
        this.nomeScientifico = nomeScientifico;
        this.descrizione = descrizione;
        this.prezzo = prezzo;
        this.esposizione = esposizione;
        this.altezza = altezza;
        this.diametroVaso = diametroVaso;
        this.quantitaMagazzino = quantitaMagazzino;
        this.disponibile = disponibile;
    }

    public int getIdPianta() {
        return idPianta;
    }

    public void setIdPianta(int idPianta) {
        this.idPianta = idPianta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getNomeComune() {
        return nomeComune;
    }

    public void setNomeComune(String nomeComune) {
        this.nomeComune = nomeComune;
    }

    public String getNomeScientifico() {
        return nomeScientifico;
    }

    public void setNomeScientifico(String nomeScientifico) {
        this.nomeScientifico = nomeScientifico;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getEsposizione() {
        return esposizione;
    }

    public void setEsposizione(String esposizione) {
        this.esposizione = esposizione;
    }

    public double getAltezza() {
        return altezza;
    }

    public void setAltezza(double altezza) {
        this.altezza = altezza;
    }

    public double getDiametroVaso() {
        return diametroVaso;
    }

    public void setDiametroVaso(double diametroVaso) {
        this.diametroVaso = diametroVaso;
    }

    public int getQuantitaMagazzino() {
        return quantitaMagazzino;
    }

    public void setQuantitaMagazzino(int quantitaMagazzino) {
        this.quantitaMagazzino = quantitaMagazzino;
    }

    public boolean isDisponibile() {
        return disponibile;
    }

    public void setDisponibile(boolean disponibile) {
        this.disponibile = disponibile;
    }

    @Override
    public String toString() {
        return "Pianta{" +
                "idPianta=" + idPianta +
                ", categoria=" + categoria +
                ", nomeComune='" + nomeComune + '\'' +
                ", nomeScientifico='" + nomeScientifico + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", esposizione='" + esposizione + '\'' +
                ", altezza=" + altezza +
                ", diametroVaso=" + diametroVaso +
                ", quantitaMagazzino=" + quantitaMagazzino +
                ", disponibile=" + disponibile +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pianta pianta = (Pianta) o;
        return idPianta == pianta.idPianta && Double.compare(prezzo, pianta.prezzo) == 0 && Double.compare(altezza, pianta.altezza) == 0 && Double.compare(diametroVaso, pianta.diametroVaso) == 0 && quantitaMagazzino == pianta.quantitaMagazzino && disponibile == pianta.disponibile && Objects.equals(categoria, pianta.categoria) && Objects.equals(nomeComune, pianta.nomeComune) && Objects.equals(nomeScientifico, pianta.nomeScientifico) && Objects.equals(descrizione, pianta.descrizione) && Objects.equals(esposizione, pianta.esposizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPianta, categoria, nomeComune, nomeScientifico, descrizione, prezzo, esposizione, altezza, diametroVaso, quantitaMagazzino, disponibile);
    }
}
