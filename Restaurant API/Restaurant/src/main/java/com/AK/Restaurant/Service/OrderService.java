package com.AK.Restaurant.Service;

import com.AK.Restaurant.Model.Food;
import com.AK.Restaurant.Model.Order;
import com.AK.Restaurant.Model.User;
import com.AK.Restaurant.Repo.IOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    IOrderRepo iOrderRepo;
    @Autowired
    UserService userService;
    @Autowired
    FoodService foodService;
    public boolean addOrder(Order order, String email) {
        Food food = order.getFoodItem();

        boolean foodAvail = foodService.isFoodInTheMenu(food);
        User user = userService.findFirstByUserEmail(email);
        if(foodAvail)
        {
            order.setUser(user);
            iOrderRepo.save(order);
            return true;
        }
        else
            return false;


    }

}
