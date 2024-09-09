package org.example.cda.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "tbl_vehicle")
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "plate_number", unique = true)
    private String plateNumber; // Placa

    private String country; // País
    private String service; // Servicio
    private String type;  // Tipo de vehículo
    private String clas; // Clase de vehículo
    private String brand; // Marca
    private String line; // Línea
    @Column(name = "traffic_license_number")
    private String trafficLicenseNumber; // Número de licencia de tránsito
    @Column(name = "registration_date")
    private Date registrationDate; // Fecha Matrícula
    private String color; // Color
    @Column(name = "fuel_propulsion")
    private String fuelPropulsion; // Combustible/Propulsión
    @Column(name = "vin_or_chassis")
    private String VinOrChassis; // VIN o Chasis
    @Column(name = "engine_number")
    private String engineNumber; // Número de motor
    @Column(name = "type_of_motor")
    private String typeOfMotor; // Tipo de motor
    @Column(name = "cylinder_capacity")
    private String cylinderCapacity; // Cilindraje
    private String mileage; // Kilometraje
    @Column(name = "passenger_number")
    private int passengerNumber; // Número de pasajeros sin incluir al conductor
    private boolean armor; // Blindaje
    private String power; // Potencia
    @Column(name = "body_type")
    private String bodyType; // Tipo de Carrocería
    private Date soat; // Fecha de vencimiento del SOAT
    @Column(name = "ngv_conversion")
    private String ngvConversion; // Conversión GNV
    @Column(name = "date_ngv_conversion")
    private Date dateNgvConversion; // Fecha de conversión GNV
    @Column(name = "document_client")
    private String documentClient; // Documento del cliente
    private String state = "Revisión"; // Estado de vehículos por defecto  // Aprobado Rechazado y en Revisión

    // Campo para la fecha actual
    @Column(name = "fecha_actual")
    private Date fechaActual;

    // Constructor
    public Vehicle() {
        this.fechaActual = Date.valueOf(LocalDate.now());
    }


    // Relaciones con otras tablas pueden agregarse aquí
    // @ManyToOne
    // @JoinColumn(name = "client_id")
    // private Client client;
}
