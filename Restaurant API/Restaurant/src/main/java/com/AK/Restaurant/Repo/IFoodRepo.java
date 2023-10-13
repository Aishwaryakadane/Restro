package com.AK.Restaurant.Repo;

import com.AK.Restaurant.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFoodRepo extends JpaRepository<Food,Integer> {
}
