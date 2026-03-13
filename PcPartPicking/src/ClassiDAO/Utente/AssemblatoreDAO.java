package ClassiDAO.Utente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ClassiDiModello.Utente.Assemblatore;
import Connection.DBConnection;

public class AssemblatoreDAO {

    public Assemblatore doLogin(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Assemblatore ass = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM ASSEMBLATORI WHERE username = ? AND password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                ass = new Assemblatore(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getBytes("foto"),
                    rs.getString("biografia"),
                    rs.getDouble("livello_reputazione")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ass;
    }

    public void doSave(Assemblatore a) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO ASSEMBLATORI (username, password, nome, cognome, email, telefono, foto, biografia, livello_reputazione) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getUsername());
            ps.setString(2, a.getPassword());
            ps.setString(3, a.getNome());
            ps.setString(4, a.getCognome());
            ps.setString(5, a.getEmail());
            ps.setString(6, a.getTelefono());
            ps.setBytes(7, a.getFoto());
            ps.setString(8, a.getBiografia());
            ps.setDouble(9, a.getLivelloReputazione());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
