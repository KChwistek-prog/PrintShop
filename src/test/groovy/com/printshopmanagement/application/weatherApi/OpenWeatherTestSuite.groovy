package com.printshopmanagement.application.weatherApi

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class OpenWeatherTestSuite extends Specification{

    @Autowired
    private OpenWeatherRequest openWeatherRequest

    def "should connect to get location" () {
        when:
        var resultLocation = openWeatherRequest.OpenWeatherConnnect()

        then:
        resultLocation.getName() == "Krakow"
    }

}
