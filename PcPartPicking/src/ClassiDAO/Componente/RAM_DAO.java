package ClassiDAO.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Componente.RAM;
import Connection.DBConnection;

public class RAM_DAO {

    //Inserimento RAM
    public void doSave(RAM ram) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO RAM (id_ram, marca, modello, capacità, tdp, frequenza, tipo, amministratore) " + "VALUES (SEQ_HARDWARE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, ram.getMarca());
            ps.setString(2, ram.getModello());
            ps.setInt(3, ram.getTdp());
            ps.setInt(4, ram.getCapacita());
            ps.setString(5, ram.getTipo());
            ps.setInt(6, ram.getFrequenza());
            ps.setString(7, ram.getAmministratore());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     //Ricerca RAM per Marca e Tipo
    public List<RAM> doRetrieveByMarcaTipo(String marca, String tipo) {
        List<RAM> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM RAM WHERE marca = ? AND tipo = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, marca);
            ps.setString(2, tipo);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new RAM(
                    rs.getInt("id_ram"),
                    rs.getString("marca"),
                    rs.getString("modello"),
                    rs.getInt("tdp"),
                    rs.getInt("capacita"),
                    rs.getString("tipo"),
                    rs.getInt("frequenza"),
                    rs.getString("amministratore")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
