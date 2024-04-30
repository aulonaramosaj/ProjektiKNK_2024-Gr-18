package service;

import model.User;

import model.dto.CreateUserDto;
import model.dto.UserDto;
import repository.UserRepository;

public class UserService {
    public static boolean signUp(UserDto userData){
        String password = userData.getFjalekalimi();
        String confirmPassword = userData.getKonfirmoFjalekalimin();

        if(!password.equals(confirmPassword)){
            return false;
        }

        String Salt = PasswordHasher.generateSalt();
        String passwordHash = PasswordHasher.generateSaltedHash(
                password, Salt
        );

        CreateUserDto createUserData = new CreateUserDto(
                userData.getEmri(),
                userData.getMbiemri(),
                userData.getNrPersonal(),
                userData.getUsername(),
                userData.getEmail(),
                Salt,
                passwordHash
        );

        return UserRepository.create(createUserData);
    }

    //public static boolean login(LoginUserDto loginData){
        //User user = UserRepository.getByEmail(loginData.getEmail());
        //if(user == null){
          //  return false;
        //}

        //String password = loginData.getPassword();
        //String salt = user.getSalt();
        //String passwordHash = user.getPasswordHash();

        //return PasswordHasher.compareSaltedHash(
                //password, salt, passwordHash
        //);
    //}

}