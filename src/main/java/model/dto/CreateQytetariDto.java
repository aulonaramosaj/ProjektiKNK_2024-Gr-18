package model.dto;

import java.sql.Date;

public class CreateQytetariDto {

    private String NrPersonal;
    private String Emri;
    private String Mbiemri;
    private String Gjinia;
    private Date Ditelindja;
    private int Adresa;
    private String NrTel;
    private String Email;
    private int UserId;

    public CreateQytetariDto(String nrPersonal, String emri, String mbiemri, String gjinia, Date ditelindja, int adresa, String nrTel, String email, int userId) {
        NrPersonal = nrPersonal;
        Emri = emri;
        Mbiemri = mbiemri;
        Gjinia = gjinia;
        Ditelindja = ditelindja;
        Adresa = adresa;
        NrTel = nrTel;
        Email = email;
        UserId = userId;
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

    public Date getDitelindja() {
        return Ditelindja;
    }

    public int getAdresa() {
        return Adresa;
    }

    public String getNrTel() {
        return NrTel;
    }

    public String getEmail() {
        return Email;
    }

    public int getUserId() {
        return UserId;
    }

}