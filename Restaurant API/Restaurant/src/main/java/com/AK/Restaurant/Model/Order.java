package com.AK.Restaurant.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;
    private String quality;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "fkey_food_Id")
    private Food foodItem;


    @ManyToOne
    @JoinColumn(name = "fkey_user_Id")
    private User user;


}
