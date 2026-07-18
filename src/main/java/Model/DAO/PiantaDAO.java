package Model.DAO;

import Model.Beans.Categoria;
import Model.Beans.Pianta;
import Model.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PiantaDAO {

    public synchronized List<Pianta> doRetrieveAll() throws SQLException {
        String query = "SELECT * FROM Pianta";
        List<Pianta> piante = new ArrayList<>();

        try (Connection con = (Connection) DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                piante.add(mapResultSetToBean(rs));
            }
        }
        return piante;
    }

    public synchronized Pianta doRetrieveById(int id) throws SQLException {
        String query = "SELECT * FROM Pianta WHERE id_pianta = ?";
        Pianta pianta = null;

        try (Connection con = (Connection) DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    pianta = mapResultSetToBean(rs);
                }
            }
        }
        return pianta;
    }

    public synchronized List<Pianta> doRetrieveByCategoria(int idCategoria) throws SQLException {
        String query = "SELECT * FROM Pianta WHERE id_categoria = ?";
        List<Pianta> piante = new ArrayList<>();

        try (Connection con = (Connection) DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, idCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    piante.add(mapResultSetToBean(rs));
                }
            }
        }
        return piante;
    }

    public synchronized List<Pianta> doSearch(String nome) throws SQLException {
        // Cerca sia nel nome comune che nel nome scientifico tramite la clausola LIKE
        String query = "SELECT * FROM Pianta WHERE nome_comune LIKE ? OR nome_scientifico LIKE ?";
        List<Pianta> piante = new ArrayList<>();

        try (Connection con = (Connection) DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            String pattern = "%" + nome + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    piante.add(mapResultSetToBean(rs));
                }
            }
        }
        return piante;
    }

    public synchronized void doInsert(Pianta pianta) throws SQLException {
        String query = "INSERT INTO Pianta (id_categoria, nome_comune, nome_scientifico, descrizione, prezzo, esposizione, altezza, diametro_vaso, quantita_magazzino, disponibile) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = (Connection) DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, pianta.getCategoria().getIdCategoria());
            ps.setString(2, pianta.getNomeComune());
            ps.setString(3, pianta.getNomeScientifico());
            ps.setString(4, pianta.getDescrizione());
            ps.setDouble(5, pianta.getPrezzo());
            ps.setString(6, pianta.getEsposizione());
            ps.setDouble(7, pianta.getAltezza());
            ps.setDouble(8, pianta.getDiametroVaso());
            ps.setInt(9, pianta.getQuantitaMagazzino());
            ps.setBoolean(10, pianta.isDisponibile());

            ps.executeUpdate();
        }
    }

    public synchronized void doUpdate(Pianta pianta) throws SQLException {
        String query = "UPDATE Pianta SET id_categoria=?, nome_comune=?, nome_scientifico=?, descrizione=?, prezzo=?, esposizione=?, altezza=?, diametro_vaso=?, quantita_magazzino=?, disponibile=? WHERE id_pianta=?";

        try (Connection con = (Connection) DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, pianta.getCategoria().getIdCategoria());
            ps.setString(2, pianta.getNomeComune());
            ps.setString(3, pianta.getNomeScientifico());
            ps.setString(4, pianta.getDescrizione());
            ps.setDouble(5, pianta.getPrezzo());
            ps.setString(6, pianta.getEsposizione());
            ps.setDouble(7, pianta.getAltezza());
            ps.setDouble(8, pianta.getDiametroVaso());
            ps.setInt(9, pianta.getQuantitaMagazzino());
            ps.setBoolean(10, pianta.isDisponibile());
            ps.setInt(11, pianta.getIdPianta());

            ps.executeUpdate();
        }
    }

    public synchronized void doDelete(int id) throws SQLException {
        String query = "DELETE FROM Pianta WHERE id_pianta = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    // Metodo di utility per mappare il ResultSet al Bean
    private Pianta mapResultSetToBean(ResultSet rs) throws SQLException {
        Pianta p = new Pianta();
        p.setIdPianta(rs.getInt("id_pianta"));
        p.setNomeComune(rs.getString("nome_comune"));
        p.setNomeScientifico(rs.getString("nome_scientifico"));
        p.setDescrizione(rs.getString("descrizione"));
        p.setPrezzo(rs.getDouble("prezzo"));
        p.setEsposizione(rs.getString("esposizione"));
        p.setAltezza(rs.getDouble("altezza"));
        p.setDiametroVaso(rs.getDouble("diametro_vaso"));
        p.setQuantitaMagazzino(rs.getInt("quantita_magazzino"));
        p.setDisponibile(rs.getBoolean("disponibile"));
        // Soluzione base:
        Categoria c = new Categoria();
        c.setIdCategoria(rs.getInt("id_categoria"));
        p.setCategoria(c);
        return p;
    }
}
