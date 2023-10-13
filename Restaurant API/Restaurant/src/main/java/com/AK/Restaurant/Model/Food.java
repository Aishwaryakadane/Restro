package com.AK.Restaurant.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Integer foodId;
    private String foodTitle;
    private String foodDesc;
    @Enumerated(EnumType.STRING)
    private FoodType foodType;
    private String foodImg;
    private Double foodPrice;

}