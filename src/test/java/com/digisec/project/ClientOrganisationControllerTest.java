package com.digisec.project;

import com.digisec.project.controller.ClientOrganisationController;
import com.digisec.project.model.ClientOrganisation;
import com.digisec.project.service.ClientOrganisationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClientOrganisationControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ClientOrganisationService clientOrganisationService;

    @InjectMocks
    private ClientOrganisationController clientOrganisationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(clientOrganisationController).build();
    }

    @Test
    void getAllClientOrganisations() throws Exception {
        ClientOrganisation org1 = new ClientOrganisation();
        org1.setName("Org1");
        org1.setRegistrationDate(LocalDate.now());
        org1.setExpiryDate(LocalDate.now().plusYears(1));

        ClientOrganisation org2 = new ClientOrganisation();
        org2.setName("Org2");
        org2.setRegistrationDate(LocalDate.now());
        org2.setExpiryDate(LocalDate.now().plusYears(1));

        when(clientOrganisationService.getAllClientOrganisations()).thenReturn(Arrays.asList(org1, org2));

        mockMvc.perform(get("/api/client-organisations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Org1"))
                .andExpect(jsonPath("$[1].name").value("Org2"));
    }

    @Test
    void createClientOrganisation() throws Exception {
        ClientOrganisation org = new ClientOrganisation();
        org.setName("Test Org");
        org.setRegistrationDate(LocalDate.now());
        org.setExpiryDate(LocalDate.now().plusYears(1));

        when(clientOrganisationService.createClientOrganisation(any(ClientOrganisation.class))).thenReturn(org);

        mockMvc.perform(post("/api/client-organisations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Test Org\", \"registrationDate\": \"2024-07-16\", \"expiryDate\": \"2025-07-16\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Org"));
    }

    @Test
    void getClientOrganisationById() throws Exception {
        ClientOrganisation org = new ClientOrganisation();
        org.setId(1L);
        org.setName("Test Org");

        when(clientOrganisationService.getClientOrganisationById(1L)).thenReturn(org);

        mockMvc.perform(get("/api/client-organisations/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Org"));
    }

    @Test
    void updateClientOrganisation() throws Exception {
        ClientOrganisation org = new ClientOrganisation();
        org.setId(1L);
        org.setName("Updated Org");

        when(clientOrganisationService.updateClientOrganisation(any(Long.class), any(ClientOrganisation.class))).thenReturn(org);

        mockMvc.perform(put("/api/client-organisations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Updated Org\", \"registrationDate\": \"2024-07-16\", \"expiryDate\": \"2025-07-16\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Updated Org"));
    }

    @Test
    void deleteClientOrganisation() throws Exception {
        mockMvc.perform(delete("/api/client-organisations/1"))
                .andExpect(status().isOk());
    }
}
