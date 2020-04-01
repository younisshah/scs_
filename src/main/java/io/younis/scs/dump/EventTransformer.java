package io.younis.scs.dump;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;

@Slf4j
//@EnableBinding(Processor.class)
public class EventTransformer {

   // @StreamListener(Processor.INPUT)
   // @SendTo(Processor.OUTPUT)
    public Bar transform(Foo foo) {
        log.info("\n==========Event Transformer==========\n");
        log.info("[+] Received: {}", foo);
        log.info("[+] Transforming");
        Bar bar = Bar.builder().value("dYMOwPFoNDvp").build();
        log.info("[+] Transformed value: {}", bar);
        return bar;
    }
}
