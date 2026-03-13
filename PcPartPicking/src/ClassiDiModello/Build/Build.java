package ClassiDiModello.Build;
import java.sql.Date;

public class Build {
    private int idBuild;
    private String nome;
    private Date dataCreazione;
    private String note;
    private String assemblatore;

    public Build(int idBuild, String nome, Date dataCreazione, String note, String assemblatore) {
        this.idBuild = idBuild;
        this.nome = nome;
        this.dataCreazione = dataCreazione;
        this.note = note;
        this.assemblatore = assemblatore;
    }

    public int getIdBuild() { return idBuild; }
    public String getNome() { return nome; }
    public Date getDataCreazione() { return dataCreazione; }
    public String getNote() { return note; }
    public String getAssemblatore() { return assemblatore; }
}
