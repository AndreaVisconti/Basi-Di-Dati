package ClassiDAO.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Componente.GPU;
import Connection.DBConnection;

public class GpuDAO {

    //Inserimento GPU
    public void doSave(GPU gpu) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO GPU (id_gpu, marca, modello, tdp, vram, amministratore) " + "VALUES (SEQ_HARDWARE.NEXTVAL, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, gpu.getMarca());
            ps.setString(2, gpu.getModello());
            ps.setInt(3, gpu.getTdp());
            ps.setInt(4, gpu.getVram());
            ps.setString(5, gpu.getAmministratore());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     //Ricerca per Marca e VRAM minima
    public List<GPU> doRetrieveByMarcaVram(String marca, int vramMin) {
        List<GPU> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM GPU WHERE marca = ? AND vram >= ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, marca);
            ps.setInt(2, vramMin);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new GPU(
                    rs.getInt("id_gpu"),
                    rs.getString("marca"),
                    rs.getString("modello"),
                    rs.getInt("tdp"),
                    rs.getInt("vram"),
                    rs.getString("amministratore")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
