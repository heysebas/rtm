package org.example.cda.model.repository;

import org.example.cda.model.entity.BrandCar;

import org.example.cda.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface BrandCarRepository extends JpaRepository<BrandCar, Long> {

    Optional<BrandCar> findFirstByName(String name);


    @Query(value = "SELECT * FROM vehiculos ORDER BY name ASC", nativeQuery = true)
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





