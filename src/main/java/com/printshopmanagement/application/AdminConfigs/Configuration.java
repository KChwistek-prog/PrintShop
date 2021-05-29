package com.printshopmanagement.application.AdminConfigs;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Configuration {
    @Value("${admin.domain.name}")
    private String domainName;

    @Value("${admin.domain.api}")
    private String domainApi;
}
