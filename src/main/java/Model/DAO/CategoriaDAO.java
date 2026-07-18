package Model.DAO;

import Model.Beans.Categoria;
import Model.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public Categoria doRetrieveById(int id) {
        String query = "SELECT * FROM Categoria WHERE id_categoria=?";
        Categoria categoria = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoria = new Categoria();
                    categoria.setIdCategoria(rs.getInt("id_categoria"));
                    categoria.setNomeCategoria(rs.getString("nome_categoria"));
                    categoria.setDescrizione(rs.getString("descrizione"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    public List<Categoria> doRetrieveAll() {
        String query = "SELECT * FROM Categoria";
        List<Categoria> categorie = new ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNomeCategoria(rs.getString("nome_categoria"));
                categoria.setDescrizione(rs.getString("descrizione"));
                categorie.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorie;
    }

    public boolean doInsert(Categoria categoria) {
        if (categoria == null) return false;
        String query = "INSERT INTO Categoria (nome_categoria, descrizione) VALUES (?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, categoria.getNomeCategoria());
            ps.setString(2, categoria.getDescrizione());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doUpdate(Categoria categoria) {
        if (categoria == null) return false;
        String query = "UPDATE Categoria SET nome_categoria=?, descrizione=? WHERE id_categoria=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, categoria.getNomeCategoria());
            ps.setString(2, categoria.getDescrizione());
            ps.setInt(3, categoria.getIdCategoria());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean doDelete(Categoria categoria) {
        if (categoria == null) return false;
        String query = "DELETE FROM Categoria WHERE id_categoria=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, categoria.getIdCategoria());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    }
