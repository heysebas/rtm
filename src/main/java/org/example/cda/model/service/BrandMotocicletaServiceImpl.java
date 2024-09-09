package org.example.cda.model.service;


import org.example.cda.model.entity.BrandCar;
import org.example.cda.model.entity.BrandMotocicleta;
import org.example.cda.model.repository.BrandCarRepository;
import org.example.cda.model.repository.BrandMotocicletaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class BrandMotocicletaServiceImpl implements IBrandMotocicletaService {
    private final BrandMotocicletaRepository brandMotocicletaRepository;

    public BrandMotocicletaServiceImpl(BrandMotocicletaRepository brandMotocicletaRepository) {
        this.brandMotocicletaRepository = brandMotocicletaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BrandMotocicleta> listBrandMotocicletas() {
        return brandMotocicletaRepository.findAll();
    }

    @Override
    @Transactional
    public void saveBrandMotocicleta(BrandMotocicleta vehicle) {
        brandMotocicletaRepository.save(vehicle);
    }

    @Override
    public BrandMotocicleta getBrandMotocicletasById(Long id) {
        return brandMotocicletaRepository.findById(id).orElse(null);
    }

    @Override
    public BrandMotocicleta updateBrandMotocicletas(BrandMotocicleta vehicle) {
        return brandMotocicletaRepository.save(vehicle);
    }

    @Override
    public void deleteBrandMotocicletas(Long id) {
        brandMotocicletaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> listConsultation() {
        return brandMotocicletaRepository.filter();
    }

    @Override
    @Transactional(readOnly = true)
    public BrandMotocicleta findByName(String name) {
        Optional<BrandMotocicleta> brandCarOpt = brandMotocicletaRepository.findFirstByName(name);
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
