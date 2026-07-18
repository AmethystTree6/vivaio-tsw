package it.unisa.progetto.model.dao;

import it.unisa.progetto.model.bean.Prodotto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class ProdottoDAO {

    // Estrae tutte le piante dal database
    public synchronized Collection<Prodotto> doRetrieveAll(String order) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Collection<Prodotto> products = new LinkedList<Prodotto>();

        String selectSQL = "SELECT * FROM prodotti";

        // Se l'utente richiede un ordinamento specifico (es. per prezzo)
        if (order != null && !order.equals("")) {
            selectSQL += " ORDER BY " + order;
        }

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Prodotto bean = new Prodotto();
                bean.setId(rs.getInt("id"));
                bean.setNome(rs.getString("nome"));
                bean.setDescrizione(rs.getString("descrizione"));
                bean.setPrezzo(rs.getDouble("prezzo"));
                bean.setAmbiente(rs.getString("ambiente"));
                bean.setEsposizione(rs.getString("esposizione"));
                bean.setDifficolta(rs.getString("difficolta"));
                bean.setStock(rs.getInt("stock"));
                bean.setImmagine(rs.getString("immagine"));

                products.add(bean);
            }

        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
            } finally {
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return products;
    }
}