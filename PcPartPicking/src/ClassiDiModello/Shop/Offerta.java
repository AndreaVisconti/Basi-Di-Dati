package ClassiDiModello.Shop;

public class Offerta {
    private String shop;
    private int idBuild;
    private double prezzo;
    private String linkProdotto;
    private int quantita;
    private char disponibilita;

    public Offerta(String shop, int idBuild, double prezzo, String linkProdotto, int quantita, char disponibilita) {
        this.shop = shop;
        this.idBuild = idBuild;
        this.prezzo = prezzo;
        this.linkProdotto = linkProdotto;
        this.quantita = quantita;
        this.disponibilita = disponibilita;
    }

    public String getShop() { return shop; }
    public int getIdBuild() { return idBuild; }
    public double getPrezzo() { return prezzo; }
    public String getLinkProdotto() { return linkProdotto; }
    public int getQuantita() { return quantita; }
    public char getDisponibilita() { return disponibilita; }
}
