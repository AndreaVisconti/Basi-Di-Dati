package ClassiDAO.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Shop.Offerta;
import Connection.DBConnection;

public class OffertaDAO {

    //Inserimento Offerta
    public void doSave(Offerta o) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO OFFERTE (shop, id_build, prezzo, link_prodotto, quantita, disponibilita) VALUES (?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            
            ps.setString(1, o.getShop());
            ps.setInt(2, o.getIdBuild());
            ps.setDouble(3, o.getPrezzo());
            ps.setString(4, o.getLinkProdotto());
            ps.setInt(5, o.getQuantita());
            // JDBC non ha setChar, quindi convertiamo il char in String
            ps.setString(6, String.valueOf(o.getDisponibilita()));
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     //Recupera tutte le offerte disponibili per una specifica Build
    public List<Offerta> doRetrieveByBuild(int idBuild) {
        List<Offerta> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM OFFERTE WHERE id_build = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idBuild);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Offerta(
                    rs.getString("shop"),
                    rs.getInt("id_build"),
                    rs.getDouble("prezzo"),
                    rs.getString("link_prodotto"),
                    rs.getInt("quantita"),
                    rs.getString("disponibilita").charAt(0) // Prendiamo il primo carattere della String
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
