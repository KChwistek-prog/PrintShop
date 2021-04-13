package com.printshopmanagement.application.scheduler;

import com.printshopmanagement.application.emaillabs.EmailLabs;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RaportScheduler {
    MailBuilder mailBuilder;
    EmailLabs emailLabs;

    @Scheduled(cron = "0 0 17 0 0")
    public void sendMail() {
        emailLabs.send(mailBuilder);
    }
}
