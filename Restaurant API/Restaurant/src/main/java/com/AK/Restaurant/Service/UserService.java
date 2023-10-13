package com.AK.Restaurant.Service;

import com.AK.Restaurant.Model.AuthenticationToken;
import com.AK.Restaurant.Model.DTO.SignIn;
import com.AK.Restaurant.Model.DTO.SignUp;
import com.AK.Restaurant.Model.User;
import com.AK.Restaurant.Repo.IUserRepo;
import com.AK.Restaurant.Service.Password.PasswordEncryptor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepo userRepo;

    @Autowired
    AuthenticationService authenticationService;

    public SignUp signUp(User user) {
        boolean signUpStatus = true;
        String signUpStatusMessage = "";

        String newEmail = user.getUserEmail();

        if(newEmail==null)
        {
            signUpStatus = false;
            signUpStatusMessage = "Please enter A valid Email";

            return new SignUp(signUpStatus,signUpStatusMessage);
        }

        User existingUser = userRepo.findFirstByUserEmail(newEmail);

        if(existingUser!=null)
        {
            signUpStatus = false;
            signUpStatusMessage = "Email already Registered";

            return new SignUp(signUpStatus,signUpStatusMessage);
        }
        try
        {
            String encryptedPassword = PasswordEncryptor.encrypt(user.getUserPassword());
            user.setUserPassword(encryptedPassword);
            userRepo.save(user);

            signUpStatusMessage = "New User Registered";
            return new SignUp(signUpStatus,signUpStatusMessage);
        }
        catch (Exception e){
            signUpStatusMessage = "Internal error occurred during sign up";
            signUpStatus = false;
            return new SignUp(signUpStatus,signUpStatusMessage);

        }

    }

    public String signIn(SignIn signInInput) {

        String userEmail = signInInput.getEmail();
        String signInStatusMessage = "";
        if(userEmail ==null)
        {
            signInStatusMessage = "Please Enter a Valid Email";
            return signInStatusMessage;
        }


        User existingUser = userRepo.findFirstByUserEmail(signInInput.getEmail());

        if(existingUser==null)
        {
            signInStatusMessage = "Email not registered";
            return signInStatusMessage;
        }
        //Matching Credentials
        try{
            String encryptedPass = PasswordEncryptor.encrypt(signInInput.getPassword());

            if(existingUser.getUserPassword().equals(encryptedPass))
            {
                AuthenticationToken auth = new AuthenticationToken(existingUser);
                authenticationService.saveAuthToken(auth);

                signInStatusMessage = "Signed In Successfully";
                return signInStatusMessage;
            }
            else
            {
                signInStatusMessage = "Invalid Credentials";
                return  signInStatusMessage;
            }
        }
        catch (Exception e)
        {
            signInStatusMessage = "Internal error occurred during Sign in";
            return  signInStatusMessage;
        }
    }


    public User findFirstByUserEmail(String email) {
        return userRepo.findFirstByUserEmail(email);
    }


}