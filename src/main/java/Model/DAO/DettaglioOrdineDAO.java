package Model.DAO;

import Model.Beans.DettaglioOrdine;
import Model.Beans.Ordine;
import Model.Beans.Pianta;
import Model.Beans.Utente;
import Model.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.List;

public class DettaglioOrdineDAO {

    public boolean doSave(DettaglioOrdine dettaglio) {
        String query = "INSERT INTO Dettaglio_Ordine (id_ordine, id_pianta, quantita, prezzo_unitario) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, dettaglio.getOrdine().getIdOrdine());
            ps.setInt(2, dettaglio.getPianta().getIdPianta());
            ps.setInt(3, dettaglio.getQuantita());
            ps.setDouble(4, dettaglio.getPrezzoUnitario());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doUpdate(DettaglioOrdine dettaglio) {
        String query = "UPDATE Dettaglio_Ordine SET id_ordine=?, id_pianta=?, quantita=?, prezzo_unitario=? WHERE id_dettaglio=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, dettaglio.getOrdine().getIdOrdine());
            ps.setInt(2, dettaglio.getPianta().getIdPianta());
            ps.setInt(3, dettaglio.getQuantita());
            ps.setDouble(4, dettaglio.getPrezzoUnitario());
            ps.setInt(5, dettaglio.getIdDettaglio());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doDelete(DettaglioOrdine dettaglio) {
        String query = "DELETE FROM Dettaglio_Ordine WHERE id_dettaglio = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, dettaglio.getIdDettaglio());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doInsert(int idOrdine, Pianta pianta, int quantita) {
        String query = "INSERT INTO Dettaglio_Ordine (id_ordine, id_pianta, quantita, prezzo_unitario) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idOrdine);
            ps.setInt(2, pianta.getIdPianta());
            ps.setInt(3, quantita);
            ps.setDouble(4, pianta.getPrezzo()); // Congeliamo il prezzo al momento dell'acquisto

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // POTENZIATO: Effettua una INNER JOIN con la tabella Pianta
    public List<DettaglioOrdine> doRetrieveByOrderID(int idOrdine) {
        // Seleziono i campi del dettaglio e i campi utili della pianta associata
        String query = "SELECT d.id_dettaglio, d.id_ordine, d.id_pianta, d.quantita, d.prezzo_unitario, " +
                "p.nome_comune, p.nome_scientifico " +
                "FROM Dettaglio_Ordine d " +
                "INNER JOIN Pianta p ON d.id_pianta = p.id_pianta " +
                "WHERE d.id_ordine = ?";

        List<DettaglioOrdine> dettagli = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idOrdine);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DettaglioOrdine d = new DettaglioOrdine();
                    d.setIdDettaglio(rs.getInt("id_dettaglio"));
                    d.setQuantita(rs.getInt("quantita"));
                    d.setPrezzoUnitario(rs.getDouble("prezzo_unitario"));

                    // Bean Ordine fittizio
                    Ordine o = new Ordine();
                    o.setIdOrdine(rs.getInt("id_ordine"));
                    d.setOrdine(o);

                    // Bean Pianta popolato grazie alla JOIN!
                    Pianta p = new Pianta();
                    p.setIdPianta(rs.getInt("id_pianta"));
                    p.setNomeComune(rs.getString("nome_comune"));
                    p.setNomeScientifico(rs.getString("nome_scientifico"));
                    // Se vuoi aggiungere l'immagine o altro in futuro, basta aggiungerlo alla SELECT e qui
                    d.setPianta(p);

                    dettagli.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dettagli;
    }
}