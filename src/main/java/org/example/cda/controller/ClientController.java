package org.example.cda.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.cda.model.entity.Client;
import org.example.cda.model.service.IClientService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class ClientController {

    private final IClientService service;

    public ClientController(IClientService service) {
        this.service = service;
    }

    @GetMapping({ "/", "/index" })
    public String listCustomer(Model model) {
        model.addAttribute("title", "Facturación | Customers");
        model.addAttribute("customers", service.listCustomers());
        return "customers";
    }

    @GetMapping("/new")
    public String showCustomerRegistrationForm(Model model) {
        model.addAttribute("customer", new Client());
        return "create_customer";
    }

    @PostMapping("/register")
    public String saveCustomer(@ModelAttribute("customer") Client customer, BindingResult result,
                               RedirectAttributes flash, HttpServletRequest request) {
        Client existingClient = service.findByDocument(customer.getDocument());

        if (existingClient != null) {
            // Cliente ya existe, redirigir a la vista de confirmación
            request.setAttribute("clientToEdit", existingClient);
            return "confirm_edit";
        }

        if (result.hasErrors()) {
            return "create_customer"; // Regresa al formulario en caso de error
        }

        service.saveCustomer(customer);
        flash.addFlashAttribute("success", "Cliente guardado con éxito");
        return "redirect:/vehicles/espera";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("customer", service.getCustomersById(id));
        return "edit_customer";
    }

    @PostMapping("/update/{id}")
    public String updateClient(@PathVariable Long id, @ModelAttribute("customer") Client customer) {
        Client existingClient = service.getCustomersById(id);
        existingClient.setName(customer.getName());
        existingClient.setDocument(customer.getDocument());
        existingClient.setPhone(customer.getPhone());
        existingClient.setEmail(customer.getEmail());
        existingClient.setAddress(customer.getAddress());
        existingClient.setCity(customer.getCity());
        existingClient.setDepartment(customer.getDepartment());

        service.updateClients(existingClient);
        return "redirect:/vehicles/espera";
    }

    @GetMapping("/delete/{id}")
    public String deleteClient(@PathVariable Long id) {
        service.deleteClients(id);
        return "redirect:/customers/";
    }

    @GetMapping("/search")
    public String searchCustomer(@RequestParam("document") String document, Model model) {
        Client client = service.findByDocument(document);
        if (client != null) {
            model.addAttribute("customers", List.of(client));
        } else {
            model.addAttribute("customers", List.of());
        }
        return "customers";
    }


    @PostMapping("/confirm-edit")
    public String confirmEdit(@RequestParam("confirm") String confirm, @RequestParam("document") String document, RedirectAttributes flash) {
        if ("yes".equals(confirm)) {
            Client existingClient = service.findByDocument(document);
            if (existingClient != null) {
                return "redirect:/customers/edit/" + existingClient.getId();
            }
        }
        return "redirect:/vehicles/espera";
    }
}
