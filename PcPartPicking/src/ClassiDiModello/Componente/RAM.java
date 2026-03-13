package ClassiDiModello.Componente;

public class RAM {
    private int id_ram;
    private String marca;
    private String modello;
    private int tdp;
    private int capacità;
    private String tipo;
    private int frequenza;
    private String amministratore;

    public RAM(int idRam, String marca, String modello, int tdp, int capacita, 
        String tipo, int frequenza, String amministratore) {
        this.id_ram = idRam;
        this.marca = marca;
        this.modello = modello;
        this.tdp = tdp;
        this.capacità = capacita;
        this.tipo = tipo;
        this.frequenza = frequenza;
        this.amministratore = amministratore;
    }

    public int getIdRam() { return id_ram; }
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public int getTdp() { return tdp; }
    public int getCapacita() { return capacità; }
    public String getTipo() { return tipo; }
    public int getFrequenza() { return frequenza; }
    public String getAmministratore() { return amministratore; }
}
