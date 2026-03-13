package ClassiDiModello.Feedback;

public class Feedback {
    private String assemblatore;
    private int idBuild;
    private int voto;
    private String giudizio;

    public Feedback(String assemblatore, int build, int voto, String giudizio) {
        this.assemblatore = assemblatore;
        this.idBuild = build;
        this.voto = voto;
        this.giudizio = giudizio;
    }

    public String getAssemblatore() { return assemblatore; }
    public int getBuild() { return idBuild; }
    public int getVoto() { return voto; }
    public String getGiudizio() { return giudizio; }
}
