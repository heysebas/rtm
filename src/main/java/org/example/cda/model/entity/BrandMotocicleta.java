package org.example.cda.model.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "motocicletas")
public class BrandMotocicleta {
    @Id
    private Long id;

    private String name;



}
