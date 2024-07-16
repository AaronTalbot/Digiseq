package com.digisec.project;

import com.digisec.project.model.ClientOrganisation;
import com.digisec.project.repository.ClientOrganisationRepository;
import com.digisec.project.service.ClientOrganisationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ClientOrganisationServiceTest {

    @Mock
    private ClientOrganisationRepository clientOrganisationRepository;

    @InjectMocks
    private ClientOrganisationService clientOrganisationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createClientOrganisation() {
        ClientOrganisation org = new ClientOrganisation();
        org.setName("Test Org");
        org.setRegistrationDate(LocalDate.now());
        org.setExpiryDate(LocalDate.now().plusYears(1));

        when(clientOrganisationRepository.save(any(ClientOrganisation.class))).thenReturn(org);

        ClientOrganisation createdOrg = clientOrganisationService.createClientOrganisation(org);

        assertNotNull(createdOrg);
        assertEquals("Test Org", createdOrg.getName());
    }

    @Test
    void getClientOrganisationById() {
        ClientOrganisation org = new ClientOrganisation();
        org.setId(1L);
        org.setName("Test Org");

        when(clientOrganisationRepository.findById(1L)).thenReturn(Optional.of(org));

        ClientOrganisation foundOrg = clientOrganisationService.getClientOrganisationById(1L);

        assertNotNull(foundOrg);
        assertEquals("Test Org", foundOrg.getName());
    }

    @Test
    void updateClientOrganisation() {
        ClientOrganisation org = new ClientOrganisation();
        org.setId(1L);
        org.setName("Test Org");

        ClientOrganisation updatedOrg = new ClientOrganisation();
        updatedOrg.setName("Updated Org");

        when(clientOrganisationRepository.findById(1L)).thenReturn(Optional.of(org));
        when(clientOrganisationRepository.save(any(ClientOrganisation.class))).thenReturn(updatedOrg);

        ClientOrganisation result = clientOrganisationService.updateClientOrganisation(1L, updatedOrg);

        assertNotNull(result);
        assertEquals("Updated Org", result.getName());
    }

    @Test
    void deleteClientOrganisation() {
        clientOrganisationService.deleteClientOrganisation(1L);
        verify(clientOrganisationRepository, times(1)).deleteById(1L);
    }
}
