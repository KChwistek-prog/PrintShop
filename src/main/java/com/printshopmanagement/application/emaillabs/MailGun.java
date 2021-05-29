package com.printshopmanagement.application.emaillabs;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.printshopmanagement.application.AdminConfigs.Configuration;
import com.printshopmanagement.application.scheduler.MailBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MailGun {

    private final Configuration configuration;

    public JsonNode sendMessage(MailBuilder mailBuilder) throws UnirestException {
        HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/" + configuration.getDomainName() + "/messages")
			    .basicAuth("api", configuration.getDomainApi())
                .queryString("from", "Excited User <vescus@gmail.com>")
                .queryString("to", "vescus@gmail.com")
                .queryString("subject", mailBuilder.prepareSubject())
                .queryString("text", mailBuilder.prepareData())
                .asJson();
        return request.getBody();
    }
}
