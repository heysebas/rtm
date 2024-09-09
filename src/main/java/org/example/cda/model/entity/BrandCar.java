package org.example.cda.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "vehiculos")
public class BrandCar {
    @Id
    private Long id;

    private String name;


}
