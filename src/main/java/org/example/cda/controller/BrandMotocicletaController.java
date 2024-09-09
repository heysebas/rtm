package org.example.cda.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cda.model.entity.BrandCar;
import org.example.cda.model.entity.BrandMotocicleta;
import org.example.cda.model.service.IBrandCarService;
import org.example.cda.model.service.IBrandMotocicletaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/brandMotocicletas")
public class BrandMotocicletaController {

    private final IBrandMotocicletaService service;

    public BrandMotocicletaController(IBrandMotocicletaService service) {
        this.service = service;
    }

    @GetMapping({ "/", "/index" })
    public String listBrandMotocicletas(Model model) {
        model.addAttribute("title", "Facturación | brandMotocicletas");
        model.addAttribute("brandMotocicletas", service.listBrandMotocicletas());
        return "brandMotocicletas";
    }



    @GetMapping("/new")
    public String showBrandMotocicletaRegistrationForm(Model model) {
        model.addAttribute("brandMotocicleta", new BrandMotocicleta());
        return "create_brandMotocicleta";
    }


    @PostMapping("/register")
    public String saveBrandMotocicleta(@ModelAttribute("brandMotocicleta") BrandMotocicleta brandMotocicleta, BindingResult result,
                                       RedirectAttributes flash, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "create_brandMotocicleta";
        }

        BrandMotocicleta existingBrandMotocicleta = service.findByName(brandMotocicleta.getName());

        if (existingBrandMotocicleta != null) {
            request.setAttribute("brandMotocicletaToEdit", existingBrandMotocicleta);
            return "confirm_edit";
        }

        service.saveBrandMotocicleta(brandMotocicleta);
        flash.addFlashAttribute("success", "Modelo guardado con éxito");
        return "redirect:/brandMotocicletas/";
    }


    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("brandMotocicleta", service.getBrandMotocicletasById(id));
        return "edit_brandMotocicleta";
    }

    @PostMapping("/update/{id}")
    public String updateBrandMotocicleta(@PathVariable Long id, @ModelAttribute("brandMotocicleta") BrandMotocicleta brandMotocicleta) {
        BrandMotocicleta existingBrandMotocicleta = service.getBrandMotocicletasById(id);
        existingBrandMotocicleta.setName(brandMotocicleta.getName());

        service.updateBrandMotocicletas(existingBrandMotocicleta);
        return "redirect:/brandMotocicletas/";
    }

    @GetMapping("/delete/{id}")
    public String deleteBrandMotocicleta(@PathVariable Long id) {
        service.deleteBrandMotocicletas(id);
        return "redirect:/brandMotocicletas/";
    }
}
