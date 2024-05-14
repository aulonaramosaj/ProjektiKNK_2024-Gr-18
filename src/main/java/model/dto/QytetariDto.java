package model.dto;

import javafx.scene.control.RadioButton;

public class QytetariDto {
   private String NrPersonal;
   private String Emri;
   private String Mbiemri;
   private RadioButton Gjinia;
   private String Ditelindja;
   private String Adresa;
   private int NrTelefonit;
   private String Email;

    public QytetariDto(String nrPersonal, String emri, String mbiemri, RadioButton gjinia, String ditelindja, String adresa, int nrTelefonit, String email) {
        NrPersonal = nrPersonal;
        Emri = emri;
        Mbiemri = mbiemri;
        Gjinia = gjinia;
        Ditelindja = ditelindja;
        Adresa = adresa;
        NrTelefonit = nrTelefonit;
        Email = email;
    }

    public QytetariDto(int i, String nrPersonal, String emri, String mbiemri, String ditelindja, String email, String nrTel, String gjinia, int adresa) {
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

    public RadioButton getGjinia() {
        return Gjinia;
    }

    public String getDitelindja() {
        return Ditelindja;
    }

    public String getAdresa() {
        return Adresa;
    }

    public int getNrTelefonit() {
        return NrTelefonit;
    }

    public String getEmail() {
        return Email;
    }
}
