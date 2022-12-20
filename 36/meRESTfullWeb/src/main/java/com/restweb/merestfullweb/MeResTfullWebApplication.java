package com.restweb.merestfullweb;

import com.restweb.merestfullweb.dto.Droid;
import com.restweb.merestfullweb.models.Greeting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

//@SpringBootApplication (scanBasePackages = {"Controllers", "repositories", "models"})
@SpringBootApplication
//@EnableConfigurationProperties(Greeting.class)
@ConfigurationPropertiesScan
public class MeResTfullWebApplication {

    public static Logger log = LoggerFactory.getLogger(MeResTfullWebApplication.class);


    public static void main(String[] args) {
        log.info("testLog1");

        SpringApplication.run(MeResTfullWebApplication.class, args);

        log.info("testLog2");
    }


}
