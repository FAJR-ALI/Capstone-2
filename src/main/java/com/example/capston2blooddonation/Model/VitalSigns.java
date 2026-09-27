package com.example.capston2blooddonation.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VitalSigns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    @NotEmpty
    private String bloodPressure;

    @NotNull
    private Integer heartRate;

    @NotNull
    private Double temperature;

    @NotNull
    private Double hemoglobinLevel;

    @NotNull
    private Double bloodSugar;
}
