package com.AK.Restaurant.Repo;

import com.AK.Restaurant.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepo extends JpaRepository<Order,Integer> {
}
