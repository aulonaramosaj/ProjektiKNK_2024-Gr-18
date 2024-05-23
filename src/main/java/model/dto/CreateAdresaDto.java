package model.dto;

public class CreateAdresaDto {

    private String Komuna;
    private String Fshati;
    private String Rruga;
    private int NumriNderteses;
    private int KodiPostar;
    private String LlojiVendbanimit;
    private int UserId;


    public CreateAdresaDto(String komuna, String fshati, String rruga, int numriNderteses, int kodiPostar, String llojiVendbanimit, int userId) {
        Komuna = komuna;
        Fshati = fshati;
        Rruga = rruga;
        NumriNderteses = numriNderteses;
        KodiPostar = kodiPostar;
        LlojiVendbanimit = llojiVendbanimit;
        UserId = userId;
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

    public String getLlojiVendbanimit() {return LlojiVendbanimit;}

    public int getUserId() {return UserId;}
}
