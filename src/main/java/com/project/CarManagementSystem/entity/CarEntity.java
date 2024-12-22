package com.project.CarManagementSystem.entity;

import com.project.CarManagementSystem.entity.enums.FUEL_TYPE;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="CarDetail")
@EntityListeners(AuditingEntityListener.class)
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String carName;
    private String model;
    private Long price;
    private String color;
    private int year;
    private FUEL_TYPE fuelType;
    @CreatedDate
    @Column(nullable = false,updatable = false)
    private LocalDateTime created_at;
    @LastModifiedDate
    private LocalDateTime updated_at;
}
