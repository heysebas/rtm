package org.example.cda.model.service;

import org.example.cda.model.entity.Client;
import org.example.cda.model.repository.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements IClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> listCustomers() {
        return clientRepository.findAll();
    }

    @Override
    @Transactional
    public void saveCustomer(Client client) {
        clientRepository.save(client);
    }

    @Override
    public Client getCustomersById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client updateClients(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClients(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Object[]> listConsultation() {
        return clientRepository.filter();
    }

    @Override
    @Transactional(readOnly = true)
    public Client findByDocument(String document) {
        Optional<Client> clientOpt = clientRepository.findFirstByDocument(document);
        return clientOpt.orElse(null);
    }
}
