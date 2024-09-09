package org.example.cda.model.service;


import org.example.cda.model.entity.BrandCar;
import org.example.cda.model.repository.BrandCarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;


@Service
public class BrandCarServiceImpl implements IBrandCarService {
    private final BrandCarRepository brandCarRepository;

    public BrandCarServiceImpl(BrandCarRepository brandCarRepository) {
        this.brandCarRepository = brandCarRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandCar> listBrandCars() {
        return brandCarRepository.findAll();
    }

    @Override
    @Transactional
    public void saveBrandCar(BrandCar vehicle) {
        brandCarRepository.save(vehicle);
    }

    @Override
    public BrandCar getBrandCarsById(Long id) {
        return brandCarRepository.findById(id).orElse(null);
    }

    @Override
    public BrandCar updateBrandCars(BrandCar vehicle) {
        return brandCarRepository.save(vehicle);
    }

    @Override
    public void deleteBrandCars(Long id) {
        brandCarRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> listConsultation() {
        return brandCarRepository.filter();
    }

    @Override
    @Transactional(readOnly = true)
    public BrandCar findByName(String name) {
        Optional<BrandCar> brandCarOpt = brandCarRepository.findFirstByName(name);
        return brandCarOpt.orElse(null);
    }
//
//
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<String> getAllVehicleNames() {
//        List<Object[]> results = vehicleRepository.findAllVehicleNames();
//        return results.stream()
//                .map(result -> (String) result[0])
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    @Transactional(readOnly = true)
//    public List<String> getAllMotociletaNames() {
//        List<Object[]> results = vehicleRepository.findAllMotocicletaNames();
//        return results.stream()
//                .map(result -> (String) result[0])
//                .collect(Collectors.toList());
//    }


}
