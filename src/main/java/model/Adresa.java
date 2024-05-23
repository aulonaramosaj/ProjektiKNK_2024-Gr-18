package model;

public class Adresa {
    private int id;
    private String komuna;
    private String fshati;
    private String rruga;
    private int numriNderteses;
    private int kodiPostar;
    private String llojiVendbanimit;

    public Adresa(int id, String komuna, String fshati, String rruga, int numriNderteses, int kodiPostar, String llojiVendbanimit) {
        this.id = id;
        this.komuna = komuna;
        this.fshati = fshati;
        this.rruga = rruga;
        this.numriNderteses = numriNderteses;
        this.kodiPostar = kodiPostar;
        this.llojiVendbanimit = llojiVendbanimit;
    }


    public int getId() {
        return id;
    }

    public String getKomuna() {
        return komuna;
    }

    public String getFshati() {
        return fshati;
    }

    public String getRruga() {
        return rruga;
    }

    public int getNumriNderteses() {
        return numriNderteses;
    }

    public int getKodiPostar() {
        return kodiPostar;
    }

    public String getLlojiVendbanimit() {
        return llojiVendbanimit;
    }


    public void setId(int id) {
        this.id = id;
    }


}
