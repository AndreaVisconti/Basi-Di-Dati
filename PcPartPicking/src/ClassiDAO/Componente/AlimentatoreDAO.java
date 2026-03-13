package ClassiDAO.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Componente.Alimentatore;
import Connection.DBConnection;

public class AlimentatoreDAO {

    //Inserimento Alimentatore
    public void doSave(Alimentatore ali) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO ALIMENTATORI (id_psu, marca, modello, tdp, potenza, certificazione, amministratore) " + "VALUES (SEQ_HARDWARE.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ali.getMarca());
            ps.setString(2, ali.getModello());
            ps.setInt(3, ali.getTdp());
            ps.setInt(4, ali.getPotenzaNominale());
            ps.setString(5, ali.getCertificazione());
            ps.setString(6, ali.getAmministratore());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     //Ricerca alimentatori per Marca e Potenza Nominale minima.
    public List<Alimentatore> doRetrieveByMarcaPotenza(String marca, int potenzaMin) {
        List<Alimentatore> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM ALIMENTATORI WHERE marca = ? AND potenza_nominale >= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, marca);
            ps.setInt(2, potenzaMin);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Alimentatore(
                    rs.getInt("id_psu"),
                    rs.getString("marca"),
                    rs.getString("modello"),
                    rs.getInt("tdp"),
                    rs.getInt("potenza_nominale"),
                    rs.getString("certificazione"),
                    rs.getString("amministratore")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
