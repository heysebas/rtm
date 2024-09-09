package org.example.cda.model.repository;

import org.example.cda.model.entity.BrandCar;
import org.example.cda.model.entity.BrandMotocicleta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BrandMotocicletaRepository extends JpaRepository<BrandMotocicleta, Long> {

    Optional<BrandMotocicleta> findFirstByName(String name);


    @Query(value = "SELECT * FROM motocicletas ORDER BY name ASC", nativeQuery = true)
    List<Object[]> filter();

//
//    // Consulta a la otra tabla 'vehiculos'
//    @Query(value = "SELECT name FROM vehiculos", nativeQuery = true)
//    List<Object[]> findAllVehicleNames();
//
//    // Consulta a la otra tabla 'Motosicleta'
//    @Query(value = "SELECT name FROM motocicletas", nativeQuery = true)
//    List<Object[]> findAllMotocicletaNames();
//
//
//    @Query(value = "SELECT name FROM modelo m WHERE m.vehicle.id = :vehicleId", nativeQuery = true)
//    List<Object[]> findAllModel();


}





