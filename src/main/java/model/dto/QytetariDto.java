package model.dto;

import javafx.scene.control.RadioButton;

import java.time.LocalDate;
import java.sql.Date;

public class QytetariDto {

    private String NrPersonal;
    private String Emri;
    private String Mbiemri;
    private String Gjinia;
    private Date Ditelindja;
    private int Adresa;
    private String NrTelefonit;
    private String Email;
    private int UserId;

    public QytetariDto(String nrPersonal, String emri, String mbiemri, String gjinia, Date ditelindja, int adresa, String nrTelefonit, String email, int userId) {

        NrPersonal = nrPersonal;
        Emri = emri;
        Mbiemri = mbiemri;
        Gjinia = gjinia;
        Ditelindja = ditelindja;
        Adresa = adresa;
        NrTelefonit = nrTelefonit;
        Email = email;
        UserId=userId;
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

    public String getNrTelefonit() {
        return NrTelefonit;
    }

    public String getEmail() {
        return Email;
    }
    public int getUserId(){return UserId;}
}