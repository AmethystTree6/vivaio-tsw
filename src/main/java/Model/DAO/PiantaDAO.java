package Model.DAO;

import Model.Beans.Pianta;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class PiantaDAO {

    public List<Pianta> doRetrieveALl() {
        String query = "SELECT * FROM pianta";
        List<Pianta> piante = new ArrayList<>();

    }

    public List<Pianta> doRetrieveByID(int id) {
        String query = "SELECT * FROM pianta WHERE id = ?";
        List<Pianta> piante = new ArrayList<>();
    }

    public List<Pianta> doRetrieveByCategoria(int idCategoria){
        String query = "SELECT * FROM pianta WHERE categoria = ?";
        List<Pianta> piante = new ArrayList<>();
    }

    public List<Pianta> doRetrieveByNome(String nome) {
        String query = "SELECT * FROM pianta WHERE nome LIKE ?";
        List<Pianta> piante = new ArrayList<>();
    }

    public boolean doSave(Pianta p){
        String query ="INSERT INTO Pianta\n" +
                "(\n" +
                "id_categoria,\n" +
                "nome_comune,\n" +
                "nome_scientifico,\n" +
                "descrizione,\n" +
                "prezzo,\n" +
                "esposizione,\n" +
                "altezza,\n" +
                "diametro_vaso,\n" +
                "quantita_magazzino,\n" +
                "disponibile\n" +
                ")\n" +
                "VALUES\n" +
                "(?,?,?,?,?,?,?,?,?,?)";

    }

    public boolean doDelete(Pianta p){
        String query = "DELETE FROM pianta WHERE id = ?";
    }
    public boolean doUpdate(Pianta p){
        String query = "UPDATE Pianta\n" +
                "SET\n" +
                "nome_comune=?,\n" +
                "descrizione=?,\n" +
                "prezzo=?,\n" +
                "quantita_magazzino=?\n" +
                "WHERE id_pianta=?";
    }



}
