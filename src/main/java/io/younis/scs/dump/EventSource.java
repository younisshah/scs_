package io.younis.scs.dump;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@Slf4j
//@EnableBinding(EventSource.Source.class)
public class EventSource {

    //@Bean
    //@InboundChannelAdapter(value = Source.EVENT_SOURCE)
    public MessageSource<String> source() {
        return () -> {
            log.info("\n==========Event Source==========\n");
            String value = Try.of(
                    () -> new ObjectMapper().writeValueAsString(Foo.builder().value("mSAa vJM").build()))
                    .getOrElse("{\"value\":\"default hi\"}");
            log.info("[+] Sending value: " + value);
            return MessageBuilder.withPayload(value).build();
        };
    }

    public interface Source {
        String EVENT_SOURCE = "event-source";

        //@Output(EVENT_SOURCE)
        MessageChannel out();
    }

}
