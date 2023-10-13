package com.AK.Restaurant.Service;

import com.AK.Restaurant.Model.AuthenticationToken;
import com.AK.Restaurant.Model.DTO.SignIn;
import com.AK.Restaurant.Model.DTO.SignUp;
import com.AK.Restaurant.Model.User;
import com.AK.Restaurant.Repo.IAuthenticationTokenRepo;
import com.AK.Restaurant.Repo.IUserRepo;
import com.AK.Restaurant.Service.Password.PasswordEncryptor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AuthenticationService {

    @Autowired
    IAuthenticationTokenRepo iAuthenticationRepo;

    public void saveAuthToken(AuthenticationToken auth) {
        iAuthenticationRepo.save(auth);
    }

    public boolean authenticate(String email, String authTokenValue)
    {
        AuthenticationToken authToken = iAuthenticationRepo.findFirstByTokenValue(authTokenValue);

        if(authToken == null)
        {
            return false;
        }

        String tokenConnectedEmail = authToken.getUser().getUserEmail();

        return tokenConnectedEmail.equals(email);
    }
}
