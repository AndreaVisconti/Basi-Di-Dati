package ClassiDAO.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Componente.Dissipatore;
import Connection.DBConnection;

public class DissipatoreDAO {

    //Inserimento Dissipatore
    public void doSave(Dissipatore d) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO DISSIPATORI (id_cooler, marca, modello, tdp, socket, tipo, dimensione, amministratore) " + "VALUES (SEQ_HARDWARE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, d.getMarca());
            ps.setString(2, d.getModello());
            ps.setInt(3, d.getTdp());
            ps.setString(4, d.getSocket());
            ps.setString(5, d.getTipo());
            ps.setInt(6, d.getDimensione());
            ps.setString(7, d.getAmministratore());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    //Ricerca dissipatori per Marca e Socket
    public List<Dissipatore> doRetrieveByMarcaSocket(String marca, String socket) {
        List<Dissipatore> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM DISSIPATORI WHERE marca = ? AND socket = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, marca);
            ps.setString(2, socket);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Dissipatore(
                    rs.getInt("id_cooler"),
                    rs.getString("marca"),
                    rs.getString("modello"),
                    rs.getInt("tdp"),
                    rs.getString("socket"),
                    rs.getString("tipo"),
                    rs.getInt("dimensione"),
                    rs.getString("amministratore")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}