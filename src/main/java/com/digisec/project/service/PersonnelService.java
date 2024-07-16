package com.digisec.project.service;

import com.digisec.project.model.Personnel;
import com.digisec.project.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonnelService {

    @Autowired
    private PersonnelRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Personnel> getAllPersonnel() {
        return repository.findAll();
    }

    public Personnel getPersonnelById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Personnel not found"));
    }

    public Personnel createPersonnel(Personnel personnel) {
        personnel.setPassword(passwordEncoder.encode(personnel.getPassword()));
        return repository.save(personnel);
    }

    public Personnel updatePersonnel(Long id, Personnel updatedPersonnel) {
        Personnel existing = getPersonnelById(id);
        existing.setFirstName(updatedPersonnel.getFirstName());
        existing.setLastName(updatedPersonnel.getLastName());
        existing.setUsername(updatedPersonnel.getUsername());
        existing.setPassword(passwordEncoder.encode(updatedPersonnel.getPassword()));
        existing.setEmail(updatedPersonnel.getEmail());
        existing.setTelephoneNumber(updatedPersonnel.getTelephoneNumber());
        return repository.save(existing);
    }

    public void deletePersonnel(Long id) {
        repository.deleteById(id);
    }
}
