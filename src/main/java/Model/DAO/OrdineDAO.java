package Model.DAO;

import Model.Beans.Ordine;
import Model.Beans.Utente;
import Model.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public List<Ordine> doRetrieveByUtente(Utente utente) {
        String query = "SELECT * FROM Ordine WHERE id_utente = ? ORDER BY data_ordine DESC";
        List<Ordine> ordini = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, utente.getIdUtente());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    ordini.add(mapResultSetToBean(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordini;
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
}
