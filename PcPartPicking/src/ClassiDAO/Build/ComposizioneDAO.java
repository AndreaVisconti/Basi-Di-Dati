package ClassiDAO.Build;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import java.sql.SQLException;

import ClassiDiModello.Build.Composizione;
import Connection.DBConnection;

public class ComposizioneDAO {

    //Salva la composizione dei pezzi per una Build.
    public String doSave(Composizione c) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "INSERT INTO COMPOSIZIONI (id_build, id_cpu, id_mb, id_ram, id_gpu, id_psu, id_case, id_diss) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            
            ps.setInt(1, c.getIdBuild());
            setOptionalInt(ps, 2, c.getIdCpu());
            setOptionalInt(ps, 3, c.getIdMb());
            setOptionalInt(ps, 4, c.getIdRam());
            setOptionalInt(ps, 5, c.getIdGpu());
            setOptionalInt(ps, 6, c.getIdPsu());
            setOptionalInt(ps, 7, c.getIdCase());
            setOptionalInt(ps, 8, c.getIdCooler());
            
            ps.executeUpdate(); // Qui scattano i TRIGGER di compatibilità
            
            // Se arriviamo qui, l'inserimento è riuscito. Controlliamo l'energia.
            return eseguiVerificaEnergia(c.getIdBuild());

        } catch (SQLException e) {
            // Se il TRIGGER lancia un errore (socket incompatibile)
            if (e.getErrorCode() >= 20000) return "ERRORE: " + e.getMessage();
            e.printStackTrace();
            return "Errore durante il salvataggio.";
        }
    }

    //Se l'ID è 0 o negativo, scrive NULL nel database
    private void setOptionalInt(PreparedStatement ps, int index, int value) throws SQLException {
        if (value <= 0) {
            ps.setNull(index, java.sql.Types.INTEGER);
        } else {
            ps.setInt(index, value);
        }
    }

     //Chiama la procedura PL/SQL PROC_VERIFICA_PSU definita su Oracle e restituisce il messaggio se l'alimentatore è piccolo
    public String eseguiVerificaEnergia(int idBuild) {
        Connection conn = null;
        CallableStatement cs = null;
        try {
            conn = DBConnection.getConnection();
            cs = conn.prepareCall("{call PROC_VERIFICA_PSU(?)}");
            cs.setInt(1, idBuild);
            cs.execute();
        } catch (SQLException e) {
            // Cattura l'avviso personalizzato di Oracle
            if (e.getErrorCode() >= 20000) return e.getMessage();
        }
        return null; // Nessun avviso
    }

    //Modifica Build esistente
    public String doUpdate(Composizione c) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBConnection.getConnection();
            String sql = "UPDATE COMPOSIZIONI SET id_cpu=?, id_mb=?, id_ram=?, id_gpu=?, id_psu=?, id_case=?, id_diss=? WHERE id_build=?";
            ps = conn.prepareStatement(sql);
            
            setOptionalInt(ps, 1, c.getIdCpu());
            setOptionalInt(ps, 2, c.getIdMb());
            setOptionalInt(ps, 3, c.getIdRam());
            setOptionalInt(ps, 4, c.getIdGpu());
            setOptionalInt(ps, 5, c.getIdPsu());
            setOptionalInt(ps, 6, c.getIdCase());
            setOptionalInt(ps, 7, c.getIdCooler());
            ps.setInt(8, c.getIdBuild());
            
            ps.executeUpdate();
            
            return eseguiVerificaEnergia(c.getIdBuild());

        } catch (SQLException e) {
            if (e.getErrorCode() >= 20000) return "ERRORE: " + e.getMessage();
            e.printStackTrace();
            return "Errore durante la modifica.";
        }
    }
    
    //Ricerca Composizione per build
    public Composizione doRetrieveByBuild(int idBuild) {
        Composizione comp = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM COMPOSIZIONI WHERE id_build = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, idBuild);
            rs = ps.executeQuery();

            if (rs.next()) {
                comp = new Composizione(
                    rs.getInt("id_build"),
                    rs.getInt("id_cpu"),
                    rs.getInt("id_mb"),
                    rs.getInt("id_ram"),
                    rs.getInt("id_gpu"),
                    rs.getInt("id_psu"),
                    rs.getInt("id_case"),
                    rs.getInt("id_diss")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comp;
    }
}