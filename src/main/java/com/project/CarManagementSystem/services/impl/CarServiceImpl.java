package com.project.CarManagementSystem.services.impl;

import com.project.CarManagementSystem.dto.CarDto;
import com.project.CarManagementSystem.entity.CarEntity;
import com.project.CarManagementSystem.repository.CarRepository;
import com.project.CarManagementSystem.services.CarService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final int PAGE_SIZE = 5;
    @Override
    public List<CarDto> getCars(String carName,String model,int year,int pageNumber,String sortBy) {
        List<CarEntity> carEntities=new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE, Sort.by(sortBy));
        if(!carName.isBlank() && !model.isBlank() && year>0){
            carEntities.addAll(carRepository.findByCarNameAndModelAndYear(carName,model,year,pageable));
        }
        else if(!carName.isBlank() && !model.isBlank()){
            carEntities.addAll(carRepository.findByCarNameAndModel(carName,model,pageable));
        }
        else if(!carName.isBlank() && year>0){
            carEntities.addAll(carRepository.findByCarNameAndYear(carName,year,pageable));
        }
        else if(!model.isBlank() && year>0){
            carEntities.addAll(carRepository.findByModelAndYear(model,year,pageable));
        }
        else if(!model.isBlank()){
            carEntities.addAll(carRepository.findByModel(model,pageable));
        }
        else if(!carName.isBlank()){
            carEntities.addAll(carRepository.findByCarName(carName,pageable));
        }
        else if(year>0){
            carEntities.addAll(carRepository.findByYear(year,pageable));
        }
        else{
            carEntities.addAll(carRepository.findAll(pageable).getContent());
        }
        return carEntities.stream().map(carEntity ->
            modelMapper.map(carEntity,CarDto.class)
        ).toList();
    }
    public List<CarDto> search(String keyword,String sortBy,int pageNumber){
        Pageable pageable= PageRequest.of(pageNumber,PAGE_SIZE,Sort.by(sortBy));
        List<CarEntity> carEntities = carRepository.findByCarNameLike(keyword,pageable);
        return carEntities.stream().map(carEntity ->
                modelMapper.map(carEntity,CarDto.class)
        ).toList();
    }
    @Override
    public CarDto getCarById(Long id) {
        CarEntity carEntity = carRepository.findById(id).orElse(null);
        if(carEntity==null) return null;
        return modelMapper.map(carEntity,CarDto.class);
    }

    @Override
    public CarDto create(CarDto carDto) {
        CarEntity carEntity = modelMapper.map(carDto,CarEntity.class);
        CarEntity savedCarEntry = carRepository.save(carEntity);
        return modelMapper.map(savedCarEntry,CarDto.class);
    }

    @Override
    public CarDto updateCarById(Long id,CarDto carDto) {
        boolean available = isPresent(id);
        if(available){
        CarEntity carEntity = CarEntity.builder()
                .id(id)
                .carName(carDto.getCarName())
                .model(carDto.getModel())
                .price(carDto.getPrice())
                .color(carDto.getColor())
                .fuelType(carDto.getFuelType())
                .build();
            return modelMapper.map(carRepository.save(carEntity), CarDto.class);
        }
        return null;
    }
    public boolean isPresent(Long id){
        CarEntity carEntity = carRepository.findById(id).orElse(null);
        return carEntity != null;
    }

    @Override
    public void deleteCarById(Long id) {
        boolean available = isPresent(id);
        if(available) carRepository.deleteById(id);
    }
}
