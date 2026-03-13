package ClassiDAO.Feedabck;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Feedback.Feedback;
import Connection.DBConnection;

public class FeedbackDAO {

    //Inserimento nuovo feedback per una build
    public void doSave(Feedback f) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO FEEDBACK (assemblatore, id_build, voto, giudizio) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, f.getAssemblatore());
            ps.setInt(2, f.getBuild());
            ps.setInt(3, f.getVoto());
            ps.setString(4, f.getGiudizio());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

     //Recupera tutti i feedback lasciati per una specifica Build
    public List<Feedback> doRetrieveByBuild(int idBuild) {
        List<Feedback> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM FEEDBACK WHERE id_build = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idBuild);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new Feedback(
                    rs.getString("assemblatore"),
                    rs.getInt("id_build"),
                    rs.getInt("voto"),
                    rs.getString("giudizio")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}