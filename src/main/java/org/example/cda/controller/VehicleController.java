package org.example.cda.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cda.model.entity.Client;
import org.example.cda.model.entity.Vehicle;
import org.example.cda.model.service.IClientService;
import org.example.cda.model.service.IVehicleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/vehicles")
public class VehicleController {

    private final IVehicleService service;
    private final IClientService service2;

    public VehicleController(IVehicleService service, IClientService service2) {
        this.service = service;
        this.service2 = service2;
    }

    @GetMapping({ "/", "/index" })
    public String listVehicle(Model model) {
        model.addAttribute("title", "Facturación | Vehicles");
        model.addAttribute("vehicles", service.listVehicles());
        return "vehicles";
    }

    @GetMapping("/new")
    public String showVehicleRegistrationForm(Model model) {
        model.addAttribute("vehicle", new Vehicle());

        List<String> vehicleNames = service.getAllVehicleNames();
        List<String> motocicletaNames = service.getAllMotociletaNames();

        System.out.println("vehicleNames: " + vehicleNames);  // Verificar si no están vacíos
        System.out.println("motociletaNames: " + motocicletaNames);  // Verificar si no están vacíos

        model.addAttribute("vehicleNames", vehicleNames);
        model.addAttribute("vehicleNames2", motocicletaNames);

        return "create_vehicle";
    }


    @PostMapping("/register")
    public String saveVehicle(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result,
                              RedirectAttributes flash, HttpServletRequest request) {
        Vehicle existingVehicle = service.findByPlateNumber(vehicle.getPlateNumber());

        if (existingVehicle != null) {
            // Cliente ya existe, redirigir a la vista de confirmación
            request.setAttribute("vehicleToEdit", existingVehicle);
            return "confirm_edit_vehicle";
        }

        Client existingClient = service2.findByDocument(vehicle.getDocumentClient());

        if (existingClient != null) {
            // Cliente ya existe, redirigir a la vista de confirmación
            request.setAttribute("clientToEdit", existingClient);
            service.saveVehicle(vehicle);
            flash.addFlashAttribute("success", "Vehículo guardado con éxito");
            return "confirm_edit";
        }

        if (result.hasErrors()) {
            return "create_vehicle"; // Regresa al formulario en caso de error
        }



        service.saveVehicle(vehicle);
        flash.addFlashAttribute("success", "Vehículo guardado con éxito");
        return "redirect:/customers/new";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", service.getVehiclesById(id));
        return "edit_vehicle";
    }

    @PostMapping("/update/{id}")
    public String updateVehicle(@PathVariable Long id, @ModelAttribute("vehicle") Vehicle vehicle) {
        Vehicle existingVehicle = service.getVehiclesById(id);
        existingVehicle.setPlateNumber(vehicle.getPlateNumber());
        existingVehicle.setPlateNumber(vehicle.getPlateNumber());
        existingVehicle.setCountry(vehicle.getCountry());
        existingVehicle.setService(vehicle.getService());
        existingVehicle.setType(vehicle.getType());
        existingVehicle.setClas(vehicle.getClas());
        existingVehicle.setBrand(vehicle.getBrand());
        existingVehicle.setLine(vehicle.getLine());
        existingVehicle.setTrafficLicenseNumber(vehicle.getTrafficLicenseNumber());
        existingVehicle.setRegistrationDate(vehicle.getRegistrationDate());
        existingVehicle.setColor(vehicle.getColor());
        existingVehicle.setFuelPropulsion(vehicle.getFuelPropulsion());
        existingVehicle.setVinOrChassis(vehicle.getVinOrChassis());
        existingVehicle.setEngineNumber(vehicle.getEngineNumber());
        existingVehicle.setTypeOfMotor(vehicle.getTypeOfMotor());
        existingVehicle.setCylinderCapacity(vehicle.getCylinderCapacity());
        existingVehicle.setMileage(vehicle.getMileage());
        existingVehicle.setPassengerNumber(vehicle.getPassengerNumber());
//        existingVehicle.setArmor(vehicle.getArmor());
        existingVehicle.setPower(vehicle.getPower());
        existingVehicle.setBodyType(vehicle.getBodyType());
        existingVehicle.setSoat(vehicle.getSoat());
        existingVehicle.setNgvConversion(vehicle.getNgvConversion());
        existingVehicle.setDateNgvConversion(vehicle.getDateNgvConversion());
        existingVehicle.setDocumentClient(vehicle.getDocumentClient());
        existingVehicle.setState(vehicle.getState());
        existingVehicle.setFechaActual(vehicle.getFechaActual());

        service.updateVehicles(existingVehicle);
        return "redirect:/vehicles/";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        service.deleteVehicles(id);
        return "redirect:/vehicles/";
    }

    @GetMapping("/search_Vehicles")
    public String searchVehicle(@RequestParam("plateNumber") String plateNumber, Model model) {
        Vehicle vehicle = service.findByPlateNumber(plateNumber);
        if (vehicle != null) {
            model.addAttribute("vehicles", List.of(vehicle));
        } else {
            model.addAttribute("vehicles", List.of());
        }
        return "vehicles";
    }

    @PostMapping("/confirm-edit-vehicle")
    public String confirmEdit(@RequestParam("confirm") String confirm, @RequestParam("plateNumber") String plateNumber, RedirectAttributes flash) {
        if ("yes".equals(confirm)) {
            Vehicle existingVehicle = service.findByPlateNumber(plateNumber);
            if (existingVehicle != null) {
                return "redirect:/vehicles/edit/" + existingVehicle.getId();
            }
        }
        return "redirect:/vehicles/";
    }





//    ******************************************************************************************************************

    @GetMapping("/espera")
    public String listVehicleEspera(Model model) {
        model.addAttribute("title", "Lista de Vehículos");
        model.addAttribute("vehicles", service.listVehicles());
        return "espera"; // Redirige a la vista espera.html
    }





    @GetMapping("/searchvehicles")
    public String searchVehicles(Model model) {
        model.addAttribute("vehicle", new Vehicle());
        return "search_vehicles";
    }


    @PostMapping("/searchVehicle")
    public String searchVehicle(@ModelAttribute("vehicle") Vehicle vehicle, BindingResult result,
                              RedirectAttributes flash, HttpServletRequest request) {
        Vehicle existingVehicle = service.findByPlateNumber(vehicle.getPlateNumber());

        if (existingVehicle != null) {
            // Cliente ya existe, redirigir a la vista de confirmación
            request.setAttribute("vehicleToEdit", existingVehicle);
            return "confirm_edit_vehicle";
        }

        return "redirect:/vehicles/new";
    }



}
