package model;

import java.time.LocalDate;

public class Qytetari {
    public int Adresa;
    public int Id;
    private String NrPersonal;
    public String Emri;
    private String Mbiemri;
    private LocalDate Ditelindja;
    private String Email;
    private String NrTel;
    private String Gjinia;

    public Qytetari(String nrPersonal,String emri, String mbiemri, LocalDate ditelindja, String email, String nrTel, String gjinia) {
        this.NrPersonal = nrPersonal;
        this.Mbiemri = mbiemri;
        this.Emri=emri;
        this.Ditelindja = ditelindja;
        this.Email = email;
        this.NrTel = nrTel;
        this.Gjinia = gjinia;
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

    public LocalDate getDitelindja() {
        return Ditelindja;
    }

    public String getEmail() {
        return Email;
    }

    public String getNrTel() {
        return NrTel;
    }

    public String getGjinia() {
        return Gjinia;
    }

    public Qytetari(int i, String nrPersonal, String emri, String mbiemri, String ditelindja, String email, String nrTel, String gjinia) {
    }
}
