package com.AK.Restaurant.Model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignIn {

    private String email;
    private String password;
}
