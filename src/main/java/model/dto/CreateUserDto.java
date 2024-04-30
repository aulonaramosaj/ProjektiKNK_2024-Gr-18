package model.dto;

public class CreateUserDto {
    private String Emri;
    private String Mbiemri;
    private String NrPersonal;
    private String Username;
    private String Email;
    private String Salt;
    private String passwordHash;

    public CreateUserDto(String emri, String mbiemri, String nrPersonal, String Username, String email, String salt, String passwordHash) {
        Emri = emri;
        Mbiemri = mbiemri;
        NrPersonal = nrPersonal;
        Username = Username;
        Email = email;
        Salt = salt;
        this.passwordHash = passwordHash;
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