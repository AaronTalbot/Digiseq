package com.digisec.project.service;

import com.digisec.project.model.ClientOrganisation;
import com.digisec.project.repository.ClientOrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientOrganisationService {

    @Autowired
    private ClientOrganisationRepository repository;

    public List<ClientOrganisation> getAllClientOrganisations() {
        return repository.findAll();
    }

    public ClientOrganisation getClientOrganisationById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Client Organisation not found"));
    }

    public ClientOrganisation createClientOrganisation(ClientOrganisation clientOrganisation) {
        clientOrganisation.setEnabled(true);
        return repository.save(clientOrganisation);
    }

    public ClientOrganisation updateClientOrganisation(Long id, ClientOrganisation updatedClientOrganisation) {
        ClientOrganisation existing = getClientOrganisationById(id);
        existing.setName(updatedClientOrganisation.getName());
        existing.setRegistrationDate(updatedClientOrganisation.getRegistrationDate());
        existing.setExpiryDate(updatedClientOrganisation.getExpiryDate());
        existing.setEnabled(updatedClientOrganisation.isEnabled());
        return repository.save(existing);
    }

    public void deleteClientOrganisation(Long id) {
        repository.deleteById(id);
    }

    public long countEnabledClientOrganisations() {
        return repository.findByEnabled(true).size();
    }
}
