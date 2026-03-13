package ClassiDiModello.Build;

public class Composizione {
    private int idBuild;
    private int idCpu;
    private int idMb;
    private int idRam;
    private int idGpu;
    private int idPsu;
    private int idCase;
    private int idCooler;

    public Composizione(int idBuild, int idCpu, int idMb, int idRam, 
        int idGpu, int idPsu, int idCase, int idCooler) {
        this.idBuild = idBuild;
        this.idCpu = idCpu;
        this.idMb = idMb;
        this.idRam = idRam;
        this.idGpu = idGpu;
        this.idPsu = idPsu;
        this.idCase = idCase;
        this.idCooler = idCooler;
    }

    public int getIdBuild() { return idBuild; }
    public int getIdCpu() { return idCpu; }
    public int getIdMb() { return idMb; }
    public int getIdRam() { return idRam; }
    public int getIdGpu() { return idGpu; }
    public int getIdPsu() { return idPsu; }
    public int getIdCase() { return idCase; }
    public int getIdCooler() { return idCooler; }
}
