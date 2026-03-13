package Interfaccia;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import Connection.DBConnection;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> ruoloComboBox;
    private JButton loginButton;
    private JLabel statusLabel;


    public LoginFrame() {
        // Impostazioni della finestra
        setTitle("PCPartPicking - Login");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Inizializzazione dei componenti
        initComponents();
    }

    private void initComponents() {
        // Pannello principale con BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Titolo superiore
        JLabel titleLabel = new JLabel("Benvenuto in PCPartPicking", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Pannello centrale per il form con GridBagLayout
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Selezione Ruolo
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Accedi come:"), gbc);
        ruoloComboBox = new JComboBox<>(new String[] {"Assemblatore", "Amministratore"});
        gbc.gridx = 1;
        formPanel.add(ruoloComboBox, gbc);

        // Username
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Username:"), gbc);
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        formPanel.add(usernameField, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Password:"), gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        formPanel.add(passwordField, gbc);

        mainPanel.add(formPanel, BorderLayout.CENTER);

        // Pannello inferiore per bottone e messaggi di stato
        JPanel bottomPanel = new JPanel(new BorderLayout(5, 5));
        loginButton = new JButton("Accedi");
        
        // Aggiunta dell'ActionListener tramite classe interna
        loginButton.addActionListener(new LoginActionListener());
        bottomPanel.add(loginButton, BorderLayout.NORTH);
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    
    //Classe interna per la gestione del click sul pulsante Accedi
    private class LoginActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String ruolo = (String) ruoloComboBox.getSelectedItem();

            // Validazione campi vuoti
            if (username.isEmpty() || password.isEmpty()) {
                statusLabel.setText("Inserire username e password");
                return;
            }

            boolean autenticato = false;
            // Verifica in base al ruolo selezionato
            if ("Amministratore".equals(ruolo)) {
                autenticato = verificaLoginAmministratore(username, password);
            } else {
                autenticato = verificaLoginAssemblatore(username, password);
            }

            if (autenticato) {
                statusLabel.setForeground(new Color(0, 150, 0)); // Verde
                statusLabel.setText("Accesso effettuato!");
                JOptionPane.showMessageDialog(LoginFrame.this,
                    "Benvenuto " + username + "!\nAccesso come " + ruolo,
                    "Login riuscito", JOptionPane.INFORMATION_MESSAGE);

                //Link a BuildFrame
                dispose();
                    if ("Assemblatore".equals(ruolo)) {
                    // Apriamo la creazione build passando lo username
                        new BuildFrame(username).setVisible(true);
                    }

            } else {
                statusLabel.setForeground(Color.RED);
                statusLabel.setText("Credenziali non valide");
            }
        }
    }

    private boolean verificaLoginAmministratore(String username, String password) {
        String sql = "SELECT password FROM AMMINISTRATORI WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return password.equals(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Errore connessione DB", "Errore", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    private boolean verificaLoginAssemblatore(String username, String password) {
        String sql = "SELECT password FROM ASSEMBLATORI WHERE username = ?";
        try (Connection conn = DBConnection.getConnection();
            PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return password.equals(rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}