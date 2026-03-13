package ClassiDiModello.Componente;

public class Dissipatore {
    private int idCooler;
    private String marca;
    private String modello;
    private int tdp;
    private String socket;
    private String tipo;
    private int dimensione;
    private String amministratore;

    public Dissipatore(int idCooler, String marca, String modello, int tdp, 
        String socket, String tipo, int dimensione, String amministratore) {
        this.idCooler = idCooler;
        this.marca = marca;
        this.modello = modello;
        this.tdp = tdp;
        this.socket = socket;
        this.tipo = tipo;
        this.dimensione = dimensione;
        this.amministratore = amministratore;
    }

    public int getIdCooler() { return idCooler; }
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public int getTdp() { return tdp; }
    public String getSocket() { return socket; }
    public String getTipo() { return tipo; }
    public int getDimensione() { return dimensione; }
    public String getAmministratore() { return amministratore; }
}
