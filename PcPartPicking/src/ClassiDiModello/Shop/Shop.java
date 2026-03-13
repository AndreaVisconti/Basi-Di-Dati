package ClassiDiModello.Shop;

public class Shop {
    private String partitaIva;
    private String nome;
    private String urlSitoWeb;

    public Shop(String partitaIva, String nome, String urlSitoWeb) {
        this.partitaIva = partitaIva;
        this.nome = nome;
        this.urlSitoWeb = urlSitoWeb;
    }

    public String getPartitaIva() { return partitaIva; }
    public String getNome() { return nome; }
    public String getUrlSitoWeb() { return urlSitoWeb; }
}
