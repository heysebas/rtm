package org.example.cda.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;


@Data
@Entity
@Table(name = "tbl_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // Nombre Completo

    @Column(unique = true)
    private String document; // Cedula, NIT, etc.

    private String phone; // Telefono Celular
    private String email; // Correo
    private Date date; // Fecha
    private String address; // Direccion
    private String city; // Ciudad
    private String department; // Departamento


}
