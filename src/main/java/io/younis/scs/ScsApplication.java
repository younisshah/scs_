package io.younis.scs;

import io.younis.scs.gateway.FooBarChannels;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.integration.annotation.IntegrationComponentScan;

@SpringBootApplication
@EnableBinding(FooBarChannels.class)
@IntegrationComponentScan
public class ScsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScsApplication.class, args);
    }

}
