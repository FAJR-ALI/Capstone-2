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
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Size(min = 7, message = "Please Write down your full name")
    private String name;

    @NotEmpty
    @Pattern(regexp = "^[0-9]{10}$", message = "please Enter your phone number correctly ")
    private String phoneNumber;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    @Pattern(regexp = "^(A|B|AB|O)[+-]$",message = "Blood type must be A+, A-, B+, B-, AB+, AB-, O+, or O-")
    private String bloodType;

    @NotNull
    @Min(value = 18 , message = "Age must be 18 or Older ")
    private Integer age;

    @NotEmpty
    private String city;

}
