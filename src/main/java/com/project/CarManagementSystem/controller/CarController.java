package com.project.CarManagementSystem.controller;

import com.project.CarManagementSystem.dto.CarDto;
import com.project.CarManagementSystem.services.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class CarController {
    private final CarService carService;
    @PostMapping("add-car")
    public ResponseEntity<CarDto> create(@RequestBody @Valid CarDto carDto){
        CarDto carDto1 = carService.create(carDto);
        return new ResponseEntity<>(carDto1, HttpStatus.CREATED);
    }
    @GetMapping("search-car")
    public ResponseEntity<List<CarDto>> search(@RequestParam(required = false,defaultValue = "") String keyword,
                                               @RequestParam(required = false,defaultValue = "id") String sortBy,
                                               @RequestParam(required = false,defaultValue = "0") int pageNumber){
        List<CarDto> carDtos = carService.search(keyword,sortBy,pageNumber);
        return new ResponseEntity<>(carDtos,HttpStatus.OK);
    }
    @GetMapping("get-car/{id}")
    public ResponseEntity<CarDto> getCarById(@PathVariable Long id){
        CarDto carDto = carService.getCarById(id);
        return new ResponseEntity<>(carDto,HttpStatus.FOUND);
    }

    @GetMapping("get-cars")
    public ResponseEntity<List<CarDto>> getCars(@RequestParam(required = false,defaultValue = "") String carName,
                                                @RequestParam(required = false,defaultValue = "") String model,
                                                @RequestParam(required = false,defaultValue = "0") int year,
                                                @RequestParam(required = false,defaultValue = "0") int pageNumber,
                                                @RequestParam(required = false,defaultValue = "id") String sortBy){
        List<CarDto> carDto = carService.getCars(carName,model,year,pageNumber,sortBy);
        return new ResponseEntity<>(carDto,HttpStatus.OK);
    }

    @PutMapping("update-car/{id}")
    public ResponseEntity<CarDto> updateCarById(@PathVariable Long id,@RequestBody @Valid CarDto carDto){
        CarDto carDto1 = carService.updateCarById(id,carDto);
        return new ResponseEntity<>(carDto1,HttpStatus.OK);
    }

    @DeleteMapping("delete-car/{id}")
    public HttpStatus deleteCarById(@PathVariable Long id){
        carService.deleteCarById(id);
        return HttpStatus.OK;
    }

}
