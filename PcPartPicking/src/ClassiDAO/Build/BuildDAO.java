package ClassiDAO.Build;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Build.Build;
import Connection.DBConnection;


public class BuildDAO {

    //Inserimento Build
    public void doSave(Build b) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO BUILDS (id_build, nome, data_creazione, note, assemblatore) " + "VALUES (SEQ_BUILD.NEXTVAL, ?, ?, ?, ?)";
            
            ps = conn.prepareStatement(sql);
            ps.setString(1, b.getNome());
            ps.setDate(2, b.getDataCreazione()); // Gestione java.sql.Date
            ps.setString(3, b.getNote());
            ps.setString(4, b.getAssemblatore());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Recupera tutte le build create da uno specifico assemblatore
    public List<Build> doRetrieveByAssemblatore(String usernameAssemblatore) {
        List<Build> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM BUILDS WHERE assemblatore = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, usernameAssemblatore);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Build(
                    rs.getInt("id_build"),
                    rs.getString("nome"),
                    rs.getDate("data_creazione"),
                    rs.getString("note"),
                    rs.getString("assemblatore")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
