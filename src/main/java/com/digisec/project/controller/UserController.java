package com.digisec.project.controller;

import com.digisec.project.model.Personnel;
import com.digisec.project.repository.PersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private PersonnelRepository personnelRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody Personnel personnel) {
        if (personnelRepository.findByUsername(personnel.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken");
        }
        personnel.setPassword(new BCryptPasswordEncoder().encode(personnel.getPassword()));
        personnelRepository.save(personnel);
        return ResponseEntity.ok("User registered successfully");
    }
}
