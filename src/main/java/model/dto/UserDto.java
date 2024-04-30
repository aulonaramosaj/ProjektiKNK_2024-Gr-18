package model.dto;

public class UserDto {
    private String Emri;
    private String Mbiemri;
    private String NrPersonal;
    private String Username;

    private String Email;
    private String Fjalekalimi;
    private String KonfirmoFjalekalimin;

    public UserDto(String Emri, String Mbiemri, String NrPersonal, String Username, String Email,String Fjalekalimi, String KonfirmoFjalekalimin) {
        this.Emri = Emri;
        this.Mbiemri = Mbiemri;
        this.NrPersonal=NrPersonal;
        this.Username=Username;
        this.Email = Email;
        this.Fjalekalimi = Fjalekalimi;
        this.KonfirmoFjalekalimin = KonfirmoFjalekalimin;
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

    public String getFjalekalimi() {
        return Fjalekalimi;
    }

    public String getKonfirmoFjalekalimin() {
        return KonfirmoFjalekalimin;
    }
}