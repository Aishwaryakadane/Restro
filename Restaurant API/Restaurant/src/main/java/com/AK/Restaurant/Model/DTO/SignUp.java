package com.AK.Restaurant.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUp {
    private boolean signUpStatus;
    private String signInStatusMessage;
}
