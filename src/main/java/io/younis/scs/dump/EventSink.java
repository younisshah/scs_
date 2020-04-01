package io.younis.scs.dump;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@Slf4j
//@EnableBinding(EventSink.Sink.class)
public class EventSink {

    //@StreamListener(Sink.EMAIL_SINK)
    public void sink(Bar bar) {
        log.info("\n******************Email Sink******************\n");
        log.info("[+] Received message: {}", bar);
    }

    public interface Sink {
        String EMAIL_SINK = "email-sink";

        //@Input(EMAIL_SINK)
        SubscribableChannel in();
    }
}
