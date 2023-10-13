package com.AK.Restaurant.Controller;

import com.AK.Restaurant.Model.Admin;
import com.AK.Restaurant.Model.DTO.SignUp;
import com.AK.Restaurant.Model.Food;
import com.AK.Restaurant.Service.AdminService;
import com.AK.Restaurant.Service.FoodService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    FoodService foodService;
    @PostMapping("Admin/signUp")
    public SignUp addAdmin(@RequestBody @Valid Admin admin)
    {
        return adminService.addAdmin(admin);
    }

    @PostMapping("food")
    public String addFoodItem(@RequestBody @Valid Food food , @RequestParam String adminEMail)
    {
        boolean adminOrNot = adminService.ifAdminExistOrNot(adminEMail);

        if(adminOrNot)
        {
            foodService.addFoodItem(food);
            return "New Food Item added to the menu";
        }
        else return  "Unauthorized access ";

    }
}
