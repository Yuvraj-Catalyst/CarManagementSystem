package com.project.CarManagementSystem.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

@Data
@Builder
public class ApiError {
    private String message;
    private HttpStatus httpStatus;
    private Timestamp timestamp;

}
