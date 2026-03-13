package ClassiDiModello.Componente;

public class Motherboard {
    private int idMb;
    private String marca;
    private String modello;
    private int tdp;
    private String chipset;
    private String formFactor;
    private int maxRam;
    private String socket;
    private int slotDimm;
    private String amministratore;

    public Motherboard(int idMb, String marca, String modello, int tdp, String chipset, 
        String formFactor, int maxRam, String socket, int slotDimm, String amministratore) {
        this.idMb = idMb;
        this.marca = marca;
        this.modello = modello;
        this.tdp = tdp;
        this.chipset = chipset;
        this.formFactor = formFactor;
        this.maxRam = maxRam;
        this.socket = socket;
        this.slotDimm = slotDimm;
        this.amministratore = amministratore;
    }

    public int getIdMb() { return idMb; }
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public int getTdp() { return tdp; }
    public String getChipset() { return chipset; }
    public String getFormFactor() { return formFactor; }
    public int getMaxRam() { return maxRam; }
    public String getSocket() { return socket; }
    public int getSlotDimm() { return slotDimm; }
    public String getAmministratore() { return amministratore; }
}
