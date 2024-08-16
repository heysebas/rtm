package org.example.cda.model.repository;

import org.example.cda.model.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    Optional<Vehicle> findFirstByPlateNumber(String plate_number);


    @Query(value = "SELECT * FROM tbl_vehicle ORDER BY name ASC", nativeQuery = true)
    List<Object[]> filter();
}
