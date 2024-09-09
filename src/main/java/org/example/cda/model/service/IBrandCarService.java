package org.example.cda.model.service;

import org.example.cda.model.entity.BrandCar;
import org.example.cda.model.entity.Vehicle;

import java.util.List;

public interface IBrandCarService {
    List<BrandCar> listBrandCars(); // lista de vehiculos
    void saveBrandCar(BrandCar brandCar); // guardar vehiculos
    BrandCar getBrandCarsById(Long id); // obtener vehiculos por id
    BrandCar updateBrandCars(BrandCar brandCar); // actualizar vehiculos
    void deleteBrandCars(Long id); // eliminar vehiculos
    List<Object[]> listConsultation();

//    // Método para buscar vehiculos por placa
BrandCar findByName(String name);
//
//    List<String> getAllVehicleNames();
//
//    List<String> getAllMotociletaNames();



}
