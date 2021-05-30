package com.printshopmanagement.application.scheduler;

import com.printshopmanagement.application.mailGun.MailGun;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class RaportScheduler {

    MailBuilder mailBuilder;
    MailGun mailGun;

    @Autowired
    public RaportScheduler(MailBuilder mailBuilder, MailGun mailGun) {
        this.mailBuilder = mailBuilder;
        this.mailGun = mailGun;
    }

    // @Scheduled(cron = "0 0 17 0 0")
    @Scheduled(fixedDelay = 10000)
    public void sendMail(){
        mailGun.sendMessage(mailBuilder);
    }
}
