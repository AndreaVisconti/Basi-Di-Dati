package ClassiDAO.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Componente.CPU;
import Connection.DBConnection;

public class CPU_DAO {

    //Inserimento CPU
    public void doSave(CPU cpu) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO CPU (id_cpu, marca, modello, tdp, socket, core, frequenza, amministratore) VALUES (SEQ_HARDWARE.NEXTVAL, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, cpu.getMarca());
            ps.setString(2, cpu.getModello());
            ps.setInt(3, cpu.getTdp());
            ps.setString(4, cpu.getSocket());
            ps.setInt(5, cpu.getCore());
            ps.setDouble(6, cpu.getFrequenza());
            ps.setString(7, cpu.getAmministratore());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Ricerca CPU per Marca e Socket
    public List<CPU> doRetrieveByMarcaSocket(String marca, String socket) {
        List<CPU> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM CPU WHERE marca = ? AND socket = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, marca);
            ps.setString(2, socket);
            rs = ps.executeQuery();

            while (rs.next()) {
                CPU cpu = new CPU(
                    rs.getInt("id_cpu"),
                    rs.getString("marca"),
                    rs.getString("modello"),
                    rs.getInt("tdp"),
                    rs.getString("socket"),
                    rs.getInt("core"),
                    rs.getDouble("frequenza"),
                    rs.getString("amministratore")
                );
                lista.add(cpu);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
