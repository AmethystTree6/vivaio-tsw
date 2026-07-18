package Model.Beans;

import java.util.Objects;

public class Categoria {
    private int idCategoria;

    private String nomeCategoria;

    private String descrizione;

    public Categoria(int idCategoria, String nomeCategoria, String descrizione) {
        this.idCategoria = idCategoria;
        this.nomeCategoria = nomeCategoria;
        this.descrizione = descrizione;
    }

    public Categoria() {
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "idCategoria=" + idCategoria +
                ", nomeCategoria='" + nomeCategoria + '\'' +
                ", descrizione='" + descrizione + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return idCategoria == categoria.idCategoria && Objects.equals(nomeCategoria, categoria.nomeCategoria) && Objects.equals(descrizione, categoria.descrizione);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria, nomeCategoria, descrizione);
    }
}
