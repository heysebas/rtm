package org.example.cda.model.service;

import org.example.cda.model.entity.BrandCar;
import org.example.cda.model.entity.BrandMotocicleta;

import java.util.List;

public interface IBrandMotocicletaService {
    List<BrandMotocicleta> listBrandMotocicletas(); // lista de vehiculos
    void saveBrandMotocicleta(BrandMotocicleta brandMotocicleta); // guardar vehiculos
    BrandMotocicleta getBrandMotocicletasById(Long id); // obtener vehiculos por id
    BrandMotocicleta updateBrandMotocicletas(BrandMotocicleta brandMotocicleta); // actualizar vehiculos
    void deleteBrandMotocicletas(Long id); // eliminar vehiculos
    List<Object[]> listConsultation();

        //    // Método para buscar vehiculos por placa
        BrandMotocicleta findByName(String name);
        //
        //    List<String> getAllVehicleNames();
        //
        //    List<String> getAllMotociletaNames();



        }
