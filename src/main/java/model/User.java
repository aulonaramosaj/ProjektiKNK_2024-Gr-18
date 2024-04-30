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

    public User(int id, String emri, String mbiemri, String nrPersonal, String username, String email, String salt, String passwordHash) {
        Id = id;
        Emri = emri;
        Mbiemri = mbiemri;
        NrPersonal = nrPersonal;
        Username = username;
        Email = email;
        Salt = salt;
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
