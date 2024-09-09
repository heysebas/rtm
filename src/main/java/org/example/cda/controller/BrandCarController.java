package org.example.cda.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cda.model.entity.BrandCar;
import org.example.cda.model.entity.Client;
import org.example.cda.model.service.IBrandCarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/brandCars")
public class BrandCarController {

    private final IBrandCarService service;

    public BrandCarController(IBrandCarService service) {
        this.service = service;
    }

    @GetMapping({ "/", "/index" })
    public String listBrandCar(Model model) {
        model.addAttribute("title", "Facturación | brandCars");
        model.addAttribute("brandCars", service.listBrandCars());
        return "brandCars";
    }

    @GetMapping("/new")
    public String showBrandCarRegistrationForm(Model model) {
        model.addAttribute("brandCar", new BrandCar());
        return "create_brandCar";
    }

    @PostMapping("/register")
    public String saveBrandCar(@ModelAttribute("brandCar") BrandCar brandCar, BindingResult result,
                               RedirectAttributes flash, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "create_brandCar";
        }

        BrandCar existingBrandCar = service.findByName(brandCar.getName());

        if (existingBrandCar != null) {
            request.setAttribute("brandCarToEdit", existingBrandCar);
            return "confirm_edit";
        }

        service.saveBrandCar(brandCar);
        flash.addFlashAttribute("success", "Modelo guardado con éxito");
        return "redirect:/brandCars/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("brandCar", service.getBrandCarsById(id));
        return "edit_brandCar";
    }

    @PostMapping("/update/{id}")
    public String updateBrandCar(@PathVariable Long id, @ModelAttribute("brandCar") BrandCar brandCar) {
        BrandCar existingBrandCar = service.getBrandCarsById(id);
        existingBrandCar.setName(brandCar.getName());

        service.updateBrandCars(existingBrandCar);
        return "redirect:/brandCars/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrandCar(@PathVariable Long id) {
        service.deleteBrandCars(id);
        return "redirect:/brandCars/";
    }
}
