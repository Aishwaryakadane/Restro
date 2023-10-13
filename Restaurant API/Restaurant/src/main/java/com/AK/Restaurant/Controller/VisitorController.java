package com.AK.Restaurant.Controller;

import com.AK.Restaurant.Model.Food;
import com.AK.Restaurant.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VisitorController {

    @Autowired
    FoodService foodService;

    @GetMapping("food")
    public List<Food> getAllFoodItems()
    {
        return foodService.getAllFoodItems();
    }

}
