package org.example.cda.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cda.model.entity.Vehicle;
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

    public VehicleController(IVehicleService service) {
        this.service = service;
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

        if (result.hasErrors()) {
            return "create_vehicle"; // Regresa al formulario en caso de error
        }

        System.out.println("entro a la condicion ");
        service.saveVehicle(vehicle);
        flash.addFlashAttribute("success", "Vehiculo guardado con éxito");
        return "redirect:/vehicles/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("vehicle", service.getVehiclesById(id));
        return "edit_vehicle";
    }

    @PostMapping("/update/{id}")
    public String updateVehicle(@PathVariable Long id, @ModelAttribute("vehicle") Vehicle vehicle) {
        Vehicle existingVehicle = service.getVehiclesById(id);
//        existingVehicle.setPlateNumber(vehicle.getName());
//        existingVehicle.setDocument(vehicle.getDocument());
//        existingVehicle.setPhone(vehicle.getPhone());
//        existingVehicle.setEmail(vehicle.getEmail());
//        existingVehicle.setAddress(vehicle.getAddress());
//        existingVehicle.setCity(vehicle.getCity());
//        existingVehicle.setDepartment(vehicle.getDepartment());

        service.updateVehicles(existingVehicle);
        return "redirect:/vehicles/";
    }

    @GetMapping("/delete/{id}")
    public String deleteVehicle(@PathVariable Long id) {
        service.deleteVehicles(id);
        return "redirect:/vehicles/";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam("plateNumber") String plateNumber, Model model) {
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
}
