package org.example.cda.model.service;


import org.example.cda.model.entity.Vehicle;
import org.example.cda.model.repository.VehicleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements IVehicleService {
    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Vehicle> listVehicles() {
        return vehicleRepository.findAll();
    }

    @Override
    @Transactional
    public void saveVehicle(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

    @Override
    public Vehicle getVehiclesById(Long id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    @Override
    public Vehicle updateVehicles(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public void deleteVehicles(Long id) {
        vehicleRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> listConsultation() {
        return vehicleRepository.filter();
    }

    @Override
    @Transactional(readOnly = true)
    public Vehicle findByPlateNumber(String plate_number) {
        Optional<Vehicle> vehicleOpt = vehicleRepository.findFirstByPlateNumber(plate_number);
        return vehicleOpt.orElse(null);
    }



    @Override
    @Transactional(readOnly = true)
    public List<String> getAllVehicleNames() {
        List<Object[]> results = vehicleRepository.findAllVehicleNames();
        return results.stream()
                .map(result -> (String) result[0])
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> getAllMotociletaNames() {
        List<Object[]> results = vehicleRepository.findAllMotocicletaNames();
        return results.stream()
                .map(result -> (String) result[0])
                .collect(Collectors.toList());
    }


}
