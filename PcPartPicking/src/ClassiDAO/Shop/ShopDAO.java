package ClassiDAO.Shop;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Shop.Shop;
import Connection.DBConnection;

public class ShopDAO {

    //Inserimento Shop
    public void doSave(Shop s) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO SHOPS (partita_iva, nome, url_sito_web) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, s.getPartitaIva());
            ps.setString(2, s.getNome());
            ps.setString(3, s.getUrlSitoWeb());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Ricerca Shop
    public List<Shop> doRetrieveAll() {
        List<Shop> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM SHOPS";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Shop(
                    rs.getString("partita_iva"),
                    rs.getString("nome"),
                    rs.getString("url_sito_web")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}