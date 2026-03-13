package ClassiDiModello.Utente;

public class Amministratore {
    private String username;
    private String password;
    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private byte[] foto; // Mappatura per BLOB
    private String idAziendale;

    public Amministratore(String username, String password, String nome, String cognome, 
        String email, String telefono, byte[] foto, String idAziendale) {
        this.username = username;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.telefono = telefono;
        this.foto = foto;
        this.idAziendale = idAziendale;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getNome() { return nome; }
    public String getCognome() { return cognome; }
    public String getEmail() { return email; }
    public String getTelefono() { return telefono; }
    public byte[] getFoto() { return foto; }
    public String getIdAziendale() { return idAziendale; }
}
