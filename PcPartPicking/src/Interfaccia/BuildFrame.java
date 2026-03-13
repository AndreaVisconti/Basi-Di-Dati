package Interfaccia;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import Connection.DBConnection;
import ClassiDAO.Build.ComposizioneDAO;
import ClassiDiModello.Build.Composizione;

public class BuildFrame extends JFrame {
    // Campi di testo e menu
    JTextField txtNome = new JTextField(15);
    JComboBox<String> cbCpu = new JComboBox<>(), cbGpu = new JComboBox<>(), cbMb = new JComboBox<>(), cbRam = new JComboBox<>(), 
        cbPsu = new JComboBox<>(), cbCase = new JComboBox<>(), cbDiss = new JComboBox<>();
    
    String userLoggato; // Memorizziamo chi sta usando l'app

    public BuildFrame(String username) {
        this.userLoggato = username;
        setTitle("Nuova Build");
        setSize(400, 500);
        setLayout(new GridLayout(10, 2)); // Una griglia da 10 righe e 2 colonne

        // Aggiungiamo i pezzi alla finestra
        add(new JLabel("Nome Build:")); add(txtNome);
        add(new JLabel("Processore:")); add(cbCpu);
        add(new JLabel("Scheda Video:")); add(cbGpu);
        add(new JLabel("Scheda Madre:")); add(cbMb);
        add(new JLabel("Memoria RAM:")); add(cbRam);
        add(new JLabel("Alimentatore:")); add(cbPsu);
        add(new JLabel("Case:")); add(cbCase);
        add(new JLabel("Dissipatore:")); add(cbDiss);

        JButton btnSalva = new JButton("SALVA");
        add(new JLabel("")); add(btnSalva);

        // Carichiamo i nomi dei componenti dal Database
        caricaDati();

        // Cosa succede quando clicco Salva
        btnSalva.addActionListener(e -> salva());

        setVisible(true);
    }

    private void caricaDati() {
        try (Connection conn = DBConnection.getConnection()) {
            riempiCombo(conn, "SELECT marca, modello FROM CPU", cbCpu);
            riempiCombo(conn, "SELECT marca, modello FROM GPU", cbGpu);
            riempiCombo(conn, "SELECT marca, modello FROM MOTHERBOARD", cbMb);
            riempiCombo(conn, "SELECT marca, modello FROM RAM", cbRam);
            riempiCombo(conn, "SELECT marca, modello FROM ALIMENTATORI", cbPsu);
            riempiCombo(conn, "SELECT marca, modello FROM CASE_PC", cbCase);
            riempiCombo(conn, "SELECT marca, modello FROM DISSIPATORI", cbDiss);
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void riempiCombo(Connection conn, String sql, JComboBox<String> combo) throws SQLException {
        ResultSet rs = conn.createStatement().executeQuery(sql);
        combo.addItem("-- Seleziona --");
        while (rs.next()) {
            combo.addItem(rs.getString(1) + " - " + rs.getString(2));
        }
    }

    private void salva() {
        try (Connection conn = DBConnection.getConnection()) {
            // Creiamo la Build nella tabella BUILDS
            String sqlB = "INSERT INTO BUILDS (id_build, nome, assemblatore) VALUES (seq_builds.NEXTVAL, ?, ?)";
            PreparedStatement psB = conn.prepareStatement(sqlB, new String[]{"ID_BUILD"});
            psB.setString(1, txtNome.getText());
            psB.setString(2, userLoggato);
            psB.executeUpdate();
            
            ResultSet rs = psB.getGeneratedKeys();
            if (rs.next()) {
                int idB = rs.getInt(1);

                //Troviamo gli ID dei pezzi scelti
                int idCpu = trovaId(conn, "CPU", "id_cpu", (String)cbCpu.getSelectedItem());
                int idGpu = trovaId(conn, "GPU", "id_gpu", (String)cbGpu.getSelectedItem());
                int idMb = trovaId(conn, "MOTHERBOARD", "id_mb", (String)cbMb.getSelectedItem());
                int idRam = trovaId(conn, "RAM", "id_ram", (String)cbRam.getSelectedItem());
                int idPsu = trovaId(conn, "ALIMENTATORI", "id_psu", (String)cbPsu.getSelectedItem());
                int idCase = trovaId(conn, "CASE_PC", "id_case", (String)cbCase.getSelectedItem());
                int idDiss = trovaId(conn, "DISSIPATORI", "id_diss", (String)cbDiss.getSelectedItem());

                //Usiamo il DAO per salvare e controllare la PSU
                Composizione c = new Composizione(idB, idCpu, idMb, idRam, idGpu, idPsu, idCase, idDiss);
                String msg = new ComposizioneDAO().doSave(c);

                if (msg != null) JOptionPane.showMessageDialog(this, msg);
                else JOptionPane.showMessageDialog(this, "Controllo energetico andato a buon fine. Build Salvata!");
            }
        } catch (Exception ex) { ex.printStackTrace(); }
    }

    private int trovaId(Connection conn, String tab, String col, String scelta) throws SQLException {
        if (scelta.equals("-- Seleziona --")) return 0;
        String[] parti = scelta.split(" - "); // Divide Marca e Modello
        String marca = parti[0];
        String modello = parti[1];
        
        String sql = "SELECT " + col + " FROM " + tab + " WHERE marca = ? AND modello = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, marca);
        ps.setString(2, modello);
        
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt(1); // Restituisce l'ID corretto
        }
        return 0;
    }
}