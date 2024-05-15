package model.dto;

public class LoginUserDto {
    private String Email;
    private String Password;

    public LoginUserDto(String email, String password) {
        Email = email;
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }
}