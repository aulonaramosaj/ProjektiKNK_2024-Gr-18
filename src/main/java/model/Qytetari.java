package model;

public class Qytetari {
    private String NrPersonal;
    private String Mbiemri;
    private String Ditelidnja;
    private String Email;
    private String NrTelefonit;
    private String Gjinia;

    public Qytetari(String nrPersonal, String mbiemri, String ditelidnja, String email, String nrTelefonit, String gjinia) {
        NrPersonal = nrPersonal;
        Mbiemri = mbiemri;
        Ditelidnja = ditelidnja;
        Email = email;
        NrTelefonit = nrTelefonit;
        Gjinia = gjinia;
    }

    public String getNrPersonal() {
        return NrPersonal;
    }

    public String getMbiemri() {
        return Mbiemri;
    }

    public String getDitelidnja() {
        return Ditelidnja;
    }

    public String getEmail() {
        return Email;
    }

    public String getNrTelefonit() {
        return NrTelefonit;
    }

    public String getGjinia() {
        return Gjinia;
    }

    public Qytetari(int i, String nrPersonal, String emri, String mbiemri, String ditelindja, String email, String nrTel, String gjinia) {
    }
}
