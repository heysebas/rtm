package org.example.cda.model.service;

import org.example.cda.model.entity.Client;
import java.util.List;

public interface IClientService {
    List<Client> listCustomers(); // lista de clientes
    void saveCustomer(Client client); // guardar clientes
    Client getCustomersById(Long id); // obtener clientes por id
    Client updateClients(Client client); // actualizar clientes
    void deleteClients(Long id); // eliminar clientes
    List<Object[]> listConsultation();

    // Método para buscar cliente por documento
    Client findByDocument(String document);
}
