package com.digisec.project.controller;

import com.digisec.project.model.Personnel;
import com.digisec.project.service.PersonnelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/personnel")
public class PersonnelController {
    @Autowired
    private PersonnelService service;

    @GetMapping
    public List<Personnel> getAllPersonnel() {
        return service.getAllPersonnel();
    }

    @GetMapping("/{id}")
    public Personnel getPersonnelById(@PathVariable Long id) {
        return service.getPersonnelById(id);
    }

    @PostMapping
    public Personnel createPersonnel(@RequestBody Personnel personnel) {
        return service.createPersonnel(personnel);
    }

    @PutMapping("/{id}")
    public Personnel updatePersonnel(@PathVariable Long id, @RequestBody Personnel updatedPersonnel) {
        return service.updatePersonnel(id, updatedPersonnel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonnel(@PathVariable Long id) {
        service.deletePersonnel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
