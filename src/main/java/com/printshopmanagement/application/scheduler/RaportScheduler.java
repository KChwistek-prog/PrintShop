package com.printshopmanagement.application.scheduler;

import com.mashape.unirest.http.exceptions.UnirestException;
import com.printshopmanagement.application.emaillabs.MailGun;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RaportScheduler {
    MailBuilder mailBuilder;
    MailGun mailGun;

   // @Scheduled(cron = "0 0 17 0 0")
    @Scheduled(fixedDelay = 10000)
    public void sendMail() throws UnirestException {
        System.out.println("Send");
        var mail = mailGun.sendMessage(mailBuilder);
        System.out.println(mail);
    }
}
