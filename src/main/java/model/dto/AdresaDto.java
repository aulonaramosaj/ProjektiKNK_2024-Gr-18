package model.dto;

public class AdresaDto {
    private int Id;
    private String Komuna;
    private String Fshati;
    private String Rruga;
    private int NumriNderteses;
    private int KodiPostar;
    private String LlojiVendbanimit;
    private int UserId;

    public AdresaDto(int id, String komuna, String fshati, String rruga, int numriNderteses, int kodiPostar, String llojiVendbanimit, int userId) {
        Id = id;
        Komuna = komuna;
        Fshati = fshati;
        Rruga = rruga;
        NumriNderteses = numriNderteses;
        KodiPostar = kodiPostar;
        LlojiVendbanimit = llojiVendbanimit;
        UserId = userId;
    }

    public int getId() {
        return Id;
    }

    public String getKomuna() {
        return Komuna;
    }

    public String getFshati() {
        return Fshati;
    }

    public String getRruga() {
        return Rruga;
    }

    public int getNumriNderteses() {
        return NumriNderteses;
    }

    public int getKodiPostar() {
        return KodiPostar;
    }

    public String getLlojiVendbanimit() {
        return LlojiVendbanimit;
    }

    public int getUserId() {
        return UserId;
    }


}
