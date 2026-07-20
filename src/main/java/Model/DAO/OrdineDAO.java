package Model.DAO;

import Model.Beans.Carrello;
import Model.Beans.ItemCarrello;
import Model.Beans.Ordine;
import Model.Beans.Utente;
import Model.DBConnection;
import org.eclipse.tags.shaded.org.apache.xalan.lib.sql.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdineDAO {

    public void doSave(Ordine ordine) {
        // La data_ordine viene generata in automatico dal DB (DEFAULT CURRENT_TIMESTAMP)
        String query = "INSERT INTO Ordine (id_utente, stato, totale) VALUES (?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, ordine.getUtente().getIdUtente()); // Prende l'ID dall'oggetto Utente annidato
            ps.setString(2, ordine.getStato() != null ? ordine.getStato() : "In preparazione");
            ps.setDouble(3, ordine.getTotale());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doUpdate(Ordine ordine) {
        String query = "UPDATE Ordine SET id_utente=?, stato=?, totale=? WHERE id_ordine=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, ordine.getUtente().getIdUtente());
            ps.setString(2, ordine.getStato());
            ps.setDouble(3, ordine.getTotale());
            ps.setInt(4, ordine.getIdOrdine());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doDelete(Ordine ordine) {
        String query = "DELETE FROM Ordine WHERE id_ordine = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, ordine.getIdOrdine());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Corretto il tipo di ritorno: restituisce un Ordine singolo, non una List
    public Ordine doRetrieveByID(int id) {
        String query = "SELECT * FROM Ordine WHERE id_ordine = ?";
        Ordine ordine = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ordine = mapResultSetToBean(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordine;
    }

    // ==========================================
    // METODO UTILITY
    // ==========================================
    private Ordine mapResultSetToBean(ResultSet rs) throws SQLException {
        Ordine o = new Ordine();
        o.setIdOrdine(rs.getInt("id_ordine"));

        // Creo un utente "vuoto" ma con l'ID corretto
        Utente u = new Utente();
        u.setIdUtente(rs.getInt("id_utente"));
        o.setUtente(u);

        o.setStato(rs.getString("stato"));
        o.setTotale(rs.getDouble("totale"));

        // Stesso fix applicato prima per la data!
        java.sql.Timestamp timestampDB = rs.getTimestamp("data_ordine");
        if (timestampDB != null) {
            // Se usi java.sql.Date
            o.setDataOrdine(new java.sql.Date(timestampDB.getTime()));

            // NOTA: Se invece nel Bean hai usato java.sql.Timestamp,
            // basta fare: o.setDataOrdine(timestampDB);
        }

        return o;
    }


    public void salvaOrdineCompleto(int idUtente, Carrello carrello) throws SQLException {
            Connection con = null;
            PreparedStatement psOrdine = null;
            PreparedStatement psDettaglio = null;
            ResultSet rs = null;

            try {
                // 1. Apriamo la connessione
                con = DBConnection.getConnection();

                // 2. DISABILITIAMO L'AUTOSALVATAGGIO (Inizio Transazione)
                con.setAutoCommit(false);

                // 3. INSERIAMO L'ORDINE (Usiamo RETURN_GENERATED_KEYS per farci dare l'ID che MySQL creerà)
                String queryOrdine = "INSERT INTO Ordine (id_utente, data_ordine, totale) VALUES (?, CURDATE(), ?)";
                psOrdine = con.prepareStatement(queryOrdine, Statement.RETURN_GENERATED_KEYS);
                psOrdine.setInt(1, idUtente);
                psOrdine.setDouble(2, carrello.getTotale());
                psOrdine.executeUpdate();

                // 4. RECUPERIAMO L'ID DELL'ORDINE APPENA CREATO
                rs = psOrdine.getGeneratedKeys();
                int idOrdineGenerato = 0;
                if (rs.next()) {
                    idOrdineGenerato = rs.getInt(1);
                } else {
                    throw new SQLException("Creazione ordine fallita, nessun ID generato.");
                }

                // 5. INSERIAMO TUTTI I DETTAGLI (Le piante)
                String queryDettaglio = "INSERT INTO Dettaglio_Ordine (id_ordine, id_pianta, quantita, prezzo_unitario) VALUES (?, ?, ?, ?)";
                psDettaglio = con.prepareStatement(queryDettaglio);

                for (ItemCarrello item : carrello.getArticoli()) {
                    psDettaglio.setInt(1, idOrdineGenerato);                     // L'ID appena recuperato
                    psDettaglio.setInt(2, item.getPianta().getIdPianta());       // ID della pianta
                    psDettaglio.setInt(3, item.getQuantita());                   // Quante ne ha prese
                    psDettaglio.setDouble(4, item.getPianta().getPrezzo());      // Congeliamo il prezzo di oggi!

                    psDettaglio.executeUpdate(); // Eseguiamo per ogni pianta
                }

                // 6. TUTTO È ANDATO BENE: CONFERMIAMO IL SALVATAGGIO (Commit)
                con.commit();

            } catch (SQLException e) {
                // 7. DISASTRO! QUALCOSA È ANDATO STORTO: ANNULLIAMO TUTTO (Rollback)
                if (con != null) {
                    con.rollback();
                }
                throw e; // Rilanciamo l'errore per farlo gestire alla Servlet
            } finally {
                // 8. PULIZIA E RIPRISTINO
                if (rs != null) rs.close();
                if (psDettaglio != null) psDettaglio.close();
                if (psOrdine != null) psOrdine.close();
                if (con != null) {
                    con.setAutoCommit(true); // Ripristiniamo il comportamento normale prima di chiudere
                    con.close();
                }
            }
        }
    public List<Model.Beans.Ordine> getOrdiniByUtente(int idUtente) throws SQLException {
        List<Model.Beans.Ordine> storico = new ArrayList<>();
        String query = "SELECT * FROM Ordine WHERE id_utente = ? ORDER BY data_ordine DESC";

        try (Connection con = DBConnection.getConnection(); // Adatta con il tuo nome della connessione
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idUtente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Model.Beans.Ordine ordine = new Model.Beans.Ordine();
                    ordine.setIdOrdine(rs.getInt("id_ordine"));

                    // --- LA CORREZIONE È QUI ---
                    // Creiamo l'entità Utente, le assegniamo l'ID e la passiamo all'Ordine
                    Model.Beans.Utente utente = new Model.Beans.Utente();
                    utente.setIdUtente(rs.getInt("id_utente"));
                    ordine.setUtente(utente); // Assicurati che il metodo si chiami setUtente() nella tua classe
                    // ---------------------------

                    ordine.setDataOrdine(rs.getDate("data_ordine"));
                    ordine.setStato(rs.getString("stato"));
                    ordine.setTotale(rs.getDouble("totale"));

                    storico.add(ordine);
                }
            }
        }
        return storico;
    }
}


