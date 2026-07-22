package Model.Beans;

public class PiantaFilter {
        private String query;
        private Integer idCategoria;
        private String esposizione;
        private Double prezzoMin;
        private Double prezzoMax;
        private String orderBy;

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getEsposizione() {
        return esposizione;
    }

    public void setEsposizione(String esposizione) {
        this.esposizione = esposizione;
    }

    public Double getPrezzoMin() {
        return prezzoMin;
    }

    public void setPrezzoMin(Double prezzoMin) {
        this.prezzoMin = prezzoMin;
    }

    public Double getPrezzoMax() {
        return prezzoMax;
    }

    public void setPrezzoMax(Double prezzoMax) {
        this.prezzoMax = prezzoMax;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}

