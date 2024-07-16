package com.digisec.project.controller;

import com.digisec.project.model.ClientOrganisation;
import com.digisec.project.service.ClientOrganisationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-organisations")
public class ClientOrganisationController {

    @Autowired
    private ClientOrganisationService service;

    @GetMapping
    public List<ClientOrganisation> getAllClientOrganisations() {
        return service.getAllClientOrganisations();
    }

    @GetMapping("/{id}")
    public ClientOrganisation getClientOrganisationById(@PathVariable Long id) {
        return service.getClientOrganisationById(id);
    }

    @PostMapping
    public ClientOrganisation createClientOrganisation(@RequestBody ClientOrganisation clientOrganisation) {
        return service.createClientOrganisation(clientOrganisation);
    }

    @PutMapping("/{id}")
    public ClientOrganisation updateClientOrganisation(@PathVariable Long id, @RequestBody ClientOrganisation updatedClientOrganisation) {
        return service.updateClientOrganisation(id, updatedClientOrganisation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClientOrganisation(@PathVariable Long id) {
        service.deleteClientOrganisation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
