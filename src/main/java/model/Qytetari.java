package model;


import java.sql.Date;

public class Qytetari {
    public int Adresa;
    public int Id;
    private String NrPersonal;
    public String Emri;
    private String Mbiemri;
    private Date Ditelindja;
    private String Email;
    private String NrTel;
    private String Gjinia;
    private int User;

    public Qytetari(int id, int adresa, String nrPersonal, String emri, String mbiemri, Date ditelindja, String email, String nrTel, String gjinia,int User) {
        this.Id=id;
        this.Adresa=adresa;
        this.NrPersonal = nrPersonal;
        this.Emri=emri;
        this.Mbiemri = mbiemri;
        this.Ditelindja = ditelindja;
        this.Email = email;
        this.NrTel = nrTel;
        this.Gjinia = gjinia;
        this.User=User;
    }

    public int getId(){ return Id;}
    public String getNrPersonal() {
        return NrPersonal;
    }
    public String getEmri() {
        return Emri;
    }

    public int getAdresa(){return Adresa;}

    public String getMbiemri() {
        return Mbiemri;
    }

    public Date getDitelindja() {
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
    public int getUser(){return User;}


}