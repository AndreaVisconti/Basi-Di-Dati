package ClassiDAO.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Componente.Motherboard;
import Connection.DBConnection;

public class MotherboardDAO {

    // Inserimento Matherboard
    public void doSave(Motherboard mb) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO MOTHERBOARDS (id_mb, marca, modello, tdp, chipset, form_factor, max_ram, socket, slot_dimm, amministratore) VALUES (SEQ_HARDWARE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, mb.getMarca());
            ps.setString(2, mb.getModello());
            ps.setInt(3, mb.getTdp());
            ps.setString(4, mb.getChipset());
            ps.setString(5, mb.getFormFactor());
            ps.setInt(6, mb.getMaxRam());
            ps.setString(7, mb.getSocket());
            ps.setInt(8, mb.getSlotDimm());
            ps.setString(9, mb.getAmministratore());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ricerca per Marca e Socket
    public List<Motherboard> doRetrieveByMarcaSocket(String marca, String socket) {
        List<Motherboard> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM MOTHERBOARDS WHERE marca = ? AND socket = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, marca);
            ps.setString(2, socket);
            rs = ps.executeQuery();

            while (rs.next()) {
                Motherboard mb = new Motherboard(
                    rs.getInt("id_mb"),
                    rs.getString("marca"),
                    rs.getString("modello"),
                    rs.getInt("tdp"),
                    rs.getString("chipset"),
                    rs.getString("form_factor"),
                    rs.getInt("max_ram"),
                    rs.getString("socket"),
                    rs.getInt("slot_dimm"),
                    rs.getString("amministratore")
                );
                lista.add(mb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
