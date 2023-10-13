package com.AK.Restaurant.Repo;

import com.AK.Restaurant.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAdminRepo extends JpaRepository<Admin,Integer> {
    Admin findFirstByAdminEmail(String adminEMail);
}

