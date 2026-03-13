package ClassiDAO.Componente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ClassiDiModello.Componente.CasePC;
import Connection.DBConnection;

public class CaseDAO {

    //Inserimento Case
    public void doSave(CasePC c) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO CASES (id_case, marca, modello, tdp, form_factor, supporto_radiatore, amministratore) " + "VALUES (SEQ_HARDWARE.NEXTVAL, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, c.getMarca());
            ps.setString(2, c.getModello());
            ps.setInt(3, c.getTdp());
            ps.setString(4, c.getFormFactor());
            ps.setString(5, c.getSupportoRadiatore());
            ps.setString(6, c.getAmministratore());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    //Ricerca Case per Marca e Form Factor
    public List<CasePC> doRetrieveByMarcaFormFactor(String marca, String formFactor) {
        List<CasePC> lista = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM CASES WHERE marca = ? AND form_factor = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, marca);
            ps.setString(2, formFactor);
            rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(new CasePC(
                    rs.getInt("id_case"),
                    rs.getString("marca"),
                    rs.getString("modello"),
                    rs.getInt("tdp"),
                    rs.getString("form_factor"),
                    rs.getString("supporto_radiatore"),
                    rs.getString("amministratore")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}