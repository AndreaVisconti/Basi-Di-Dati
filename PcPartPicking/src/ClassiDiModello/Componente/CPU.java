package ClassiDiModello.Componente;

public class CPU {
    private int idCpu;
    private String marca;
    private String modello;
    private int tdp;
    private String socket;
    private int core;
    private double frequenza;
    private String amministratore;

    public CPU(int idCpu, String marca, String modello, int tdp, 
        String socket, int core, double frequenza, String amministratore) {
        this.idCpu = idCpu;
        this.marca = marca;
        this.modello = modello;
        this.tdp = tdp;
        this.socket = socket;
        this.core = core;
        this.frequenza = frequenza;
        this.amministratore = amministratore;
    }

    public int getIdCpu() { return idCpu; }
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public int getTdp() { return tdp; }
    public String getSocket() { return socket; }
    public int getCore() { return core; }
    public double getFrequenza() { return frequenza; }
    public String getAmministratore() { return amministratore; }
}
