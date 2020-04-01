package io.younis.integration.router;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.messaging.MessageChannel;

import java.util.Random;

@Slf4j
@Configuration
public class Router {

    @Bean
    IntegrationFlow flow() {
        return IntegrationFlows
                .from(() -> new Random(System.nanoTime()).nextInt(100),
                        consumer -> consumer.poller(spec -> spec.fixedRate(2000)))
                .handle(Integer.class, (number, headers) -> {
                    log.info("[+] random number is: {}", number);
                    return number;
                })
                .routeToRecipients(spec -> spec.<Integer>recipient(even(), number -> number % 2 == 0)
                        .<Integer>recipient(odd(), number -> number % 2 != 0)).get();
    }

    @Bean
    MessageChannel even() {
        return MessageChannels.direct().get();
    }

    @Bean
    MessageChannel odd() {
        return MessageChannels.direct().get();
    }

    @Bean
    IntegrationFlow evenFlow() {
        return IntegrationFlows.from(even()).handle(Integer.class, (number, headers) -> {
            log.info("[+] EVEN NUMBER: {}", number);
            return null;
        }).get();
    }

    @Bean
    IntegrationFlow oddFlow() {
        return IntegrationFlows.from(odd()).handle(Integer.class, (number, headers) -> {
            log.info("[+] ODD NUMBER: {}", number);
            return null;
        }).get();
    }

}
