package model.dto;

public class CreateQytetariDto {
    private String NrPersonal;
    private String Emri;
    private String Mbiemri;
    private String Gjinia;
    private String Ditelindja;
    private String Adresa;
    private int NrTelefonit;
    private String Email;

    public CreateQytetariDto(String nrPersonal, String emri, String mbiemri, String gjinia, String ditelindja, String adresa, int nrTelefonit, String email) {
        NrPersonal = nrPersonal;
        Emri = emri;
        Mbiemri = mbiemri;
        Gjinia = gjinia;
        Ditelindja = ditelindja;
        Adresa = adresa;
        NrTelefonit = nrTelefonit;
        Email = email;
    }

    public CreateQytetariDto(String emri, String mbiemri, String nrPersonal, String email, String nrTelefonit, String ditelindja, int gjinia, String adresa, String salt, String passwordHash) {
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
