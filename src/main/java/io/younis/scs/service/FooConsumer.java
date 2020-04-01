package io.younis.scs.service;

import io.younis.scs.gateway.FooBarChannels;
import io.younis.scs.request.Foo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Headers;

import java.util.Map;

import static io.younis.scs.constants.Constants.FOO_HEADER_INPUT;
import static io.younis.scs.constants.Constants.FOO_INPUT;

@Slf4j
@EnableBinding(FooBarChannels.class)
public class FooConsumer {

    @StreamListener(FOO_INPUT)
    public void consumeFoo(Foo foo) {
        log.info("[+] received foo: {}", foo);
    }

    @StreamListener(FOO_HEADER_INPUT)
    public void consumeFooWithHeaders(Foo foo, @Headers Map<String, String> headers) {
        log.info("[+] received foo: {}", foo);
        log.info("[+] received headers: {}", headers);
    }
}
