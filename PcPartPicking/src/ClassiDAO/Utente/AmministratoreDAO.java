package ClassiDAO.Utente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ClassiDiModello.Utente.Amministratore;
import Connection.DBConnection;

public class AmministratoreDAO {

    public Amministratore doLogin(String username, String password) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Amministratore admin = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM AMMINISTRATORI WHERE username = ? AND password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();

            if (rs.next()) {
                admin = new Amministratore(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("nome"),
                    rs.getString("cognome"),
                    rs.getString("email"),
                    rs.getString("telefono"),
                    rs.getBytes("foto"),
                    rs.getString("id_aziendale")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }
}