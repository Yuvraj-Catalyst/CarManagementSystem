package com.project.CarManagementSystem.dto;

import com.project.CarManagementSystem.annotations_validation.CarColorValidation;
import com.project.CarManagementSystem.entity.enums.FUEL_TYPE;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {
    @NotBlank(message = "Car Name can't be Empty.")
    private String carName;
    @NotBlank(message = "Model name can't be empty.")
    private String model;
    @Positive
    private long price;
    @CarColorValidation
    private String color;
    @PastOrPresent(message = "Year can't be future")
    private int year;
    private FUEL_TYPE fuelType;
}
