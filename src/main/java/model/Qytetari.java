package model;

import java.time.LocalDate;

public class Qytetari {
    private String NrPersonal;
    private String Mbiemri;
    private LocalDate Ditelindja;
    private String Email;
    private String NrTel;
    private String Gjinia;

    public Qytetari(String nrPersonal, String mbiemri, LocalDate ditelindja, String email, String nrTel, String gjinia) {
        NrPersonal = nrPersonal;
        Mbiemri = mbiemri;
        Ditelindja = ditelindja;
        Email = email;
        NrTel = nrTel;
        Gjinia = gjinia;
    }

    public String getNrPersonal() {
        return NrPersonal;
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
