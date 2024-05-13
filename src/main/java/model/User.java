package model;

public class User {
    private int Id;
    private String Emri;
    private String Mbiemri;
    private String NrPersonal;
    private String Username;
    private String Email;
    private String Salt;
    private String passwordHash;

    public User(int Id, String Emri, String Mbiemri, String NrPersonal, String Username, String Email, String Salt, String passwordHash) {
        this.Id = Id;
        this.Emri = Emri;
        this.Mbiemri = Mbiemri;
        this.NrPersonal = NrPersonal;
        this.Username = Username;
        this.Email = Email;
        this.Salt = Salt;
        this.passwordHash = passwordHash;
    }

    public int getId() {
        return Id;
    }

    public String getEmri() {
        return Emri;
    }

    public String getMbiemri() {
        return Mbiemri;
    }

    public String getNrPersonal() {
        return NrPersonal;
    }

    public String getUsername() {
        return Username;
    }

    public String getEmail() {
        return Email;
    }

    public String getSalt() {
        return Salt;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
