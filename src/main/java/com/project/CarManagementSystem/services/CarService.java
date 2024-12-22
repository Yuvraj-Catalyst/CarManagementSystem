package com.project.CarManagementSystem.services;


import com.project.CarManagementSystem.dto.CarDto;

import java.util.List;

public interface CarService {
    public List<CarDto> getCars(String carName,String model,int year,int pageNumber,String sortBy);
    public CarDto getCarById(Long id);
    public CarDto create(CarDto carDto);
    public CarDto updateCarById(Long id,CarDto carDto);
    public void deleteCarById(Long id);
    public List<CarDto> search(String keyword,String sortBy,int pageNumber);

}
