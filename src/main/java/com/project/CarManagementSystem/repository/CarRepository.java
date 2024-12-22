package com.project.CarManagementSystem.repository;

import com.project.CarManagementSystem.dto.CarDto;
import com.project.CarManagementSystem.entity.CarEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<CarEntity,Long> {
    public List<CarEntity> findByCarNameLike(String carName,Pageable pageable);
    public List<CarEntity> findByCarName(String carName, Pageable pageable);
    public List<CarEntity> findByModel(String model,Pageable pageable);
    public List<CarEntity> findByYear(int year,Pageable pageable);
    public List<CarEntity> findByCarNameAndYear(String carName,int year,Pageable pageable);
    public List<CarEntity> findByCarNameAndModel(String carName,String model,Pageable pageable);
    public List<CarEntity> findByModelAndYear(String model,int year,Pageable pageable);
    public List<CarEntity> findByCarNameAndModelAndYear(String carName, String model, int year, Pageable pageable);
}
