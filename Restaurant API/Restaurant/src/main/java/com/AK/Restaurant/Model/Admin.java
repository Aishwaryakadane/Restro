package com.AK.Restaurant.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Admin {

    @Id
    private Integer adminId;
    private String adminName;
    @Pattern(regexp = ".*: admin\\.com$")
    private String adminEmail;
    private String adminPassword;
}
