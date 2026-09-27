package com.example.capston2blooddonation.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BloodInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Pattern(regexp = "^(A\\+|A-|B\\+|B-|AB\\+|AB-|O\\+|O-)$", message = "Blood type must be valid")
    private String bloodType;

    @NotNull
    @Min(value = 0, message = "Quantity cannot be negative")
    private Integer quantity;

    @NotEmpty
    @Size(min = 3, message = "City must be more than 2 characters")
    private String city;

}
