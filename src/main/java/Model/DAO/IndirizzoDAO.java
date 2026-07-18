package Model.DAO;

import Model.Beans.Indirizzo;
import Model.Beans.Utente;
import Model.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class IndirizzoDAO {

    public void doSave(Indirizzo indirizzo) {
        String query = "INSERT INTO Indirizzo (id_utente, via, numero_civico, citta, cap, provincia, paese, tipo) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, indirizzo.getUtente().getIdUtente());
            ps.setString(2, indirizzo.getVia());
            ps.setString(3, indirizzo.getNumeroCivico());
            ps.setString(4, indirizzo.getCitta());
            ps.setString(5, indirizzo.getCap());
            ps.setString(6, indirizzo.getProvincia());
            ps.setString(7, indirizzo.getPaese());
            ps.setString(8, indirizzo.getTipo()); // 'spedizione' o 'fatturazione'

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doUpdate(Indirizzo indirizzo) {
        String query = "UPDATE Indirizzo SET id_utente=?, via=?, numero_civico=?, citta=?, cap=?, provincia=?, paese=?, tipo=? WHERE id_indirizzo=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, indirizzo.getUtente().getIdUtente());
            ps.setString(2, indirizzo.getVia());
            ps.setString(3, indirizzo.getNumeroCivico());
            ps.setString(4, indirizzo.getCitta());
            ps.setString(5, indirizzo.getCap());
            ps.setString(6, indirizzo.getProvincia());
            ps.setString(7, indirizzo.getPaese());
            ps.setString(8, indirizzo.getTipo());
            ps.setInt(9, indirizzo.getIdIndirizzo());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void doDelete(Indirizzo indirizzo) {
        String query = "DELETE FROM Indirizzo WHERE id_indirizzo = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, indirizzo.getIdIndirizzo());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Indirizzo> doRetrieveAll() {
        String query = "SELECT * FROM Indirizzo";
        List<Indirizzo> indirizzi = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                indirizzi.add(mapResultSetToBean(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indirizzi;
    }

    public List<Indirizzo> doRetrieveByUtente(Utente utente) {
        String query = "SELECT * FROM Indirizzo WHERE id_utente = ?";
        List<Indirizzo> indirizzi = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, utente.getIdUtente());
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    indirizzi.add(mapResultSetToBean(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return indirizzi;
    }

    // ==========================================
    // METODO UTILITY
    // ==========================================
    private Indirizzo mapResultSetToBean(ResultSet rs) throws SQLException {
        Indirizzo i = new Indirizzo();
        i.setIdIndirizzo(rs.getInt("id_indirizzo"));

        // Ricostruisco l'oggetto Utente fittizio per passargli l'ID
        Utente u = new Utente();
        u.setIdUtente(rs.getInt("id_utente"));
        i.setUtente(u);

        i.setVia(rs.getString("via"));
        i.setNumeroCivico(rs.getString("numero_civico"));
        i.setCitta(rs.getString("citta"));
        i.setCap(rs.getString("cap"));
        i.setProvincia(rs.getString("provincia"));
        i.setPaese(rs.getString("paese"));
        i.setTipo(rs.getString("tipo"));

        return i;
    }
}
