package com.AK.Restaurant.Service;

import com.AK.Restaurant.Model.Admin;
import com.AK.Restaurant.Model.DTO.SignUp;
import com.AK.Restaurant.Repo.IAdminRepo;
import com.AK.Restaurant.Service.Password.PasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    IAdminRepo iAdminRepo;
    public SignUp addAdmin(Admin admin) {
        String email = admin.getAdminEmail();

        String signUpStatusMessage = "";
        if(email==null)
        {
            signUpStatusMessage = "Enter a Valid Email";
            return new SignUp(false,signUpStatusMessage);
        }

        Admin existingAdmin = iAdminRepo.findFirstByAdminEmail(email);

        if(existingAdmin!=null)
        {
            signUpStatusMessage = "Email Already Registered";
            return  new SignUp(false ,signUpStatusMessage );
        }

        try {
            String encryptPass = PasswordEncryptor.encrypt(admin.getAdminPassword());
            admin.setAdminPassword(encryptPass);
            iAdminRepo.save(admin);

            signUpStatusMessage = "New Admin Registered";

            return new SignUp(true,signUpStatusMessage);
        }
        catch (Exception e)
        {
            signUpStatusMessage = "Internal error occurred ";

            return new SignUp(false,signUpStatusMessage);
        }

    }

    public boolean ifAdminExistOrNot(String adminEMail) {
        Admin existingAdmin = iAdminRepo.findFirstByAdminEmail(adminEMail);

        return existingAdmin!=null;
    }

}
