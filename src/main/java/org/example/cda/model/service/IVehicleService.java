package org.example.cda.model.service;

import org.example.cda.model.entity.Vehicle;

import java.util.List;

public interface IVehicleService {
    List<Vehicle> listVehicles(); // lista de vehiculos
    void saveVehicle(Vehicle vehicle); // guardar vehiculos
    Vehicle getVehiclesById(Long id); // obtener vehiculos por id
    Vehicle updateVehicles(Vehicle vehicle); // actualizar vehiculos
    void deleteVehicles(Long id); // eliminar vehiculos
    List<Object[]> listConsultation();

    // Método para buscar vehiculos por placa
    Vehicle findByPlateNumber(String plate_number);
}
