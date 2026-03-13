package ClassiDiModello.Componente;

public class Alimentatore {
    private int idPsu;
    private String marca;
    private String modello;
    private int tdp;
    private int potenzaNominale;
    private String certificazione;
    private String amministratore;

    public Alimentatore(int idPsu, String marca, String modello, int tdp, 
        int potenzaNominale, String certificazione, String amministratore) {
        this.idPsu = idPsu;
        this.marca = marca;
        this.modello = modello;
        this.tdp = tdp;
        this.potenzaNominale = potenzaNominale;
        this.certificazione = certificazione;
        this.amministratore = amministratore;
    }

    public int getIdPsu() { return idPsu; }
    public String getMarca() { return marca; }
    public String getModello() { return modello; }
    public int getTdp() { return tdp; }
    public int getPotenzaNominale() { return potenzaNominale; }
    public String getCertificazione() { return certificazione; }
    public String getAmministratore() { return amministratore; }
}