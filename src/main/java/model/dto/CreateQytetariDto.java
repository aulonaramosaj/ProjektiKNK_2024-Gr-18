package model.dto;

import java.time.LocalDate;

public class CreateQytetariDto {
    public String getDitelindja;
    private String NrPersonal;
    private String Emri;
    private String Mbiemri;
    private String Gjinia;
    private LocalDate Ditelindja;
    private String Adresa;
    private String NrTel;
    private String Email;

    public CreateQytetariDto(String nrPersonal, String emri, String mbiemri, String gjinia, LocalDate ditelindja, String adresa, String nrTel, String email) {
        NrPersonal = nrPersonal;
        Emri = emri;
        Mbiemri = mbiemri;
        Gjinia = gjinia;
        Ditelindja = ditelindja;
        Adresa = adresa;
        NrTel = nrTel;
        Email = email;
    }

    public CreateQytetariDto(String emri, String mbiemri, String nrPersonal, String email, String nrTelefonit, String ditelindja, int gjinia, String adresa, String salt, String passwordHash) {
    }

    public CreateQytetariDto(String text, String text1, String text2, String ditelindjaStr, String text3, String text4, String gjinia, int i) {
    }

    public CreateQytetariDto(String nrPersonal, String emri, String mbiemri, String text, LocalDate ditelindja, String adresa, int nrTelefonit, String email) {
    }

    public String getNrPersonal() {
        return NrPersonal;
    }

    public String getEmri() {
        return Emri;
    }

    public String getMbiemri() {
        return Mbiemri;
    }

    public String getGjinia() {
        return Gjinia;
    }

    public LocalDate getDitelindja() {
        return Ditelindja;
    }

    public String getAdresa() {
        return Adresa;
    }

    public String getNrTel() {
        return NrTel;
    }

    public String getEmail() {
        return Email;
    }
}
