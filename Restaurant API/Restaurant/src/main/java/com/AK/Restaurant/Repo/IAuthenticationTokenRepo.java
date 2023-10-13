package com.AK.Restaurant.Repo;

import com.AK.Restaurant.Model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAuthenticationTokenRepo extends JpaRepository <AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);
}

