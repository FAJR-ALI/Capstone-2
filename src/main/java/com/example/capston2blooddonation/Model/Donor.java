package com.example.capston2blooddonation.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@AllArgsConstructor
@Data
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Size(min = 7, message = "Please Write down your full name")
    private String name;

    @NotNull
    @Pattern(regexp = "^[0-9]{10}$", message = "please Enter your phone number correctly ")
    private String phoneNumber;

    @NotNull
    private String bloodType;

    @NotNull
    @Min(value = 18 , message = "Age must be 18 or Older ")
    private Integer age;

    @NotEmpty
    private String city;


}
