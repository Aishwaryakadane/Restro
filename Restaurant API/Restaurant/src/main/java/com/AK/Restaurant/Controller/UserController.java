package com.AK.Restaurant.Controller;

import com.AK.Restaurant.Model.DTO.SignIn;
import com.AK.Restaurant.Model.DTO.SignUp;
import com.AK.Restaurant.Model.Food;
import com.AK.Restaurant.Model.Order;
import com.AK.Restaurant.Model.User;
import com.AK.Restaurant.Service.AuthenticationService;
import com.AK.Restaurant.Service.FoodService;
import com.AK.Restaurant.Service.OrderService;
import com.AK.Restaurant.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    OrderService orderService;

    @Autowired
    FoodService foodService;

    @PostMapping("user/SignUp")
    public SignUp signUp(@RequestBody User user) {
        return userService.signUp(user);
    }

    @PostMapping("user/SignIn")
    public String signIn(@RequestBody SignIn sign) {
        return userService.signIn(sign);
    }

    @PostMapping("order")
    public String addOrder(@RequestBody Order order, @RequestParam String email, @RequestParam String token) {
        if (authenticationService.authenticate(email, token)) {
            boolean foodOrdered = orderService.addOrder(order, email);

            return foodOrdered ? "Food ordered" : "The food you are trying to order is Not available in the menu";

        } else {
            return "Sign in to order Food";
        }
    }


    @GetMapping("foods")
    public List<Food> getAllFoodItems()
    {
        return foodService.getAllFoodItems();
    }

}
