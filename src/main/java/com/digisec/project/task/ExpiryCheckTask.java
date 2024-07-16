package com.digisec.project.task;


import com.digisec.project.model.ClientOrganisation;
import com.digisec.project.repository.ClientOrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ExpiryCheckTask {
    @Autowired
    private ClientOrganisationRepository repository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void checkExpiryDates() {
        List<ClientOrganisation> organisations = repository.findAll();
        LocalDate now = LocalDate.now();
        for (ClientOrganisation org : organisations) {
            if (org.getExpiryDate().isBefore(now)) {
                org.setEnabled(false);
                repository.save(org);
            }
        }
    }
}
