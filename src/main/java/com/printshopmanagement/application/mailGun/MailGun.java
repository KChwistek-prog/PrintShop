package com.printshopmanagement.application.mailGun;

import com.printshopmanagement.application.config.MailgunApi;
import com.printshopmanagement.application.scheduler.MailBuilder;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class MailGun {

    private final MailgunApi mailgunApi;

    @Autowired
    public MailGun(MailgunApi mailgunApi) {
        this.mailgunApi = mailgunApi;
    }

    public JsonNode sendMessage(MailBuilder mailBuilder) {

        HttpResponse<kong.unirest.JsonNode> request = Unirest.post("https://api.eu.mailgun.net/v3/" + mailgunApi.getDomainName() + "/messages")
			    .basicAuth("api", mailgunApi.getDomainApi())
                .queryString("from", "Excited User <vescus@gmail.com>")
                .queryString("to", "vescus@gmail.com")
                .queryString("subject", mailBuilder.prepareSubject())
                .queryString("text", mailBuilder.prepareData())
                .asJson();
        return request.getBody();
    }
}
