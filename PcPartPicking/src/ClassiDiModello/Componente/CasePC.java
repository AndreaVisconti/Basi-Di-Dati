package ClassiDiModello.Componente;

public class CasePC {
    private int idCase;
    private String marca;
    private String modello;
    private int tdp;
    private String formFactor;
    private String supportoRadiatore;
    private String amministratore;

    public CasePC(int idCase, String marca, String modello, int tdp, 
        String formFactor, String supportoRadiatore, String amministratore) {
        this.idCase = idCase;
        this.marca = marca;
        this.modello = modello;
        this.tdp = tdp;
        this.formFactor = formFactor;
        this.supportoRadiatore = supportoRadiatore;
        this.amministratore = amministratore;
    }

    public int getIdCase() { return idCase; }
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public int getTdp() { return tdp; }
    public String getFormFactor() { return formFactor; }
    public String getSupportoRadiatore() { return supportoRadiatore; }
    public String getAmministratore() { return amministratore; }
}