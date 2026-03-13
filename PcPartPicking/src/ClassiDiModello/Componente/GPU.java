package ClassiDiModello.Componente;

public class GPU {
    private int idGpu;
    private String marca;
    private String modello;
    private int tdp;
    private int vram;
    private String amministratore;

    public GPU(int idGpu, String marca, String modello, int tdp, int vram, String amministratore) {
        this.idGpu = idGpu;
        this.marca = marca;
        this.modello = modello;
        this.tdp = tdp;
        this.vram = vram;
        this.amministratore = amministratore;
    }

    public int getIdGpu() { return idGpu; }
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public int getTdp() { return tdp; }
    public int getVram() { return vram; }
    public String getAmministratore() { return amministratore; }
}
