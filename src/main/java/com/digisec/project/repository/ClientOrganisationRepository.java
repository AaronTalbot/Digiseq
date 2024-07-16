package com.digisec.project.repository;

import com.digisec.project.model.ClientOrganisation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientOrganisationRepository extends JpaRepository<ClientOrganisation, Long> {
    List<ClientOrganisation> findByEnabled(boolean enabled);

}
