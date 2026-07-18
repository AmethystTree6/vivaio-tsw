package Model.DAO;

import Model.Beans.Utente;
import Model.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAO {
 public Utente doRetrieveById(int id) {
        String query = "SELECT * FROM Utente WHERE id_utente = ?";
        Utente utente = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    utente = mapResultSetToBean(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
     return utente;
    }

    public Utente doRetrieveByEmail(String email) {
        String query = "SELECT * FROM Utente WHERE email = ?";
        Utente utente = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    utente = mapResultSetToBean(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utente;
    }

    public List<Utente> doRetrieveAll() {
        String query = "SELECT * FROM Utente";
        List<Utente> utenti = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                utenti.add(mapResultSetToBean(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utenti;
    }

    public boolean doInsert(Utente utente) {
        // Non inseriamo data_registrazione perché il DB ha "DEFAULT CURRENT_TIMESTAMP"
        String query = "INSERT INTO Utente (nome, cognome, email, password_hash, telefono, ruolo) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getPasswordHash());
            ps.setString(5, utente.getTelefono());
            ps.setString(6, utente.getRuolo() != null ? utente.getRuolo() : "cliente");

            // executeUpdate restituisce il numero di righe modificate
            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doUpdate(Utente utente) {
        // Nota: Non aggiorno la password_hash qui per evitare di sovrascriverla accidentalmente
        // quando si aggiorna solo il profilo. C'è il metodo apposito per la password.
        String query = "UPDATE Utente SET nome=?, cognome=?, email=?, telefono=?, ruolo=? WHERE id_utente=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, utente.getNome());
            ps.setString(2, utente.getCognome());
            ps.setString(3, utente.getEmail());
            ps.setString(4, utente.getTelefono());
            ps.setString(5, utente.getRuolo() != null ? utente.getRuolo() : "cliente");
            ps.setInt(6, utente.getIdUtente());

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doUpdatePassword(int idUtente, String passwordHash) {
        String query = "UPDATE Utente SET password_hash=? WHERE id_utente=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, passwordHash);
            ps.setInt(2, idUtente);

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doDelete(int idUtente) {
        String query = "DELETE FROM Utente WHERE id_utente = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idUtente);

            int result = ps.executeUpdate();
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsByEmail(String email) {
        String query = "SELECT 1 FROM Utente WHERE email = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                // Se rs.next() è true, vuol dire che c'è almeno un record con questa email
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ==========================================
    // METODO UTILITY
    // ==========================================
    private Utente mapResultSetToBean(ResultSet rs) throws SQLException {
        Utente u = new Utente();
        u.setIdUtente(rs.getInt("id_utente"));
        u.setNome(rs.getString("nome"));
        u.setCognome(rs.getString("cognome"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setTelefono(rs.getString("telefono"));
        u.setRuolo(rs.getString("ruolo"));

        // RECUPERO E CONVERSIONE DELLA DATA (Versione per java.sql.Date)
        java.sql.Timestamp timestampDB = rs.getTimestamp("data_registrazione");
        if (timestampDB != null) {
            // Creiamo esplicitamente un java.sql.Date usando i millisecondi del DB
            u.setDataRegistrazione(new java.sql.Date(timestampDB.getTime()));
        }
         return u;
    }
}
